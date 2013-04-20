package org.vijos.auth.lib;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	
	private static MessageDigest md5 = null;
	private static MessageDigest sha1 = null;
	private static MessageDigest sha256 = null;
	
	public Hash() {
		try {
			Hash.md5 = MessageDigest.getInstance("MD5");
			Hash.sha1 = MessageDigest.getInstance("SHA1");
			Hash.sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static String MD5(String text) {
		md5.reset();
        md5.update(text.getBytes());
        byte[] digest = md5.digest();

        return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
	}

	public static String SHA1(String text) {
		sha1.reset();
        sha1.update(text.getBytes());
        byte[] digest = sha1.digest();

        return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
	}

	public static String SHA256(String text) {
		sha256.reset();
        sha256.update(text.getBytes());
        byte[] digest = sha256.digest();

        return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
	}
	
	public static String computeHash(String method, String text) {
		
		switch(method) {
			case "md5":
				return Hash.MD5(text);
			case "sha1":
				return Hash.SHA1(text);
			case "sha256":
				return Hash.SHA256(text);
			default:
				return text;
		}
		
	}
}