package com.UFlying.user.util;

public class MemcacheUtil {
	
	public static String getVerifyCodeKey(String phoneNumber) {
		
		return  "code_"  + phoneNumber;
		
	}
	
}
