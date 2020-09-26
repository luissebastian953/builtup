package com.software.builtup.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.software.builtup.model.Client;
import com.software.builtup.model.FeedBack;

import java.util.Random;

public class FeedBackRepository extends DbConnect {

    public FeedBackRepository(Context context) {
        super(context);
    }

    public String insertTransaction (FeedBack feedBack) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("FeedBackID", feedBack.getFeedBackID());
        value.put("ClientID", feedBack.getClientID());
        value.put("ArchitectID", feedBack.getArchitectID());
        value.put("FeedBackMessage", feedBack.getFeedBackMessage());
        db.insert("TrTransaction", null, value);
        return feedBack.getFeedBackID();
    }

    public String IDEncoder(String ClientID, String ArchitectID, String FeedBackMessage ){

        String firstEncode = "";
        if(ArchitectID == "-"){
            firstEncode = ClientID.charAt(0) + ClientID.charAt(1) + ClientID.charAt(ClientID.length()-1) + "-";
        }else if(ClientID == "-"){
            firstEncode = ArchitectID.charAt(0) + ArchitectID.charAt(1) + ArchitectID.charAt(ArchitectID.length()-1) + "-";
        }
        String secondEncode = FeedBackMessage.charAt(0) + FeedBackMessage.charAt(1) + FeedBackMessage.substring(FeedBackMessage.length()-1) + "-";
        String thirdEncode = "";
        Random rand = new Random();
        for(int i=0;i<4;i++){
            thirdEncode = thirdEncode + rand.nextInt(2);
        }int enSwitch=1;
        for(int i=0;i<6;i++){
            if(enSwitch == 0){
                thirdEncode = thirdEncode + (char) (rand.nextInt(26) + 'A');
                enSwitch = 1;
            }else{
                thirdEncode = thirdEncode + (char) (rand.nextInt(26) + 'a');
                enSwitch = 0;
            }
        }
        String encoded = "FB" + firstEncode + secondEncode + thirdEncode;
        return encoded;
    }

}
