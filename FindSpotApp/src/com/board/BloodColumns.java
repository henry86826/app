package com.board;
/* 
 * Copyright (C) 2009 Roman Masek
 * 
 * This file is part of OpenSudoku.
 * 
 * OpenSudoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OpenSudoku is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OpenSudoku.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */



import android.provider.BaseColumns;

public abstract class BloodColumns implements BaseColumns 
{
	public static final String Blood_ID = "blood_id";
	public static final String S1 = "dastolic";
	public static final String S2 = "systolic";
	public static final String S3 = "rdate";
	public static final String S4 = "img";
  
  String data1, data2, data3, data4, data5, data6, data7, data8;
}
