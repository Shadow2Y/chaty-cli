package org.chatycli.Interface;

import org.chatycli.Data.Session;
import org.chatycli.Data.User;
import org.chatycli.NetworkClient;

import java.util.Date;
import java.util.Scanner;

public class SessionController {
    public static Session session = null;
    public static boolean isLoggedIn() {
        session = Storage.getStoredSession();
        if(session != null && session.getExpiryDate().before(new Date())) {
            return true;
        }
        return false;
    }

    public static void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username :: ");
        String username = scanner.nextLine();
        System.out.print("Password :: ");
        String password = scanner.nextLine();
        System.out.println(":: Logging into User session :: ");
        String storedPassword = Storage.getPassword(username);
        NetworkClient.loginUser(username,password);
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
