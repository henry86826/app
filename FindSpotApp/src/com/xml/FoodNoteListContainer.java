package com.xml;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class FoodNoteListContainer 
{

	private ArrayList<FoodNoteStruct> jlist_items;

	//item
	public ArrayList<FoodNoteStruct> getListItems() 
	{
	  return jlist_items;
	}
	
	public FoodNoteStruct getoneJL(int index)
	{
		return jlist_items.get(index);
	}
	
	public FoodNoteListContainer() 
	{
		jlist_items = new ArrayList<FoodNoteStruct>();
	}

	public void addRXMLItem(FoodNoteStruct item) 
	{
		jlist_items.add(item);
	}
}
