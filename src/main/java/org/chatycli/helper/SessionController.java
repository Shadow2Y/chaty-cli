package org.chatycli.helper;

import org.chatycli.Data.Session;

import java.util.Date;

public class SessionController {
    public static Session session = null;
    public static boolean isLoggedIn() {
        if(session!=null && Storage.isSessionValid()) {
            session = Storage.getStoredSession();
            if(session != null && session.getExpiryDate().before(new Date())) {
                return true;
            }
        }
        return false;
    }

    public static void loginUser(String username, String password) {
        String storedPassword = Storage.getPassword(username);
        if (storedPassword.equals(password)) {
            System.out.println("Logged in as :: '" + username+"'");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public static String getUsername() {
        return session.getUser().getUsername();
    }
    public static void logoutUser() {}
}
