package com.example.notessqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "Notes.db";//This will be the name of the database storing the notes.

    public static final String TABLE_NAME = "Notes";//Creating a table which would store the data.

    //Creating columns.
    public static final String _ID = "_id";//This id would help in referring to the particular data when updating or deleting.
    public static final String SUBJECT = "subject";//This would be the subject/heading of the data stored.
    public static final String DESC = "description";//This would actually have the details/data of the note.

    static final int DB_VERSION = 1;//Database version which will be updated afterwards.

    //Query to create the table
    private static final String CREATE_TABLE = "create table " + TABLE_NAME
                                                + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//Query to put id in first column and keep it as primary key and autoincrement it.
                                                + SUBJECT + " TEXT NOT NULL, " //Subject
                                                + DESC + " TEXT);";//Description

    //The constructor of the database.
    public DatabseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //On create method.
    public void onCreate(SQLiteDatabase db){
        //Executing the query of creating the table.
        db.execSQL(CREATE_TABLE);

    }

    //On upgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );//Remove the table if it already exists.
        onCreate(db);//Go to/implement the oncreate method.
    }
}
