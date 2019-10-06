package com.example.eco_v;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private final static String CREATE_EVENT_TABLE="CREATE TABLE " + DBStructure.EVENT_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +DBStructure.EVENT + " TEXT, "+DBStructure.TIME+" TEXT, "+DBStructure.DATE+" TEXT, "+DBStructure.MONTH+" TEXT, "+DBStructure.YEAR+" TEXT)";

    private static final String DROP_EVENT_TABLE=" DROP TABLE IF EXISTS "+ DBStructure.EVENT_TABLE_NAME;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_EVENT_TABLE);
        onCreate(db);
    }

    public void SaveEvent(String event,String time,String  date, String month, String year, SQLiteDatabase database){
        ContentValues values=new ContentValues();
        values.put(DBStructure.EVENT,event);
        values.put(DBStructure.TIME,time);
        values.put(DBStructure.DATE,date);
        values.put(DBStructure.MONTH,month);
        values.put(DBStructure.YEAR,year);
        database.insert(DBStructure.EVENT_TABLE_NAME, null,values);
    }
    public Cursor ReadEvent(String date,SQLiteDatabase database){
        String [] Projectins={DBStructure.EVENT, DBStructure.TIME, DBStructure.DATE, DBStructure.MONTH,DBStructure.YEAR};
        String Selection=DBStructure.DATE + "=?";
        String [] SelectionArgs={date};

        return database.query(DBStructure.EVENT_TABLE_NAME,Projectins,Selection,SelectionArgs,null,null,null);

    }
    public Cursor ReadEventPerMonth(String month,String  year,SQLiteDatabase database){
        String [] Projectins={DBStructure.EVENT, DBStructure.TIME, DBStructure.DATE, DBStructure.MONTH,DBStructure.YEAR};
        String Selection=DBStructure.MONTH + "=? and "+DBStructure.YEAR +"=?";
        String [] SelectionArgs={month,year};

        return database.query(DBStructure.EVENT_TABLE_NAME,Projectins,Selection,SelectionArgs,null,null,null);

    }
    public void deleteEvent(String event,String date,String time,SQLiteDatabase database){
        String selection=DBStructure.EVENT+"=? and "+ DBStructure.DATE+"=? and "+DBStructure.TIME+"=?";
        String  [] selectionArg={event,date,time};
        database.delete(DBStructure.EVENT_TABLE_NAME,selection,selectionArg);
    }

}

