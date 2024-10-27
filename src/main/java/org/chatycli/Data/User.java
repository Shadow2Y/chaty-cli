package org.chatycli.Data;

public class User {
    public String username;
    public int id;
    private String password;
    public User(String username, int id, String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
