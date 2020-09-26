package com.software.builtup.model;

import java.io.Serializable;

public class FeedBack implements Serializable {

    public String FeedBackID;
    public String ArchitectID;
    public String ClientID;
    public String FeedBackMessage;


    public FeedBack(String feedBackID, String architectID, String clientID, String feedBackMessage) {
        FeedBackID = feedBackID;
        ArchitectID = architectID;
        ClientID = clientID;
        FeedBackMessage = feedBackMessage;
    }

    public FeedBack(){

    }

    public String getFeedBackID() {
        return FeedBackID;
    }

    public void setFeedBackID(String feedBackID) {
        FeedBackID = feedBackID;
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

    public String getFeedBackMessage() {
        return FeedBackMessage;
    }

    public void setFeedBackMessage(String feedBackMessage) {
        FeedBackMessage = feedBackMessage;
    }

}
