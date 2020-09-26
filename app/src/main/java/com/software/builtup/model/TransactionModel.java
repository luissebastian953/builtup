package com.software.builtup.model;

import java.io.Serializable;

public class TransactionModel implements Serializable {

    private String TransactionID;
    private String RequestMessage;
    private String TransactionStatus;
    private String ConstructionType;
    private String ClientID;
    private String ArchitectID;
    private String ArchitectName;
    private String ArchitectOrganization;
    private String Budget;
    private String ClientName;

    public TransactionModel(String transactionID, String requestMessage, String transactionStatus, String constructionType, String clientID, String architectID, String architectName, String architectOrganization, String budget, String clientName) {
        TransactionID = transactionID;
        RequestMessage = requestMessage;
        TransactionStatus = transactionStatus;
        ConstructionType = constructionType;
        ClientID = clientID;
        ArchitectID = architectID;
        ArchitectName = architectName;
        ArchitectOrganization = architectOrganization;
        Budget = budget;
        ClientName = clientName;
    }

    public TransactionModel(){

    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }
    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getRequestMessage() {
        return RequestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        RequestMessage = requestMessage;
    }

    public String getTransactionStatus() {
        return TransactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        TransactionStatus = transactionStatus;
    }

    public String getConstructionType() {
        return ConstructionType;
    }

    public void setConstructionType(String constructionType) {
        ConstructionType = constructionType;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getArchitectID() {
        return ArchitectID;
    }

    public void setArchitectID(String architectID) {
        ArchitectID = architectID;
    }

    public String getArchitectName() {
        return ArchitectName;
    }

    public void setArchitectName(String architectName) {
        ArchitectName = architectName;
    }

    public String getArchitectOrganization() {
        return ArchitectOrganization;
    }

    public void setArchitectOrganization(String architectOrganization) {
        ArchitectOrganization = architectOrganization;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }


}
