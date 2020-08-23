package util.Rsa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.BaseEncoding;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ApplePayCryptoTest {

  private ObjectMapper jsonMapper = new ObjectMapper();
@Test
  public void testVerify() throws Exception {
    String dataJson = "json";
    PaymentData paymentData = jsonMapper.readValue(dataJson, PaymentData.class);
    
    byte[] symmetricKeyBytes = ApplePayCryptoUtil.restoreSymmetricKey(
        Base64.decodeBase64(paymentData.getHeader().getWrappedKey()), getPrivateKey("private_key.pem"));
    
    System.out.println(BaseEncoding.base16().encode(symmetricKeyBytes));
    
    byte[] decryptedDataBytes = ApplePayCryptoUtil.decryptData(symmetricKeyBytes, Base64.decodeBase64(paymentData.getData()));
    System.out.println(new String(decryptedDataBytes, "UTF-8"));
  }
  
  private byte[] getPrivateKey(String privateKeyFile) throws IOException {
    DataInputStream dis = new DataInputStream(new FileInputStream(this.getClass().getClassLoader().getResource(privateKeyFile).getFile()));
    byte[] priKeyBytes = new byte[dis.available()];
    dis.readFully(priKeyBytes);
    dis.close();
    
    String temp = new String(priKeyBytes);
    temp = temp.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");//.replaceAll("\r", "").replaceAll("\n", "");
    
    byte[] decoded = Base64.decodeBase64(temp);
    return decoded;
  }
  
}
