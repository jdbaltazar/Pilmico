package core.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoHelper {

	public final static int BASE64 = 0;

	private final static byte[] KEY = { (byte) 164, (byte) 208, (byte) 200, (byte) 118, (byte) 112, (byte) 181, (byte) 82, (byte) 74, (byte) 30,
			(byte) 179, (byte) 9, (byte) 125, (byte) 95, (byte) 97, (byte) 51, (byte) 226 };
	private final static byte[] SALT = { (byte) 129, (byte) 187, (byte) 151, (byte) 73, (byte) 225, (byte) 239, (byte) 49, (byte) 24 };

	private final static String CIPHER = "Blowfish/CBC/PKCS5Padding";
	private final static String SECRET_KEY = "Blowfish";

	private static Cipher cipher;
	private static IvParameterSpec salt;
	private static SecretKeySpec secretKey;

	private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		if (cipher == null) {
			cipher = Cipher.getInstance(CIPHER);
		}
		return cipher;
	}

	private static SecretKeySpec getSecretKey() throws NoSuchAlgorithmException {
		if (secretKey == null) {
			secretKey = new SecretKeySpec(KEY, SECRET_KEY);
		}
		return secretKey;
	}

	private static IvParameterSpec getSalt() throws NoSuchPaddingException {
		if (salt == null) {
			salt = new IvParameterSpec(SALT);
		}
		return salt;
	}

	public static synchronized void writeEncrypted(File file, String value) throws Exception {
		Cipher c = getCipher();
		c.init(Cipher.ENCRYPT_MODE, getSecretKey(), getSalt());

		FileOutputStream fos = new FileOutputStream(file);
		try {
			CipherOutputStream cos = new CipherOutputStream(fos, c);

			try {
				ByteArrayInputStream bais = new ByteArrayInputStream(value.getBytes());

				byte[] buf = new byte[8];
				int len = bais.read(buf);
				while (len > 0) {
					cos.write(buf, 0, len);
					len = bais.read(buf);
				}
			} finally {
				cos.close();
			}
		} finally {
			fos.close();
		}
	}

	public static synchronized byte[] encrypt(byte[] input) throws Exception {
		Cipher c = getCipher();
		c.init(Cipher.ENCRYPT_MODE, getSecretKey(), getSalt());

		return c.doFinal(input);
	}

	public static synchronized byte[] decrypt(byte[] input) throws Exception {
		Cipher c = getCipher();
		c.init(Cipher.DECRYPT_MODE, getSecretKey(), getSalt());

		return c.doFinal(input);
	}

	public static synchronized String readEncrypted(File file) throws Exception {
		String returnVal = null;
		Cipher c = getCipher();
		c.init(Cipher.DECRYPT_MODE, getSecretKey(), getSalt());

		FileInputStream fis = new FileInputStream(file);
		try {
			CipherInputStream cis = new CipherInputStream(fis, c);
			try {

				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				byte[] buf = new byte[8];
				int len = cis.read(buf);
				while (len > 0) {
					baos.write(buf, 0, len);
					len = cis.read(buf);
				}

				returnVal = baos.toString();
			} finally {
				cis.close();
			}
		} finally {
			fis.close();
		}
		return returnVal;
	}
}