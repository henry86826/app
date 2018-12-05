package com.board;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * This class helps open, create, and upgrade the database file.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "DatabaseHelper";

	public static final int DATABASE_VERSION = 9;

	private Context mContext;

	DatabaseHelper(Context context) {
		super(context, mDatabase.DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE IF NOT EXISTS " + mDatabase.BOADRD_TABLE_NAME + " ("
				+ BoardColumns._ID + " INTEGER PRIMARY KEY,"
				+ BoardColumns.S1 + " TEXT,"
				+ BoardColumns.S2 + " TEXT,"
		        + BoardColumns.S3 + " TEXT,"
		        + BoardColumns.S4 + " TEXT,"
		        + BoardColumns.S5 + " TEXT,"
		        + BoardColumns.S6 + " TEXT"
				+ ");");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS " + mDatabase.BLOOD_TABLE_NAME + " ("
		        + BloodColumns._ID + " INTEGER PRIMARY KEY,"
		        + BloodColumns.S1 + " TEXT,"
		        + BloodColumns.S2 + " TEXT,"
		        + BloodColumns.S3 + " TEXT,"
		        + BloodColumns.S4 + " TEXT"
		        + ");");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS " + mDatabase.PLACE_TABLE_NAME + " ("
		        + PlaceColumns._ID + " INTEGER PRIMARY KEY,"
		        + PlaceColumns.S1 + " TEXT,"
		        + PlaceColumns.S2 + " TEXT,"
		        + PlaceColumns.S3 + " TEXT,"
		        + PlaceColumns.S4 + " TEXT"
		        + ");");
		
	}

	
	void insertBOARD(SQLiteDatabase db,  String s1, String s2,  String s3, String s4, String s5, String s6) 
	{
	  
	    String sql = "INSERT INTO " + mDatabase.BOADRD_TABLE_NAME + "(" + 
		    BoardColumns.S1 +"," + BoardColumns.S2 + "," +  
		    BoardColumns.S3 + "," + 
		    BoardColumns.S4 + "," + 
		    BoardColumns.S5 + "," + 
		    BoardColumns.S6 + ") VALUES ('" 
		    + s1 + "', '" + s2 + "', '" + s3 + "', '" + s4 + "', '" + s5 + "', '" + s6 + "');";
	  
		db.execSQL(sql);
	}
	
	 void insertBLOOD(SQLiteDatabase db,  String s1, String s2,  String s3,  String s4) 
	 {
	    
	    String sql = "INSERT INTO " + mDatabase.BLOOD_TABLE_NAME + "(" + 
	    BloodColumns.S1  +"," + BloodColumns.S2 + "," +  BloodColumns.S3 + "," +  BloodColumns.S4 + ") VALUES ('" 
	    + s1 + "', '" + s2 + "', '" + s3 + "', '" + s4 + "');";
	    
	    db.execSQL(sql);
	 }
	 
	 void insertPlace(SQLiteDatabase db,  String s1, String s2,  String s3,  String s4) 
	 {
	    String sql = "INSERT INTO " + mDatabase.PLACE_TABLE_NAME + "(" + 
        PlaceColumns.S1  +"," + PlaceColumns.S2 + "," +  PlaceColumns.S3 + "," +  PlaceColumns.S4 + ") VALUES ('" 
	    + s1 + "', '" + s2 + "', '" + s3 + "', '" + s4 + "');";
	    
	    db.execSQL(sql);
	 }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ".");

	}

}
