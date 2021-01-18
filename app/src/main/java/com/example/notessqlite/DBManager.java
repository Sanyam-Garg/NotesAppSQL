package com.example.notessqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabseHelper dbHelper;//Instance of the already created database created here to access it.
    private Context context;//Instance of the context.
    private SQLiteDatabase database;//Instance of an sql database.

    //Creating the constructor of the class.
    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException{
        dbHelper = new DatabseHelper(context);  //Assigning the instance of the already created database to the database manager.
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    //Method to insert data.
    public void insert(String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabseHelper.SUBJECT, name);
        contentValues.put(DatabseHelper.DESC, desc);
        database.insert(DatabseHelper.TABLE_NAME, null, contentValues);//After putting all the data to contentvalues, insert that data to the table given by that name.

    }

    //Method to fetch the columns.
    public Cursor fetch(){
        String[] columns = new String[] {DatabseHelper._ID,DatabseHelper.SUBJECT,DatabseHelper.DESC};

        Cursor cursor = database.query(DatabseHelper.TABLE_NAME, columns, null, null,null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Method to update.
    public int update(long _id, String name, String desc){
        ContentValues contentValues = new ContentValues();//Storing all the data provided by the user in content values.
        contentValues.put(DatabseHelper.SUBJECT, name);
        contentValues.put(DatabseHelper.DESC, desc);

        int i = database.update(DatabseHelper.TABLE_NAME, contentValues, DatabseHelper._ID + " = "+ _id, null);//Updating the data provided in the content values according to the ID.

        return i;
    }

    public void delete(long _id){
        database.delete(DatabseHelper.TABLE_NAME, DatabseHelper._ID + " = " + _id, null);//Deleting on the basis of the id as it is the primary key.

    }
}
