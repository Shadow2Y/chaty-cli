package org.chatycli.helper;

import org.chatycli.Data.Session;

public class Storage {
    // Single instance of the class
    private static Storage instance;

    // Private constructor to prevent instantiation
    private Storage() {
        // Initialization code, like setting up file paths, databases, etc.
    }

    public static boolean isSessionValid() {
        return false;
    }

    public static Session getStoredSession() {
        return null;
    }

    // Method to provide access to the single instance
    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public static String getPassword(String username) {
        return "";
    }
}
