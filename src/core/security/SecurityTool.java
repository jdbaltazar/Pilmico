package core.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class SecurityTool {

	public static final String ENCODING = "UTF-8";
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

	private static Cipher getCipherEncrypt() throws Exception {
		byte[] keyBytes = KEY.getBytes(ENCODING);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		return cipher;
	}

	private static Cipher getCipherDecrypt() throws Exception {
		byte[] keyBytes = KEY.getBytes(ENCODING);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		return cipher;
	}

	public static void encryptAndWriteFile(File inputFile, File outputFile) throws Exception {
		Cipher cipher = getCipherEncrypt();
		FileOutputStream fos = null;
		CipherOutputStream cos = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inputFile);
			fos = new FileOutputStream(outputFile);
			cos = new CipherOutputStream(fos, cipher);
			fos = null;
			byte[] data = new byte[1024];
			int read = fis.read(data);
			while (read != -1) {
				cos.write(data, 0, read);
				read = fis.read(data);
				System.out.println(new String(data, ENCODING).trim());
			}
			cos.flush();
		} finally {
			if (cos != null) {
				cos.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}

	public static void decryptFile(File inputFile, File outputFile) throws Exception {
		Cipher cipher = getCipherDecrypt();
		FileOutputStream fos = null;
		CipherInputStream cis = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inputFile);
			cis = new CipherInputStream(fis, cipher);
			fos = new FileOutputStream(outputFile);
			byte[] data = new byte[1024];
			int read = cis.read(data);
			while (read != -1) {
				fos.write(data, 0, read);
				read = cis.read(data);
				System.out.println(new String(data, ENCODING).trim());
			}
		} finally {
			fos.close();
			cis.close();
			fis.close();
		}
	}
}