package com.software.builtup.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.software.builtup.model.Architect;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArchitectRepository extends DbConnect{

    public ArchitectRepository(Context context) {
        super(context);
    }

    public Architect ValidArchitectUser(String architectUsername, String architectPassword){

        Architect architect = new Architect();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect WHERE Username = \"" +
                        architectUsername + "\" AND Password = \"" +
                        architectPassword + "\""
                , null);

        if(!cursor.moveToFirst())
            return null;
        else
        {
            architect.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
            architect.setArchitectUsername(cursor.getString(cursor.getColumnIndex("Username")));
            architect.setArchitectPassword(cursor.getString(cursor.getColumnIndex("Password")));
            architect.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
            architect.setArchitectPhone(cursor.getString(cursor.getColumnIndex("Phone")));
            architect.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
            architect.setArchitectFieldFocus(cursor.getString(cursor.getColumnIndex("FieldFocus")));
            architect.setArchitectAddress(cursor.getString(cursor.getColumnIndex("Address")));

            return architect;
        }
    }

    //overload
    public boolean ValidArchitectUser(String architectUsername){
        Architect architect = new Architect();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect WHERE Username = \"" +
                        architectUsername + "\""
                , null);
        if(!cursor.moveToFirst())return true;   // not found
        else return false;  // found
    }

    public List<Architect> getAllBuildHouseArchitect(){

        String buildHouseType = "Build House";
        SQLiteDatabase db = this.getWritableDatabase();
        List<Architect> architectBuildHouseList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect WHERE FieldFocus = \"" + buildHouseType + "\""
                , null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                Architect architectBH = new Architect();

                architectBH.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                architectBH.setArchitectUsername(cursor.getString(cursor.getColumnIndex("Username")));
                architectBH.setArchitectPassword(cursor.getString(cursor.getColumnIndex("Password")));
                architectBH.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                architectBH.setArchitectPhone(cursor.getString(cursor.getColumnIndex("Phone")));
                architectBH.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                architectBH.setArchitectFieldFocus(cursor.getString(cursor.getColumnIndex("FieldFocus")));
                architectBH.setArchitectAddress(cursor.getString(cursor.getColumnIndex("Address")));

                architectBuildHouseList.add(architectBH);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();

        }
        return architectBuildHouseList;
    }


    public List<Architect> getAllRenovationArchitect(){

        String RenovationType = "Renovation";
        SQLiteDatabase db = this.getWritableDatabase();
        List<Architect> architectRenovationList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect WHERE FieldFocus = \"" +
                        RenovationType + "\""
                , null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                Architect architectBH = new Architect();

                architectBH.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                architectBH.setArchitectUsername(cursor.getString(cursor.getColumnIndex("Username")));
                architectBH.setArchitectPassword(cursor.getString(cursor.getColumnIndex("Password")));
                architectBH.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                architectBH.setArchitectPhone(cursor.getString(cursor.getColumnIndex("Phone")));
                architectBH.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                architectBH.setArchitectFieldFocus(cursor.getString(cursor.getColumnIndex("FieldFocus")));
                architectBH.setArchitectAddress(cursor.getString(cursor.getColumnIndex("Address")));

                architectRenovationList.add(architectBH);

                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return architectRenovationList;
    }


    public List<Architect> getAllSearchArchitect(String search){

        SQLiteDatabase db = this.getWritableDatabase();
        List<Architect> architectRenovationList = new ArrayList<>();
        String modifiedSearch = search;
        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect WHERE "+
                        "Name LIKE '%" + modifiedSearch + "%' OR "+
                        "Phone LIKE '%" + modifiedSearch + "%' OR "+
                        "Organization LIKE '%" + modifiedSearch + "%' OR "+
                        "FieldFocus LIKE '%" + modifiedSearch + "%' OR "+
                        "Address LIKE '%" + modifiedSearch + "%' "
                , null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Architect architectBH = new Architect();
                architectBH.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                architectBH.setArchitectUsername(cursor.getString(cursor.getColumnIndex("Username")));
                architectBH.setArchitectPassword(cursor.getString(cursor.getColumnIndex("Password")));
                architectBH.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                architectBH.setArchitectPhone(cursor.getString(cursor.getColumnIndex("Phone")));
                architectBH.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                architectBH.setArchitectFieldFocus(cursor.getString(cursor.getColumnIndex("FieldFocus")));
                architectBH.setArchitectAddress(cursor.getString(cursor.getColumnIndex("Address")));
                architectRenovationList.add(architectBH);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return architectRenovationList;
    }


    public boolean validateSearchedArchitect(String search){

        SQLiteDatabase db = this.getWritableDatabase();
        String modifiedSearch = search;
        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect WHERE "+
                        "Name LIKE '%" + modifiedSearch + "%' OR "+
                        "Phone LIKE '%" + modifiedSearch + "%' OR "+
                        "Organization LIKE '%" + modifiedSearch + "%' OR "+
                        "FieldFocus LIKE '%" + modifiedSearch + "%' OR "+
                        "Address LIKE '%" + modifiedSearch + "%' "
                , null);
        if(cursor.moveToFirst()){
            return true;
        }else return false;
    }


    public List<Architect> getAllArchitect(){

        SQLiteDatabase db = this.getWritableDatabase();
        List<Architect> architectRenovationList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM MsArchitect"
                , null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                Architect architectBH = new Architect();
                architectBH.setArchitectID(cursor.getString(cursor.getColumnIndex("ArchitectID")));
                architectBH.setArchitectUsername(cursor.getString(cursor.getColumnIndex("Username")));
                architectBH.setArchitectPassword(cursor.getString(cursor.getColumnIndex("Password")));
                architectBH.setArchitectName(cursor.getString(cursor.getColumnIndex("Name")));
                architectBH.setArchitectPhone(cursor.getString(cursor.getColumnIndex("Phone")));
                architectBH.setArchitectOrganization(cursor.getString(cursor.getColumnIndex("Organization")));
                architectBH.setArchitectFieldFocus(cursor.getString(cursor.getColumnIndex("FieldFocus")));
                architectBH.setArchitectAddress(cursor.getString(cursor.getColumnIndex("Address")));
                architectRenovationList.add(architectBH);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }return architectRenovationList;
    }



    public Architect insertRegisterArchitect (Architect architect) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("ArchitectID", architect.getArchitectID());
        value.put("Username", architect.getArchitectUsername());
        value.put("Password", architect.getArchitectPassword());
        value.put("Name", architect.getArchitectName());
        value.put("Phone", architect.getArchitectPhone());
        value.put("Organization", architect.getArchitectOrganization());
        value.put("FieldFocus", architect.getArchitectFieldFocus());
        value.put("Address", architect.getArchitectAddress());
        database.insert("MsArchitect", null, value);
        return architect;
    }

    //overload
