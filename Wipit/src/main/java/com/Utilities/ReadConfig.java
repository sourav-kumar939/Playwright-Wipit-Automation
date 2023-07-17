package com.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
Properties properties;
	
	String path="C:\\Users\\SouravKumar\\eclipse-workspace\\JIN\\Configuration\\config.properties";
	
	public ReadConfig() {
		try {
			properties=new Properties();
			
			FileInputStream fis=new FileInputStream(path);
			properties.load(fis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getBaseUrl() {
		String value=properties.getProperty("baseUrl");
		
		if(value!=null) {
			return value;
		}
		else
			throw new RuntimeException("url not specified in config file");
	}
	
	public String getBrowser() {
		String value=properties.getProperty("browser");
		
		if(value!=null) {
			return value;
		}
		else
			throw new RuntimeException("browser not specified in config file");
	}
	
	public String getUserName() {
		String value=properties.getProperty("userName");
		
		if(value!=null) {
			return value;
		}
		else
			throw new RuntimeException("UserName not specified in config file");
	}
	
	public String getUserName2() {
		String value=properties.getProperty("userName2");
		
		if(value!=null) {
			return value;
		}
		else
			throw new RuntimeException("UserName2 not specified in config file");
	}
	
	public String getPassword() {
		String value=properties.getProperty("password");
		
		if(value!=null) {
			return value;
		}
		else
			throw new RuntimeException("Password not specified in config file");
	}
	
	public String getPassword2() {
		String value=properties.getProperty("password2");
		
		if(value!=null) {
			return value;
		}
		else
			throw new RuntimeException("Password2 not specified in config file");
	}

}
