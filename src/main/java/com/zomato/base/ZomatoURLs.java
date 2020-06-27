package com.zomato.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;



public enum ZomatoURLs {

	BASE_URL,
	
	CATEGORIES_URL,
	CITIES_URL,
	COLLECTIONS_URL,
	CUISINES_URL, 
	ESTABLISHMENTS_URL,
	GECODE_URL,

	LOACTIONS_URL,
	LOCATION_DETAILS_URL,

	DAILY_MENU_URL,
	RESTAURENT_URL,
	REVIEWS_URL,
	SEARCH_URL,

	API_KEY;
	
	private Properties properties;
	
	private ZomatoURLs() {
		properties = new Properties();
		try {
			if(StringUtils.isEmpty(System.getProperty("env"))) {
				properties.load(
						new FileInputStream("C:\\Users\\himan\\ZetaTest\\ZetaZomatoTest\\src\\test"
								+"\\resources\\zomato.cfg")
						);
			}
			else {
				properties.load(
						new FileInputStream(
								System.getProperty("basedir")+
								"/../resources"+
								System.getProperty("env")+
								".cfg")
						);
			
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String value() {
		return properties.getProperty(this.name());
	}
}
