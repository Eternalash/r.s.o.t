package util.Ecc;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.Map;


public final class EccDecrypt {

  // Ap ple Pay uses an 0s for the IV
  private static final byte[] IV = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
      0x00, 0x00};

  // precompute prefix bytes for the 'other' parameter of the NIST contact KDF
  private static final byte[] KDF_OTHER_BYTES_PREFIX;

  static {
    try {
      KDF_OTHER_BYTES_PREFIX = ((char) 0x0D + "id-aes256-GCM" + "Apple").getBytes("ASCII");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public String decrypt(String paymentTokenData, String merchantPkcFilename, String pkcs12Filename,String p12Password)
      throws IOException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException, UnrecoverableKeyException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {

    if (Security.getProvider("BC") == null) {
      Security.addProvider(new BouncyCastleProvider());
    }

    // read json payment token
    Map paymentToken = new Gson().fromJson(encode(Base64.decode(paymentTokenData)), Map.class);

    // data is the ciphertext
    byte[] data = org.bouncycastle.util.encoders.Base64.decode((String) paymentToken.get("data"));

    // read the ephemeral public key. it's a PEM file without header/footer -- add it back to make our lives easy
    Map header = (Map) paymentToken.get("header");
    String ephemeralPubKeyStr = header.get("ephemeralPublicKey").toString();
    KeyFactory keyFactory = KeyFactory.getInstance("EC", "SunEC");
    X509EncodedKeySpec spec = new X509EncodedKeySpec(org.bouncycastle.util.encoders.Base64.decode(ephemeralPubKeyStr));
    ECPublicKey ephemeralPublicKey = null;
    PublicKey pubKey = keyFactory.generatePublic(spec);
    if (pubKey != null) {
      ephemeralPublicKey = (ECPublicKey) pubKey;
    }
    FileInputStream fileInputStream = new FileInputStream(merchantPkcFilename);

    // Apple assigns a merchant identifier and places it in an extension (OID 1.2.840.113635.100.6.32)
    final X509Certificate merchantCertificate = readDerEncodedX509Certificate(fileInputStream);
    byte[] merchantIdentifier = extractMerchantIdentifier(merchantCertificate);

    // load the merchant EC private key
    // WARNING: this key should live permanently in an HSM in a production environment!
    // export it from e.g. mac's keychain
    ECPrivateKey merchantPrivateKey = loadPrivateKey(pkcs12Filename,p12Password);

    // now we have all the data we need -- decrypt per Apple Pay spec
    final byte[] plaintext = decrypt(data, merchantPrivateKey, ephemeralPublicKey, merchantIdentifier);
    return new String(plaintext, "ASCII");
  }


  public byte[] decrypt(byte[] ciphertext, ECPrivateKey merchantPrivateKey, ECPublicKey ephemeralPublicKey,
      byte[] merchantIdentifier)
      throws InvalidKeyException, NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
    // ECDH key agreement
    final KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", "BC");
    keyAgreement.init(merchantPrivateKey);
    keyAgreement.doPhase(ephemeralPublicKey, true);
    final byte[] sharedSecret = keyAgreement.generateSecret();

    // NIST key derivation function w/ Apple Pay specific parameters
    byte[] partyV = merchantIdentifier;
    byte[] other = new byte[KDF_OTHER_BYTES_PREFIX.length + partyV.length];
    System.arraycopy(KDF_OTHER_BYTES_PREFIX, 0, other, 0, KDF_OTHER_BYTES_PREFIX.length);
    System.arraycopy(partyV, 0, other, KDF_OTHER_BYTES_PREFIX.length, partyV.length);

    final Digest digest = new SHA256Digest();
    KDFConcatGenerator kdfConcatGenerator = new KDFConcatGenerator(digest, other);
    kdfConcatGenerator.init(new KDFParameters(sharedSecret, null));
    byte[] aesKey = new byte[32];
    kdfConcatGenerator.generateBytes(aesKey, 0, aesKey.length);

    final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
    final SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
    cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(IV));
    return cipher.doFinal(ciphertext);
  }

  private ECPrivateKey loadPrivateKey(String pkcs12Filename,String p12Password)
      throws KeyStoreException, NoSuchProviderException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
    KeyStore keystore = KeyStore.getInstance("PKCS12", "BC");
    final FileInputStream fileInputStream = new FileInputStream(pkcs12Filename);
    keystore.load(fileInputStream, p12Password.toCharArray());
    Enumeration<String> aliases = keystore.aliases();
    String alias = null;
    while (aliases.hasMoreElements()) {
      alias = aliases.nextElement();
    }
    return (ECPrivateKey) keystore.getKey(alias, null);
  }

  private byte[] extractMerchantIdentifier(X509Certificate merchantCertificate)
      throws UnsupportedEncodingException {
    byte[] merchantIdentifierTlv = merchantCertificate.getExtensionValue("1.2.840.113635.100.6.32");
    assert merchantIdentifierTlv.length == 68;
    byte[] merchantIdentifier = new byte[64];
    System.arraycopy(merchantIdentifierTlv, 4, merchantIdentifier, 0, 64);
    return hexStringToByteArray(new String(merchantIdentifier, "ASCII"));
  }

  private X509Certificate readDerEncodedX509Certificate(InputStream in)
      throws CertificateException {
    CertificateFactory factory = CertificateFactory.getInstance("X.509");
    return (X509Certificate) factory.generateCertificate(in);
  }

  public byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i + 1), 16));
    }
    return data;
  }

  public static String encode(byte[] bytes){
    Preconditions.checkArgument(bytes != null);

    return new String(bytes, Charset.forName("UTF-8"));
  }

  public static byte[] decode(String text){
    Preconditions.checkArgument(!Strings.isNullOrEmpty(text));

    return text.getBytes(Charset.forName("UTF-8"));
  }
}