//    public void insertRegisterArchitect (String ID, String Username, String Password, String Name, String Phone, String Organization, String FieldFocus, String Address) {
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues value = new ContentValues();
//        value.put("ArchitectID", ID);
//        value.put("Username", Username);
//        value.put("Password", Password);
//        value.put("Name", Name);
//        value.put("Phone", Phone);
//        value.put("Organization", Organization);
//        value.put("FocusField", FieldFocus);
//        value.put("Address", Address);
//        database.insert("MsArchitect", null, value);
//    }

    public void UpdateArchitect(Architect architect){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("ArchitectID", architect.getArchitectID());
        value.put("Username", architect.getArchitectUsername());
        value.put("Password", architect.getArchitectPassword());
        value.put("Name", architect.getArchitectName());
        value.put("Phone", architect.getArchitectPhone());
        value.put("Organization", architect.getArchitectOrganization());
        value.put("FieldFocus", architect.getArchitectFieldFocus());
        value.put("Address", architect.getArchitectAddress());
        db.update("MsArchitect",
                value,
                "ArchitectID = \""+ architect.getArchitectID() + "\"",
                null);
    }


    public String IDEncoder(String username, String password, String phone, String organization){

        String firstEncode = username.charAt(0) + username.charAt(1) + "-";
        String secondEncode = password.charAt(0) + password.substring(password.length() - 1) + "-";
        String thirdEncode = phone.charAt(0) + phone.charAt(1) + phone.substring(phone.length()-2) + "-";
        String fourthEncode = organization.charAt(0) + organization.substring(organization.length()-1);
        String fifthEncode = "";
        Random rand = new Random();
        for(int i=0;i<3;i++){
            fifthEncode = fifthEncode + rand.nextInt(2);
        }int enSwitch=1;
        for(int i=0;i<5;i++){
            if(enSwitch == 0){
                fifthEncode = fifthEncode + (char) (rand.nextInt(26) + 'A');
                enSwitch = 1;
            }else{
                fifthEncode = fifthEncode + (char) (rand.nextInt(26) + 'a');
                enSwitch = 0;
            }
        }
        String encoded = "AR" + firstEncode + secondEncode + thirdEncode + fourthEncode + fifthEncode;
        return encoded;
    }

}
