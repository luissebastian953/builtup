package com.software.builtup.model;

import java.io.Serializable;

public class ChatDetail implements Serializable {

    public ChatDetail(){

    }

    public ChatDetail(String chatDetailID, String chatID, String messageText, String currentHolder) {
        ChatDetailID = chatDetailID;
        ChatID = chatID;
        MessageText = messageText;
        CurrentHolder = currentHolder;
    }

    private String ChatDetailID;
    private String ChatID;
    private String MessageText;
    private String CurrentHolder;

    public String getChatDetailID() {
        return ChatDetailID;
    }

    public void setChatDetailID(String chatDetailID) {
        ChatDetailID = chatDetailID;
    }

    public String getChatID() {
        return ChatID;
    }

    public void setChatID(String chatID) {
        ChatID = chatID;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public String getCurrentHolder() {
        return CurrentHolder;
    }

    public void setCurrentHolder(String currentHolder) {
        CurrentHolder = currentHolder;
    }

}
