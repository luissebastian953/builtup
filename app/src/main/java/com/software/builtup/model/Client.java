package com.software.builtup.model;

import java.io.Serializable;

public class Client implements Serializable {

    private String ClientID;
    private String ClientUsername;
    private String ClientPassword;
    private String ClientName;
    private String ClientPhone;
    private String ClientAddress;

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getClientUsername() {
        return ClientUsername;
    }

    public void setClientUsername(String clientUsername) {
        ClientUsername = clientUsername;
    }

    public String getClientPassword() {
        return ClientPassword;
    }

    public void setClientPassword(String clientPassword) {
        ClientPassword = clientPassword;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public String getClientAddress() {
        return ClientAddress;
    }

    public void setClientAddress(String clientAddress) {
        ClientAddress = clientAddress;
    }

    public Client(String clientID, String clientUsername, String clientPassword, String clientName, String clientPhone, String clientAddress) {
        this.ClientID = clientID;
        this.ClientUsername = clientUsername;
        this.ClientPassword = clientPassword;
        this.ClientName = clientName;
        this.ClientPhone = clientPhone;
        this.ClientAddress = clientAddress;
    }

    public Client(){

    }
}
