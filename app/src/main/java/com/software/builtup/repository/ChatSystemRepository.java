package com.software.builtup.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.software.builtup.model.Architect;
import com.software.builtup.model.ChatSystem;
import com.software.builtup.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatSystemRepository extends DbConnect{

    public ChatSystemRepository(Context context) {
        super(context);
    }

    public void insertChatSystem(ChatSystem chatSystem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ChatID", chatSystem.getChatID());
        values.put("ClientID", chatSystem.getClientID());
        values.put("ArchitectID", chatSystem.getArchitectID());
        db.insert("TrChat", null, values);
    }

    public boolean valideChatExisted(String ArchitectID, String ClientID){
        ChatSystem chatSystem = new ChatSystem();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * "+
                        "FROM TrChat uchat "+
                        "WHERE ClientID = \""+ ClientID +"\" AND ArchitectID = \""+ ArchitectID +"\"",
                null);
        if(!cursor.moveToFirst()){
            return true;
        }else return false;
    }

    public List<ChatSystem> getAllChatSystemByClient(Client client){
        List<ChatSystem> chatSystemList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                "ChatID, "+
                "uchat.ArchitectID AS [ArchitectID], "+
                "ClientID, "+
                "uarchitect.Name AS [Name], "+
                "uarchitect.FieldFocus AS [FieldFocus] "+
                "FROM TrChat uchat "+
                "INNER JOIN MsArchitect uarchitect "+
                "ON uchat.ArchitectID = uarchitect.ArchitectID "+
                "WHERE ClientID = \""+ client.getClientID() +"\"",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                ChatSystem chatSystem = new ChatSystem();
                chatSystem.setChatID(cursor.getString(cursor.getColumnIndex("ChatID")));
                chatSystem.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                chatSystem.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                chatSystem.setClientName(client.getClientName());
                chatSystem.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                chatSystem.setArchitectFieldFocus(cursor.getString(cursor.getColumnIndex("FieldFocus")));
                chatSystemList.add(chatSystem);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return chatSystemList;
    }


    public List<ChatSystem> getAllChatSystemByArchitect(Architect architect){
        List<ChatSystem> chatSystemList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "+
                        "ChatID, "+
                        "uchat.ClientID AS [ClientID], "+
                        "ArchitectID, "+
                        "uclient.Name AS [Name] "+
                        "FROM TrChat uchat "+
                        "INNER JOIN MsClient uclient "+
                        "ON uchat.ClientID = uclient.ClientID "+
                        "WHERE ArchitectID = \""+ architect.getArchitectID() +"\"",
                null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                ChatSystem chatSystem = new ChatSystem();
                chatSystem.setChatID(cursor.getString(cursor.getColumnIndex("ChatID")));
                chatSystem.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                chatSystem.setClientID(cursor.getString(cursor.getColumnIndex("ClientID")));
                chatSystem.setArchitectName(architect.getArchitectName());
                chatSystem.setClientName(cursor.getString(cursor.getColumnIndex("Name")));
                chatSystemList.add(chatSystem);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return chatSystemList;
    }

    public String IDEncoder(String ArchitectName, String ClientName){
        String firstEncode = ArchitectName.charAt(0) + ArchitectName.charAt(1) +
                ArchitectName.substring(0, ArchitectName.length() - 3) + ArchitectName.substring(1, ArchitectName.length() - 2)+
                ArchitectName.charAt(ArchitectName.length()-1) + ArchitectName.length()+ "-";

        String secondEncode = ClientName.charAt(0) + ClientName.charAt(1) +
                ClientName.substring(0, ClientName.length() - 3) + ClientName.substring(1, ClientName.length() - 2)+
                ClientName.charAt(ClientName.length()-1) + ClientName.length()+ "-";

        String thirdEncode = "", fourthEncode = "";
        int codeSwitcher = 0;
        Random rand = new Random();
        for(int i=0;i<5;i++){
            if(codeSwitcher == 0){
                fourthEncode = fourthEncode + rand.nextInt(2);
                codeSwitcher = 1;
            }else{
                thirdEncode = thirdEncode + rand.nextInt(2);
                codeSwitcher = 0;
            }
        }int enSwitch=0;
        for(int i=0; i<(ArchitectName.length() + ClientName.length()) / 2; i++){
            if(enSwitch == 0){
                if(codeSwitcher == 0){
                    fourthEncode = fourthEncode + (char) (rand.nextInt(26) + 'A');
                    codeSwitcher = 1;
                }else{
                    thirdEncode = thirdEncode + (char) (rand.nextInt(26) + 'A');
                    codeSwitcher = 0;
                }enSwitch = 1;
            }else{
                if(codeSwitcher == 0){
                    fourthEncode = fourthEncode + (char) (rand.nextInt(26) + 'a');
                    codeSwitcher = 1;
                }else{
                    thirdEncode = thirdEncode + (char) (rand.nextInt(26) + 'a');
                    codeSwitcher = 0;
                }enSwitch = 0;
            }
        }
        String encoded = firstEncode + secondEncode + thirdEncode + fourthEncode;
        return encoded;
    }
}
