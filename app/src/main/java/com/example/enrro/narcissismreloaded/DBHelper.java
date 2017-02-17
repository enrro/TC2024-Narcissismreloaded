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

    public static final String DB_NAME = "school.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "student";
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";


    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // this is what should happen if the db didnt exists.
        // SQL for table creation
        String creation = "CREATE TABLE " + TABLE_NAME + "( " + ID_FIELD + " INTEGER PRIMARY KEY, " + NAME_FIELD + " TEXT)";

        sqLiteDatabase.execSQL(creation);

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

    public int delete(String name){
        SQLiteDatabase db = getWritableDatabase();

        String clause = NAME_FIELD + " = ?";
        String[] args = {name};

        return db.delete(TABLE_NAME, clause, args);

    }
    public int find(String name){
        SQLiteDatabase db = getWritableDatabase();

        String selection = NAME_FIELD + " = ?";
        String[] args = {name};

        Cursor c = db.query(TABLE_NAME, null, selection, args, null, null, null, null);
        int result = -1;


        if (c.moveToFirst()){
            result = c.getInt(0);
        }


        return result;
    }
}
