package com.example.mobilefinalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String databaseName = "watchlist.db";
    public static final String title = "title";
    public static final String posterUrl = "posterUrl";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, databaseName , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table watchlist " +
                        "(title text, posterUrl text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS watchlist");
        onCreate(db);
    }

    public boolean insertItem (String title, String posterUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("posterUrl", posterUrl);
        db.insert("watchlist", null, contentValues);
        return true;
    }

    public Cursor getData(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from watchlist where title="+title+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, title);
        return numRows;
    }

    public boolean updateWatchlist (String title, String posterUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("posterUrl", posterUrl);
        db.update("watchlist", contentValues, "title = ? ", new String[] { title } );
        return true;
    }

    public Integer deleteItem (String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("watchlist",
                "title = ? ",
                new String[] { title });
    }

    public ArrayList<String> getAllItems() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from watchlist", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(title)));
            res.moveToNext();
            res.close();    //added to close the SQLite database cursor after use
        }
        return array_list;
    }
}
