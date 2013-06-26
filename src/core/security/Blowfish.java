package core.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class Blowfish {

	private static final String key = "calbayog";

	public static void main(String[] args) throws Exception {
		System.out.println("For encryption: " + encrypt("123456"));
		System.out.println("Encrypted: " + decrypt("8T79y+oEatE"));
	}

	public static String encrypt(String password) throws Exception {
		byte[] keyData = (key + password).getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] hasil = cipher.doFinal(password.getBytes());
		return new BASE64Encoder().encode(hasil);
	}

	public static String decrypt(String string) throws Exception {
		byte[] keyData = (key + "password").getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] hasil = cipher.doFinal(new BASE64Decoder().decodeBuffer(string));
		return new String(hasil);
	}
}