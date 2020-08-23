package util.Ecc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.util.Enumeration;

public final class ApplePayAesGcm {

  private static final byte[] IV = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
      0x00, 0x00};

  private ApplePayAesGcm() {
  }

  private static RSAPrivateCrtKey loadPrivateKey(InputStream privateKeyStream)
      throws KeyStoreException, NoSuchProviderException, IOException, NoSuchAlgorithmException,
      CertificateException, UnrecoverableKeyException {
    KeyStore keystore = KeyStore.getInstance("PKCS12", "BC");
    keystore.load(privateKeyStream, "123456".toCharArray());
    Enumeration<String> aliases = keystore.aliases();
    String alias = null;
    while (aliases.hasMoreElements()) {
      alias = aliases.nextElement();
      boolean b = keystore.isKeyEntry(alias);
      if (b) {
        break;
      }
    }
    return (RSAPrivateCrtKey) keystore.getKey(alias, null);
  }

  /**
   * Use the symmetric key to decrypt the value of the data key.
   * For RSA (RSA_v1), Decrypt the data key using AES–128 (id-aes128-GCM 2.16.840.1.101.3.4.1.6),
   * with an initialization vector of 16 null bytes and no associated authentication data.
   */
  public static byte[] decryptData(byte[] dataBytes, byte[] wrappedBytes, String privateKeyBytes)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchProviderException,
      IOException, UnrecoverableKeyException, KeyStoreException, CertificateException {
    if (Security.getProvider("BC") == null) {
      Security.addProvider(new BouncyCastleProvider());
    }
    RSAPrivateCrtKey erchantPrivateKey = loadPrivateKey(new ByteArrayInputStream(Base64.decode(privateKeyBytes)));
    Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA-256AndMGF1Padding");
    cipher.init(Cipher.DECRYPT_MODE, erchantPrivateKey);
    byte[] wrapped = cipher.doFinal(wrappedBytes);

    SecretKeySpec secretKeySpec = new SecretKeySpec(wrapped, "AES");
    cipher = Cipher.getInstance("AES/GCM/NoPadding","BC");
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new GCMParameterSpec(128, IV));
    return cipher.doFinal(dataBytes);
  }


}
