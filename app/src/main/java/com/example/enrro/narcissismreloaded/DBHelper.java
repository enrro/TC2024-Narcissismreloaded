package com.example.enrro.narcissismreloaded;

/**
 * Created by Enrro on 17/2/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "homework.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "student";
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";

    public static final String OTHER_TABLE_NAME = "friend";
    public static final String OTHER_ID_FIELD = "id";
    public static final String OTHER_NAME_FIELD = "name";
    public static final String OTHER_HOBBY_FIELD = "hobby";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // this is what should happen if the db didnt exists.
        // SQL for table creation
        String creation = "CREATE TABLE " + TABLE_NAME + "( " + ID_FIELD + " INTEGER PRIMARY KEY, " + NAME_FIELD + " TEXT)";
        String creation1 = "CREATE TABLE " + OTHER_TABLE_NAME + "( " + OTHER_ID_FIELD + " INTEGER PRIMARY KEY, " + OTHER_NAME_FIELD + " TEXT, " + OTHER_HOBBY_FIELD + " TEXT)";

        sqLiteDatabase.execSQL(creation);
        sqLiteDatabase.execSQL(creation1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String[] name = {TABLE_NAME};
        //prepared statements
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ?", name);
        onCreate(sqLiteDatabase);
    }

    public void add(String name){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME_FIELD, name);


        db.insert(TABLE_NAME, null, cv);
    }

    public void addFriend(String name, String hobby){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(OTHER_NAME_FIELD, name);
        cv.put(OTHER_HOBBY_FIELD, hobby);

        db.insert(OTHER_TABLE_NAME, null, cv);
    }

    public int deleteFriend(String name){
        SQLiteDatabase db = getWritableDatabase();

        String clause = OTHER_NAME_FIELD + " = ?";
        String[] args = {name};

        return db.delete(OTHER_TABLE_NAME, clause, args);
    }

    public String find(String name){
        SQLiteDatabase db = getWritableDatabase();

        String selection = NAME_FIELD + " = ?";
        String[] args = {name};

        Cursor c = db.query(TABLE_NAME, null, selection, args, null, null, null, null);
        String result = "";


        if (c.moveToFirst()){
            result = c.getString(1);
        }


        return result;
    }


    //recieves the name of the table and the name of the persona that you are looking for.
    //also recieves the column number from which give the resulting string
    //example. column == 1 then once its found the value of the name it gives back the second column
    public String find(String name, String table, int column){
        SQLiteDatabase db = getWritableDatabase();

        String selection = NAME_FIELD + " = ?";
        String[] args = {name};

        Cursor c = db.query(table, null, selection, args, null, null, null, null);
        String result = "";


        if (c.moveToFirst()){
            result = c.getString(column);
        }


        return result;
    }
}
