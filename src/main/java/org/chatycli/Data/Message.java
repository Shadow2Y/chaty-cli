package org.chatycli.Data;

import java.util.Date;

public class Message {
    final private User sender;
    final private User receiver;
    final private String messageText;
    final private Date date;

    public Message(User sender, User receiver, String message, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = message;
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Date getDate() {
        return date;
    }

    public String getMessageText() {
        return messageText;
    }
}
