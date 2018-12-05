package com.board;


import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;

public class mDatabase {
	public static final String DATABASE_NAME = "opensudoku";


	public static final String SUDOKU_TABLE_NAME = "sudoku";
	public static final String FOLDER_TABLE_NAME = "folder";
	public static final String BOADRD_TABLE_NAME = "board";
	public static final String BLOOD_TABLE_NAME = "blood";
	public static final String HOSPITAL_TABLE_NAME = "hospital";
	public static final String PLACE_TABLE_NAME = "place";

	//private static final String TAG = "SudokuDatabase";

	private DatabaseHelper mOpenHelper;

	public mDatabase(Context context) {
		mOpenHelper = new DatabaseHelper(context);
	}

	/**
	 * Returns list of puzzle folders.
	 *
	 * @return
	 */
	public Cursor getSCOREList() {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(BOADRD_TABLE_NAME);

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		return qb.query(db, null, null, null, null, null, "");
	}
	
	public Cursor getBLOODList() {
	    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	
	    qb.setTables(BLOOD_TABLE_NAME);
	
	    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	    return qb.query(db, null, null, null, null, null, "");
	}
	
	public Cursor getPlaceList() {
	    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	
	    qb.setTables(PLACE_TABLE_NAME);
	
	    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	    return qb.query(db, null, null, null, null, null, "");
	}
	
	public void inputScore(String s1, String s2,  String s3, String s4, String s5, String s6) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		mOpenHelper.insertBOARD(db, s1, s2, s3, s4, s5, s6);
	}
	
	 public void inputBlood(String s1, String s2,  String s3,  String s4) {
	    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

	    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	    mOpenHelper.insertBLOOD(db, s1, s2, s3, s4);
	}
	 
	 public void inputPlace(String s1, String s2,  String s3,  String s4) {
		    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		    mOpenHelper.insertPlace(db, s1, s2, s3, s4);
	} 
	 
	public void close() {

		mOpenHelper.close();
	}

	public void beginTransaction() {
		mOpenHelper.getWritableDatabase().beginTransaction();
	}

	public void setTransactionSuccessful() {
		mOpenHelper.getWritableDatabase().setTransactionSuccessful();
	}

	public void endTransaction() {
		mOpenHelper.getWritableDatabase().endTransaction();
	}
}
