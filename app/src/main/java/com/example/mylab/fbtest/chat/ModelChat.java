package com.example.mylab.fbtest.chat;

/**
 * Created by Abins Shaji on 16/01/18.
 */

public class ModelChat {
    private String msgText;
    private String msgUser;

    public ModelChat() {
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgUser() {
        return msgUser;
    }

    public void setMsgUser(String msgUser) {
        this.msgUser = msgUser;
    }

    public ModelChat(String msgText, String msgUser) {
        this.msgText = msgText;
        this.msgUser = msgUser;
    }
}
