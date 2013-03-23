package com.vijoslogin.lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	private static MessageDigest md5 = null;
	
	public MD5() {
		try {
			MD5.md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static String md5(String inStr) {
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		
		for (int i=0; i < charArray.length; i++) byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = MD5.md5.digest(byteArray);
		
		StringBuffer hexValue = new StringBuffer();
		
		for (int i=0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}
}