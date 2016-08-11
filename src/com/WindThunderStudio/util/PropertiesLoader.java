package com.WindThunderStudio.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
	
	/** Method to load the current config, and change it with newLang,
	 * then write back to the File.
	 * @param newLang the new language and locale to change.
	 * @throws IOException
	 */
	public void changeLanguage(String newLang) throws IOException {
		Properties prop = new Properties();
		String uri = Constants.CONFIG_PATH;
		//if uri begin with "/", it's absolute.
		//if not, the path is constructed from under the same package where this class
		//is located. So in this case, the properties file cannot be under super or parallel 
		//packages.
		InputStream input = getClass().getResourceAsStream(uri);
		File f = null;
		FileWriter writer = null;
		try {
			prop.load(input);
			prop.setProperty(Constants.CONFIG_KEY_LOCALE, newLang);
			URL url = getClass().getResource(uri);
			f = new File(url.getFile());
			writer = new FileWriter(f);
			prop.store(writer, null);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			input.close();
			if (writer != null) {
				writer.close();
			}
		}
	}
}
