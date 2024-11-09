package org.chatycli.Data;

import java.util.Date;

public class Session {
    private User user;
    private Date expiryDate;

    public Session() {
    }
    public Session(User user, Date expiryDate) {
        this.user = user;
        this.expiryDate = expiryDate;
    }
    public User getUser() {
        return user;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
}
