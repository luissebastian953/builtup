package com.software.builtup.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbConnect extends SQLiteOpenHelper {

    //public static final String DATABASE_LITE = "BuiltUpDB4.db";

    public DbConnect(Context context) {
        super(context, "BuiltUpDB11", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // DDL exec query
        db.execSQL("CREATE TABLE MsClient( "+
                "ClientID TEXT PRIMARY KEY NOT NULL,"+
                "Username TEXT NOT NULL,"+
                "Password TEXT NOT NULL,"+
                "Name TEXT NOT NULL,"+
                "Phone TEXT NOT NULL,"+
                "Address TEXT)");

        db.execSQL("CREATE TABLE MsArchitect( "+
                "ArchitectID TEXT PRIMARY KEY NOT NULL, "+
                "Username TEXT NOT NULL, "+
                "Password TEXT NOT NULL, "+
                "Name TEXT NOT NULL, "+
                "Phone TEXT NOT NULL, "+
                "Organization TEXT NOT NULL, "+
                "FieldFocus TEXT, "+
                "Address TEXT) ");

        db.execSQL("CREATE TABLE TrTransaction( "+
                "TransactionID TEXT PRIMARY KEY NOT NULL,"+
                "ClientID TEXT NOT NULL,"+
                "ArchitectID TEXT NOT NULL,"+
                "RequestMessage TEXT NOT NULL,"+
                "TransactionStatus TEXT NOT NULL,"+
                "ConstructionType TEXT,"+
                "Budget TEXT NOT NULL,"+
                "FOREIGN KEY(ClientID) REFERENCES MsClient(ClientID)," +
                "FOREIGN KEY(ArchitectID) REFERENCES MsArchitect(ArchitectID) )");

        db.execSQL("CREATE TABLE TrFeedBack( "+
                "FeedBackID TEXT PRIMARY KEY NOT NULL,"+
                "ClientID TEXT NOT NULL,"+
                "ArchitectID TEXT NOT NULL,"+
                "FeedBackMessage TEXT NOT NULL,"+
                "FOREIGN KEY(ClientID) REFERENCES MsClient(ClientID)," +
                "FOREIGN KEY(ArchitectID) REFERENCES MsArchitect(ArchitectID) )");


        db.execSQL("CREATE TABLE TrChat( "+
                "ChatID TEXT PRIMARY KEY NOT NULL,"+
                "ClientID TEXT NOT NULL,"+
                "ArchitectID TEXT NOT NULL,"+
                "FOREIGN KEY(ClientID) REFERENCES MsClient(ClientID)," +
                "FOREIGN KEY(ArchitectID) REFERENCES MsArchitect(ArchitectID) )");

        db.execSQL("CREATE TABLE TrChatDetail( "+
                "ChatDetailID TEXT PRIMARY KEY NOT NULL,"+
                "ChatID TEXT NOT NULL,"+
                "CurrentHolder TEXT NOT NULL,"+
                "ChatMessage TEXT NOT NULL,"+
                "FOREIGN KEY(ChatID) REFERENCES TrChat(ChatID) )");

//        db.execSQL("CREATE TABLE TrInbox( "+
//                "InboxID TEXT PRIMARY KEY NOT NULL,"+
//                "TransactionID TEXT NOT NULL,"+
//                "ClientID TEXT NOT NULL,"+
//                "ArchitectID TEXT NOT NULL,"+
//                "ApprovalMessage TEXT NOT NULL,"+
//                "TransactionApproval TEXT NOT NULL,"+
//                "FOREIGN KEY(TransactionID) REFERENCES TrTransaction(TransactionID)," +
//                "FOREIGN KEY(ClientID) REFERENCES MsClient(ClientID)," +
//                "FOREIGN KEY(ArchitectID) REFERENCES MsArchitect(ArchitectID) )");


        // dummy data
        db.execSQL("INSERT INTO MsClient VALUES" +
                "(\"CL1032\", \"eltonyeo@gmail.com\", \"eltonyeo45\", \"Elton Yeo\", \"0854949545\", \"Jl. Gunung Sahari 8 Blok GH7, Jakarta Utara\")");

        db.execSQL("INSERT INTO MsClient VALUES" +
                "(\"CL4384\", \"andreasaditya@gmail.com\", \"andreas45\", \"Andreas Aditya\", \"0854949545\", \"Bekasi Utara, Jawabarat\")");

        String architectData = "INSERT INTO MsArchitect VALUES" +
                "(\"AR1432\", \"crlraditya@gmail.com\", \"crlraditya45\", \"Carolus Raditya\", \"0855446546\", \"Freelancer\", \"Renovation\", \"Jl. Kebon Jeruk 5, Jakarta Barat\")," +
                "(\"AR1433\", \"andreasArchitect@gmail.com\", \"andreas45\", \"Andreas Aditya\", \"0855446546\", \"Teknik Sipil Binus University\", \"Build House\", \"Jl. Syahdan 51, Jakarta Barat\")," +
                "(\"AR1434\", \"wilo@gmail.com\", \"wilowilo45\", \"William\", \"0843443434\", \"Architect Division Binus Center\", \"Renovation\", \"Jl Kelapa Gading, Jakarta Utara\")," +
                "(\"AR1435\", \"wibukusuma@gmail.com\", \"riokusuma45\", \"Ryo Kenrie\", \"089473843839\", \"Freelancer\", \"Build House\", \"Jl . Pulo Gadung 60, Jakarta Timur\")," +
                "(\"AR1436\", \"hanif@gmail.com\", \"hanifwijaya45\", \"Hanif kusuma wijaya\", \"08463488235\", \"Senior Architect at Bisanara.com\", \"Renovation\", \"Jl Karyo, Padang\")," +
                "(\"AR1437\", \"andreharyanto@gmail.com\", \"andreharryanto45\", \"Andre Harriyanto\", \"08531242515\", \"Freelancer\", \"Build House\", \"Jl. Candi Orabmaban I, Bekasi Timur\")," +
                "(\"AR1438\", \"felixwijaya@gmail.com\", \"felixwijaya45\", \"Felix Anderson\", \"08548734873\", \"Freelancer\", \"Renovation\", \"Jl. Kebayoran lama 8, Jakarta Selatan\")," +
                "(\"AR1439\", \"kubusakeh@gmail.com\", \"kubusakeh45\", \"Kubus Jaya Akeh\", \"082415227362\", \"Freelancer\", \"Build House\", \"Jl. Pulo Gadung, Jakarta Timur\")," +
                "(\"AR1440\", \"sergio@gmail.com\", \"sergiomayranio45\", \"Sergio Mayranio\", \"08954059495\", \"Freelancer\", \"Renovation\", \"Jakarta\")," +
                "(\"AR1441\", \"tegarakeh@gmail.com\", \"tegarakeh45\", \"Muhammad Rafi Tegar\", \"08467326373\", \"Freelancer\", \"Build House\", \"Jl. Bekasi Barat\")";

        db.execSQL(architectData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MsClient");
        db.execSQL("DROP TABLE IF EXISTS MsArchitect");
        db.execSQL("DROP TABLE IF EXISTS TrTransaction");
        db.execSQL("DROP TABLE IF EXISTS TrFeedBack");
        db.execSQL("DROP TABLE IF EXISTS TrChat");
        db.execSQL("DROP TABLE IF EXISTS TrChatDetail");
        onCreate(db);
    }
}
