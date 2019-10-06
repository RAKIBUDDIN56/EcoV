package com.example.eco_v;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMaterial {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_CELL = "_cell";

    private final String DATABASE_NAME = "ContactsDB";
    private final String DATABASE_TABLE = "ContactsTable";
    private static final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    public DBMaterial(Context context)
    {
        ourContext = context;
    }

    private  class DBHelper extends SQLiteOpenHelper {

        public DBHelper (Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode="CREATE TABLE " +  DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_CELL + " TEXT NOT NULL)";
            db.execSQL(sqlCode);

        }
    }

    public DBMaterial open() throws SQLException {

        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;

    }
    public void close()
    {
        ourHelper.close();
    }

    public long createEntry(String name, String cell){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_CELL,cell);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }
    public String getData(){

        String [] columns=new String[] {KEY_ROWID,KEY_NAME,KEY_CELL};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);

        String result="";
        int iRowID=c.getColumnIndex(KEY_ROWID);
        int iName=c.getColumnIndex(KEY_NAME);
        int iCell=c.getColumnIndex(KEY_CELL);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result + c.getString(iRowID) + ": " + c.getString(iName) + " "+
                    c.getString(iCell)+ "\n";

        }
        c.close();
        return result;
    }

    public  long deleteEntry(String rowId){
        return ourDatabase.delete(DATABASE_TABLE,KEY_ROWID + "=?",new String[]{rowId});
    }
    public long updateEntry(String rowId,String name, String cell){
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_CELL,cell);

        return ourDatabase.update(DATABASE_TABLE,cv,KEY_ROWID+"=?",new String[]{rowId});

    }
}
