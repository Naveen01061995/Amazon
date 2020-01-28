package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {

	public static Properties prop;

	public static Properties propertyFileLoad(String propertyFilePath) {

		try {

			File file = new File(propertyFilePath);
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {
			System.out.println("issue with the Envoirnment file Load");
		}

		return prop;

	}

}
