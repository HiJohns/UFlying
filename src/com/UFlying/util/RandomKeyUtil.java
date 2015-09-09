package com.UFlying.util;

import java.util.Random;
import java.util.UUID;

public class RandomKeyUtil {

	public static String randomKey() {
		String token = Long.toString(Math.abs(new Random().nextLong()), 10);
		return token.substring(0, 5);
	}

	public static String getRandomKeyForPhoneRegister() {
		String token = Long.toString(Math.abs(new Random().nextLong()), 10);
		return token.substring(0, 6);
	}

	public static String getToken() {
		String token = UUID.randomUUID().toString();
		return token;
	}

	public static String getRandomFileName() {
		return getToken().replaceAll("-", "");
	}

}
