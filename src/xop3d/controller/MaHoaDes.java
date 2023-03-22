package xop3d.controller;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.buf.Utf8Decoder;

public class MaHoaDes {
	 public static String Encrypt(String value,String Key) throws NoSuchAlgorithmException, NoSuchPaddingException, 
     InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
try {
		String originalText = value;
		String key = "12345678";
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] byteEncrypted = cipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
		String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);
		return encrypted;
  } catch (Exception ex) {
	ex.printStackTrace();
  }
return null;
}
public static String Decrypt(String value,String Key) throws NoSuchAlgorithmException, NoSuchPaddingException, 
     InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
try {
		String encryptText = value;
		String key = "12345678";
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptText));
		return  new String(decrypted,StandardCharsets.UTF_8);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
return null;
}
}
