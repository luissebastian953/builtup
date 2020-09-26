package com.software.builtup.model;

import java.io.Serializable;

public class ChatSystem implements Serializable {

    public ChatSystem(){

    }

    public ChatSystem(String chatID, String architectID, String clientID, String architectName, String architectFieldFocus, String clientName) {
        ChatID = chatID;
        ArchitectID = architectID;
        ClientID = clientID;
        ArchitectName = architectName;
        ArchitectFieldFocus = architectFieldFocus;
        ClientName = clientName;
    }

    private String ChatID;
    private String ArchitectID;
    private String ClientID;
    private String ArchitectName;
    private String ArchitectFieldFocus;
    private String ClientName;

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getArchitectName() {
        return ArchitectName;
    }

    public void setArchitectName(String architectName) {
        ArchitectName = architectName;
    }

    public String getArchitectFieldFocus() {
        return ArchitectFieldFocus;
    }

    public void setArchitectFieldFocus(String architectFieldFocus) {
        ArchitectFieldFocus = architectFieldFocus;
    }

    public String getChatID() {
        return ChatID;
    }

    public void setChatID(String chatID) {
        ChatID = chatID;
    }

    public String getArchitectID() {
        return ArchitectID;
    }

    public void setArchitectID(String architectID) {
        ArchitectID = architectID;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }


}
