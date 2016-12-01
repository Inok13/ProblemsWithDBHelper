package ru.startandroid.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    final String DB_NAME = "staff";
    final int DB_VERSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(MainActivity.this, DB_NAME, DB_VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG,"---Staff db.Version " + db.getVersion() + "---");
        writeStaff(db);
        dbHelper.close();
    }

    private void writeStaff(SQLiteDatabase db){
        Log.d(LOG_TAG,"---Method writeStaff was started---");
        Cursor c = db.rawQuery("select * from people", null);
        logCursor(c, "Table people");
        c.close();
    }

    void logCursor(Cursor c, String title){
        Log.d(LOG_TAG,"---Method logCursor was started---");
        if(c != null){
            if(c.moveToFirst()){
                Log.d(LOG_TAG, title + ". " + c.getCount() + " rows.");
                StringBuilder sb = new StringBuilder();
                do{
                    sb.setLength(0);
                    for(String cn: c.getColumnNames()){
                        sb.append(cn + " = "+
                        c.getString(c.getColumnIndex(cn)) + ";");
                    }
                    Log.d(LOG_TAG, sb.toString() );
                }while (c.moveToNext());
            }else
                Log.d(LOG_TAG,"DataBase is empty !");
        }else
            Log.d(LOG_TAG, title + ". Cursor is null!");
    }
}
