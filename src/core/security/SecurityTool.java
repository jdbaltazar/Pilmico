package core.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class SecurityTool {

	private static final String ENCODING = "UTF-8";
	private static final String ALGORITHM = "AES";
	private static final String KEY = "PilmicoStore2013";

	private SecurityTool() {

	}

	public static String encryptString(String valueToEnc) throws Exception {
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, generateKey());
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);
		return encryptedValue;
	}

	public static String decryptString(String encryptedValue) throws Exception {
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, generateKey());
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(KEY.getBytes(ENCODING), ALGORITHM);
		return key;
	}

	public static CipherInputStream encryptFile(String inputFilePath) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey());
		return new CipherInputStream(new FileInputStream(inputFilePath), cipher);
	}

	public static CipherInputStream decryptFile(String inputFilePath) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, generateKey());
		return new CipherInputStream(new FileInputStream(inputFilePath), cipher);
	}

	public static void writeToFile(InputStream is, String outputFilePath) throws IOException {
		OutputStream os = new FileOutputStream(outputFilePath);
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
}