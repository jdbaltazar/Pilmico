package core.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherExample {

	private static final String ENCODING = "UTF-8";
	private static final String ALGORITHM = "AES";
	private static final String KEY = "PilmicoStore2013";

	public static void main(String[] args) {
		try {
			String key = "PilmicoStore2013"; // needs to be at least 8 characters
														// for DES

			FileInputStream fis = new FileInputStream("data/pilmico-create.sql");
			FileOutputStream fos = new FileOutputStream("data/encrypted.txt");
			encrypt(key, fis, fos);

			FileInputStream fis2 = new FileInputStream("data/encrypted.txt");
			FileOutputStream fos2 = new FileOutputStream("data/decrypted.txt");
			decrypt(key, fis2, fos2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(KEY.getBytes(ENCODING), ALGORITHM);
		return key;
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		// DESKeySpec dks = new DESKeySpec(key.getBytes());
		// SecretKeyFactory skf = SecretKeyFactory.getInstance("AES");
		// SecretKey desKey = skf.generateSecret(dks);
		// Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for
		// SunJCE

		Cipher cipher = Cipher.getInstance(ALGORITHM);

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, generateKey());
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, generateKey());
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
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