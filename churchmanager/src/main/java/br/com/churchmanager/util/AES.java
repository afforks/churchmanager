package br.com.churchmanager.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AES {
	private static SecretKeySpec secretKey = getKey(
			"kdjdoKSKDHDKDKkshkjIugTRDuhbUGFjhbGijndokeoeowdfiwowiDeGVtBKuGfGSHDHD");
	private static String decryptedString;
	private static String encryptedString;

	public static SecretKeySpec getKey(String myKey) {
		byte[] key = myKey.getBytes();
		SecretKeySpec secretKey = null;
		MessageDigest sha = null;

		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
			return secretKey;
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException arg4) {
			arg4.printStackTrace();
			return null;
		}
	}

	public static String getDecryptedString() {
		return decryptedString;
	}

	public static void setDecryptedString(String decryptedString) {
		AES.decryptedString = decryptedString;
	}

	public static String getEncryptedString() {
		return encryptedString;
	}

	public static void setEncryptedString(String encryptedString) {
		AES.encryptedString = encryptedString;
	}

	public static String encrypt(String strToEncrypt) {
		try {
			Cipher e = Cipher.getInstance("AES/ECB/PKCS5Padding");
			e.init(1, secretKey);
			setEncryptedString(Base64.encodeBase64String(e.doFinal(strToEncrypt.getBytes("UTF-8"))));
		} catch (Exception arg1) {
			arg1.printStackTrace();
		}

		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			Cipher e = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			e.init(2, secretKey);
			setDecryptedString(new String(e.doFinal(Base64.decodeBase64(strToDecrypt))));
		} catch (Exception arg1) {
			arg1.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		encrypt("Mensagem");
		String crip = getEncryptedString();
		decrypt(crip);
		String dec = getDecryptedString();
		String concat = crip + " - " + dec;
		System.out.println(concat);
	}
}