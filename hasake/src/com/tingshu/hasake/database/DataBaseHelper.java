package com.tingshu.hasake.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper  extends SQLiteOpenHelper {
	
    private static final int VERSION = 10;
    public static final String DATABASE_NAME = "HASAKE_DATABASE";
    
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
