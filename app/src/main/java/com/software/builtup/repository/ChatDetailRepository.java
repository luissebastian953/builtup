package com.software.builtup.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.software.builtup.model.ChatDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatDetailRepository extends DbConnect {
    public ChatDetailRepository(Context context) {
        super(context);
    }

    public void insertChatDetail(ChatDetail chatDetail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ChatDetailID", chatDetail.getChatDetailID());
        values.put("ChatID", chatDetail.getChatID());
        values.put("CurrentHolder", chatDetail.getCurrentHolder());
        values.put("ChatMessage", chatDetail.getMessageText());
        db.insert("TrChatDetail", null, values);
    }

    public List<ChatDetail> getAllChatDetail(String ChatID){
        List<ChatDetail> chatDetailList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TrChatDetail WHERE ChatID = \""+ ChatID +"\"", null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                ChatDetail chatDetail = new ChatDetail();
                chatDetail.setChatDetailID(cursor.getString(cursor.getColumnIndex("ChatDetailID")));
                chatDetail.setChatID(cursor.getString(cursor.getColumnIndex("ChatID")));
                chatDetail.setCurrentHolder(cursor.getString(cursor.getColumnIndex("CurrentHolder")));
                chatDetail.setMessageText(cursor.getString(cursor.getColumnIndex("ChatMessage")));
                chatDetailList.add(chatDetail);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return chatDetailList;
    }

    public String IDEncoder(String chatID, String textMessage, String currentHolder){
        String firstEncode = chatID.charAt(0) + textMessage.charAt(1) + chatID.charAt(chatID.length()-1) + textMessage.charAt(textMessage.length()-2)+ "-";
        String secondEncode = chatID.charAt(1) + textMessage.charAt(1) + "-";
        String thirdEncode = chatID.substring(0, chatID.length()-2);
        if(textMessage.length() < 5) thirdEncode = thirdEncode + textMessage;
        else thirdEncode = thirdEncode + textMessage.substring(0, textMessage.length() % 5);
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
        String encoded = "";
        if(currentHolder == "Client") encoded = "CLI" + firstEncode + secondEncode + thirdEncode + fourthEncode;
        else encoded = "ARCH" + firstEncode + secondEncode + thirdEncode + fourthEncode;
        return encoded;
    }

}
