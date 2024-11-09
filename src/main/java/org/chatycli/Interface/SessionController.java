package org.chatycli.Interface;

import org.chatycli.Data.Session;
import org.chatycli.Data.User;

import java.util.Date;

public class SessionController {
    public static Session session = null;
    public static boolean isLoggedIn() {
        session = Storage.getStoredSession();
        if(session != null && session.getExpiryDate().before(new Date())) {
            return true;
        }
        return false;
    }

    public static void loginUser(String username, String password) {
        String storedPassword = Storage.getPassword(username);
        if (storedPassword.equals(password)) {
            System.out.println("Logged in as :: '" + username+"'");
            Date expiryDate = new Date(System.currentTimeMillis()+10000);
            session = new Session(new User(username,0,"shadowy"),expiryDate);
            session = Storage.getStoredSession();
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public static String getUsername() {
        return session.getUser().getUsername();
    }
    public static void logoutUser() {}
}
