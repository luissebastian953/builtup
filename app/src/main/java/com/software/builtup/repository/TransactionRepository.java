package com.software.builtup.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.software.builtup.model.Architect;
import com.software.builtup.model.Client;
import com.software.builtup.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionRepository extends DbConnect {

    public TransactionRepository(Context context) {
        super(context);
    }

//    public TransactionModel insertTransaction (TransactionModel transaction)
//    {
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues value = new ContentValues();
//        value.put("TransactionID", transaction.getTransactionID());
//        value.put("RequestMessage", transaction.getRequestMessage());
//        value.put("TransactionStatus", transaction.getTransactionStatus());
//        value.put("ConstructionType", transaction.getConstructionType());
//        value.put("ClientID", transaction.getClientID());
//        value.put("ArchitectID", transaction.getArchitectID());
//        database.insert("MsArchitect", null, value);
//        return transaction;
//    }

    //overload
    public boolean ValidTransaction(String trArchitectID, String trClientID){
        String completed = "%Completed%";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM TrTransaction " +
                        "WHERE ClientID = \"" + trClientID + "\" " +
                        "AND ArchitectID = \"" + trArchitectID + "\" "+
                        "AND TransactionStatus NOT LIKE \""+ completed +"\""
                , null);
        if(!cursor.moveToFirst())return true;   // not found
        else return false;  // found
    }



    public List<TransactionModel> getAllClientListPending(Client client){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String pending = "Pending";
        String accepted = "Accepted";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uarchitect.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ClientID = \""+ client.getClientID() +"\" AND ulist.TransactionStatus = \""+ pending +"\"",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();

                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(client.getClientName());
                transactionModel.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModelList.add(transactionModel);

                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }


    public List<TransactionModel> getAllClientListAccepted(Client client){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String pending = "Pending";
        String accepted = "Accepted";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uarchitect.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ClientID = \""+ client.getClientID() +"\" AND ulist.TransactionStatus LIKE '%"+ accepted +"%'",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();

                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(client.getClientName());
                transactionModel.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModelList.add(transactionModel);

                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }


    public List<TransactionModel> getAllClientListPendingArchitectInbox(Architect architect){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String pending = "Pending";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uclient.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ArchitectID = \""+ architect.getArchitectID() +"\" AND ulist.TransactionStatus = \""+ pending +"\"",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModel.setArchitectName(architect.getArchitectName());
                transactionModelList.add(transactionModel);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }

    public List<TransactionModel> getAllCurrentTransactionArchitect(Architect architect){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String accepted = "Accepted";
        String acceptedCurrent = "Accepted-Current";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uclient.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ArchitectID = \""+ architect.getArchitectID() +"\" AND ulist.TransactionStatus = \""+ acceptedCurrent +"\" " +
                "OR ulist.TransactionStatus = \""+ accepted +"\"",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModel.setArchitectName(architect.getArchitectName());
                transactionModelList.add(transactionModel);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }

    public List<TransactionModel> getInboxNotified(Client client){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String declined = "Declined";
        String accepted = "Accepted";
        String completed = "Completed";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uarchitect.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ClientID = \""+ client.getClientID() +"\" AND ulist.TransactionStatus IN (\""+ accepted +"\", \""+ declined +"\", \""+ completed +"\")",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(client.getClientName());
                transactionModel.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModelList.add(transactionModel);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }

    public List<TransactionModel> getAllClientListHistory(Client client){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String completed = "Completed";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uarchitect.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ClientID = \""+ client.getClientID() +"\" AND ulist.TransactionStatus LIKE '%"+ completed +"%'",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(client.getClientName());
                transactionModel.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModelList.add(transactionModel);

                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }

    public List<TransactionModel> getAllArchitectListHistory(Architect architect){

        List<TransactionModel> transactionModelList = new ArrayList<>();
        String completed = "Completed";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "TransactionID, " +
                        "uclient.ClientID AS [ClientID], " +
                        "uarchitect.ArchitectID AS [ArchitectID] , " +
                        "uclient.Name AS [Name], " +
                        "uarchitect.Organization AS [Organization], " +
                        "RequestMessage, " +
                        "TransactionStatus, " +
                        "ConstructionType, " +
                        "Budget " +
                        "FROM TrTransaction ulist " +
                        "INNER JOIN MsClient uclient " +
                        "ON ulist.ClientID = uclient.ClientID " +
                        "INNER JOIN MsArchitect uarchitect " +
                        "ON ulist.ArchitectID = uarchitect.ArchitectID " +
                        "WHERE ulist.ArchitectID = \""+ architect.getArchitectID() +"\" AND ulist.TransactionStatus = \""+ completed +"\" "+
                        "OR ulist.TransactionStatus LIKE '%"+ completed +"%' ",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionID(cursor.getString(cursor.getColumnIndex("TransactionID")));
                transactionModel.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                transactionModel.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                transactionModel.setClientName(cursor.getString(cursor.getColumnIndex("Name")));
                transactionModel.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                transactionModel.setRequestMessage(cursor.getString(cursor.getColumnIndex("RequestMessage")));
                transactionModel.setTransactionStatus(cursor.getString(cursor.getColumnIndex("TransactionStatus")));
                transactionModel.setConstructionType(cursor.getString(cursor.getColumnIndex("ConstructionType")));
                transactionModel.setBudget(cursor.getString(cursor.getColumnIndex("Budget")));
                transactionModel.setArchitectName(architect.getArchitectName());
                transactionModelList.add(transactionModel);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return transactionModelList;
    }


    public String insertTransaction (TransactionModel transaction) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("TransactionID", transaction.getTransactionID());
        value.put("ClientID", transaction.getClientID());
        value.put("ArchitectID", transaction.getArchitectID());
        value.put("RequestMessage", transaction.getRequestMessage());
        value.put("TransactionStatus", transaction.getTransactionStatus());
        value.put("ConstructionType", transaction.getConstructionType());
        value.put("Budget", transaction.getBudget());
        db.insert("TrTransaction", null, value);

        return transaction.getTransactionID();
    }

    public void UpdateTransaction(TransactionModel transactionModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("TransactionID", transactionModel.getTransactionID());
        value.put("ClientID", transactionModel.getClientID());
        value.put("ArchitectID", transactionModel.getArchitectID());
        value.put("RequestMessage", transactionModel.getRequestMessage());
        value.put("TransactionStatus", transactionModel.getTransactionStatus());
        value.put("ConstructionType", transactionModel.getConstructionType());
        value.put("Budget", transactionModel.getBudget());
        db.update("TrTransaction",
                value,
                "TransactionID = \""+ transactionModel.getTransactionID() + "\"",
                null);
    }

    public void CancelTransaction(String TransactionID){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TrTransaction",
                "TransactionID = \""+ TransactionID + "\"",
                null);

    }

    public void RemovePendingTransaction(String ClientID){

        String pendingStatus = "Pending";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TrTransaction",
                        "ClientID = \""+ ClientID + "\" "+
                "AND TransactionStatus = \""+ pendingStatus +"\"",
                null);

    }


    public String IDEncoder(String ClientName, String ArchitectName, String ConstructionType){
        String firstEncode = ClientName.charAt(0) + ClientName.charAt(1) + "-";
        String secondEncode = ArchitectName.charAt(0) + ArchitectName.substring(1) + "-";
        String thirdEncode = ConstructionType.charAt(0) + ConstructionType.charAt(1) + "-";
        String fourthEncode = "";
        Random rand = new Random();
        for(int i=0;i<3;i++){
            fourthEncode = fourthEncode + rand.nextInt(2);
        }int enSwitch=1;
        for(int i=0;i<5;i++){
            if(enSwitch == 0){
                fourthEncode = fourthEncode + (char) (rand.nextInt(26) + 'A');
                enSwitch = 1;
            }else{
                fourthEncode = fourthEncode + (char) (rand.nextInt(26) + 'a');
                enSwitch = 0;
            }
        }
        String encoded = "TR" + firstEncode + secondEncode + thirdEncode + fourthEncode;
        return encoded;
    }

}
