package com.board;
import android.provider.BaseColumns;

public abstract class BoardColumns implements BaseColumns {
	public static final String BOARD_ID = "board_id";
	public static final String S1 = "medianame";
	public static final String S2 = "englishname";
	public static final String S3 = "type";
	public static final String S4 = "shape";
	public static final String S5 = "img";
	public static final String S6 = "content";
	
	String data1, data2, data3, data4;
}
