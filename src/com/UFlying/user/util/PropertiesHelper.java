package com.UFlying.user.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesHelper {

	private static final Logger logger = Logger.getLogger(PropertiesHelper.class);

	private static final Properties properties = new Properties();

	private static final String file = "/conf.properties";

	static {
		try {
			InputStream in = PropertiesHelper.class.getResourceAsStream(file);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static Object get(Object key) {
		return properties.get(key);
	}

	public static boolean containsKey(Object key) {
		return properties.containsKey(key);
	}

}
