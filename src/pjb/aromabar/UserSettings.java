package pjb.aromabar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class UserSettings {
	private static UserSettings instance = new UserSettings("aromabar.properties");
	
	private String path;
	
	private Map<String,String> cache = new HashMap<String,String>();
	
	public UserSettings(String path) {
		this.path = path;
		loadProperties();
	}
	
	public static UserSettings getInstance() {
		return instance;
	}
	
	private void loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(path)));
			@SuppressWarnings("unchecked")
			Enumeration<String> propertyNames = (Enumeration<String>) properties.propertyNames();
			while(propertyNames.hasMoreElements()) {
				String key = propertyNames.nextElement();
				cache.put(key, properties.getProperty(key));
			}
		} catch (IOException e) {
			throw new RuntimeException("@UserSettings.load / could not load properties '" + path + "'");
		}
	}
	
	public List<String> findStringKeys(String prefix) {
		List<String> list = new ArrayList<String>(); 
		for(Entry<String,String> entry : cache.entrySet()) {
			if(entry.getKey().startsWith(prefix)) {
				list.add(entry.getKey());
			}
		}
		return list;
	}
	
	public String getStringValue(String key) {
		return cache.get(key);
	}

	public String getStringValue(String key, String defaultValue) {
		String value =  cache.get(key);
		return value != null ? value : defaultValue;
	}
	
	public boolean getBooleanValue(String key, boolean defaultValue) {
		String value = getStringValue(key);
		if(value != null) {
			return Boolean.TRUE.toString().equals(value.toLowerCase());
		}
		return defaultValue;
	}
	
	public int getIntegerValue(String key, int defaultValue) {
		String value = getStringValue(key);
		if(value != null) {
			try {
				return Integer.parseInt(value);
			} catch(NumberFormatException e) {
				throw new RuntimeException("@UserSettings.getIntegerValue / bad properties value format in file '" + path + "', key='" + key + "', value='" + value + "'");
			}
		}
		return defaultValue;
	}
	
	public float getFloatValue(String key, float defaultValue) {
		String value = getStringValue(key);
		if(value != null) {
			try {
				return Float.parseFloat(value);
			} catch(NumberFormatException e) {
				throw new RuntimeException("@UserSettings.getFloatValue / bad properties value format in file '" + path + "', key='" + key + "', value='" + value + "'");
			}
		}
		return defaultValue;
	}
}
