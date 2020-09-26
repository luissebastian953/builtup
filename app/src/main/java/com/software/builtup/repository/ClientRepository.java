package com.software.builtup.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.software.builtup.model.Client;

import java.util.Random;

public class ClientRepository extends DbConnect{

    public ClientRepository(Context context) {
        super(context);
    }

    public Client ValidClientUser(String clientUsername, String clientPassword){

        Client client = new Client();
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT * FROM MsClient WHERE Username = \"" + clientUsername + "\" AND Password = \"" + clientPassword + "\"";
        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsClient WHERE Username = \"" +
                        clientUsername + "\" AND Password = \"" +
                        clientPassword + "\""
                , null);

        if(!cursor.moveToFirst()) return null;
        else
        {
            client.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
            client.setClientUsername(cursor.getString(cursor.getColumnIndex("Username")));
            client.setClientPassword(cursor.getString(cursor.getColumnIndex("Password")));
            client.setClientName(cursor.getString(cursor.getColumnIndex("Name")));
            client.setClientPhone(cursor.getString(cursor.getColumnIndex("Phone")));
            client.setClientAddress(cursor.getString(cursor.getColumnIndex("Address")));
            return client;
        }
    }

    //overload
    public boolean ValidClientUser(String clientUsername){
        Client client = new Client();
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT * FROM MsClient WHERE Username = \"" + clientUsername + "\" AND Password = \"" + clientPassword + "\"";
        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsClient WHERE Username = \"" +
                        clientUsername + "\""
                , null);
        if(!cursor.moveToFirst())return true;   // not found
        else return false;  // found
    }


    public Client insertRegisterClient (Client client)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("ClientID", client.getClientID());
        value.put("Username", client.getClientUsername());
        value.put("Password", client.getClientPassword());
        value.put("Name", client.getClientName());
        value.put("Phone", client.getClientPhone());
        value.put("Address", client.getClientAddress());
        database.insert("MsClient", null, value);
        return client;
    }

    public void UpdateClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("ClientID", client.getClientID());
        value.put("Username", client.getClientUsername());
        value.put("Password", client.getClientPassword());
        value.put("Name", client.getClientName());
        value.put("Phone", client.getClientPhone());
        value.put("Address", client.getClientAddress());
        db.update("MsClient",
                value,
                "ClientID = \""+ client.getClientID() + "\"",
                null);
    }


    public String IDEncoder(String username, String password, String phone){

        String firstEncode = username.charAt(0) + username.charAt(1) + "-";
        String secondEncode = password.charAt(0) + password.substring(password.length() - 1) + "-";
        String thirdEncode = phone.charAt(0) + phone.substring(phone.length()-2);
        String fourthEncode = "";

        Random rand = new Random();
        for(int i=0;i<3;i++){
            fourthEncode = fourthEncode + rand.nextInt(2);
        }int enSwitch=0;
        for(int i=0;i<5;i++){
            if(enSwitch == 0){
                fourthEncode = fourthEncode + (char) (rand.nextInt(26) + 'A');
                enSwitch = 1;
            }else{
                fourthEncode = fourthEncode + (char) (rand.nextInt(26) + 'a');
                enSwitch = 0;
            }
        }
        String encoded = "CL" + firstEncode + secondEncode + thirdEncode + fourthEncode;
        return encoded;
    }
}
