package org.chatycli.Data;

public class DataModel {
    private User user;
    private Session session;
    private Message message;

    public DataModel() {}
    public DataModel(User user, Session session, Message message) {
        this.user = user;
        this.session = session;
        this.message = message;
    }
    public User getUser() {
        return user;
    }
    public Session getSession() {
        return session;
    }
    public Message getMessage() {
        return message;
    }
}
