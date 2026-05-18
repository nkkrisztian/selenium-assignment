package utils;

import java.util.Properties;
import java.io.InputStream;

public class Config {
    private static Properties properties;

    static {
        // Use the ClassLoader to find the file in the 'resources' folder
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties in classpath");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

	public static String getUrl(String pathKey) {
        String activeEnv = properties.getProperty("env", "public"); 
        String baseDomain = properties.getProperty("domain." + activeEnv);
        String pagePath = properties.getProperty("path." + pathKey);
        
        return baseDomain + pagePath;
    }
}
