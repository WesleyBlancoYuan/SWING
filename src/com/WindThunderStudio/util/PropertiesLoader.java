package com.WindThunderStudio.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	/**
	 * Method of loading settings from config.properties.
	 * @return Properties file as config.
	 * @throws IOException, if the file cannot be loaded.
	 */
	public Properties getSettings() throws IOException {
		Properties prop = new Properties();
		String uri = Constants.CONFIG_PATH;
		//if uri begin with "/", it's absolute.
		//if not, the path is constructed from under the same package where this class
		//is located. So in this case, the properties file cannot be under super or parallel 
		//packages.
		InputStream input = getClass().getResourceAsStream(uri);
		try {
			prop.load(input);
			return prop;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			input.close();
		}
		return null;
	}
}
