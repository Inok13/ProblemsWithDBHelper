package ru.startandroid.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Home on 29.11.2016.
 */

class DBHelper extends SQLiteOpenHelper{

    private String[] people_name = { "Frankie Head", "Luxury George", "Boris Razor", "Frankie TwoFingers", "Turkish", "Tommy"};
    private String[] people_positions = {"Director", "Accountant", "Program Developer",
            "Program Developer", "Security", "Program Developer"};
    private ContentValues cv = new ContentValues();

    public DBHelper(Context context, String base_title, int base_version){
        super(context, base_title, null, base_version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
        Log.d("myLog", "-----------------------------------");
        Log.d("myLog", "---OnCreate database---");

        db.execSQL("create table people ("+
                "id integer primary key autoincrement,"+
                "name text, position text);");

        fillInDataBase(db);
    }

    protected void fillInDataBase(SQLiteDatabase db){
        for(int i = 0; i < people_name.length; i++){
            cv.clear();
            cv.put("name", people_name[i]);
            cv.put("position", people_positions[i]);
            db.insert("people", null, cv);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
