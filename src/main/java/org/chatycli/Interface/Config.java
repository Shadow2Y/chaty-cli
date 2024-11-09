package org.chatycli.Interface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    public static final String STORAGE_FILE = "storage.properties";
    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load configuration", ex);
        }
    }

    public static String getStorageFile() {
        return properties.getProperty(STORAGE_FILE);
    }
    // Access a configuration value by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Optionally, provide specific methods for commonly used keys
    public static String getServerHost() {
        return properties.getProperty("server.host");
    }

    public static int getServerPort() {
        return Integer.parseInt(properties.getProperty("server.port"));
    }

}
