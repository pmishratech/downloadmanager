package com.download.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {
	private static Map<String, String> configProperties = null;
	private static final String CONFIG = "config.properties";
	private static final String INPUTS = "input.txt";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map<String, String> loadConfigProperties() throws IOException {
		if (Utility.isEmpty(configProperties)) {
			InputStream is = PropertyLoader.class.getClassLoader().getResourceAsStream(CONFIG);
			Properties configProp = new Properties();
			configProp.load(is);
			configProperties = new HashMap(configProp);
			is.close();
		}
		return configProperties;
	}

	public static String getConfigProperty(String key) throws IOException {
		return loadConfigProperties().get(key);
	}

	/**
	 * Method to load all input URL from property
	 * @return List of URL strings
	 * @throws IOException
	 */
	public static List<String> loadInputs() throws IOException {
		InputStream is = PropertyLoader.class.getClassLoader().getResourceAsStream(INPUTS);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		List<String> inputs = new ArrayList<String>();
		while ((str = br.readLine()) != null) {
			inputs.add(str.trim());
		}
		return inputs;
	}
}