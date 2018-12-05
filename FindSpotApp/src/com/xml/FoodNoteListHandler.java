package com.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class FoodNoteListHandler extends DefaultHandler
{
	//tag

	private String TAG = "RentListHandler";
	
	private final static int ID = 1;
	private final static int NAME = 2;
	private final static int WEBSITE = 3;
	private final static int CLASS1 = 4;
	private final static int PY = 5;
	private final static int PX = 6;
	private final static int GOV = 7;
	private final static int OPENTIME = 8;
	private final static int TRAVELLINGINFO = 9;
	private final static int ZIPCODE = 10;
	private final static int ADD = 11;
	private final static int TEL = 12;
	private final static int DESCRIPTION = 13;
	
	private FoodNoteStruct jls;
	private FoodNoteListContainer jlcs;
	
	private int type;

	public FoodNoteListContainer getContainer() 
	{
		return jlcs;
	}

	public FoodNoteStruct getJListStruct() 
	{
		return jlcs.getoneJL(0);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String s = new String(ch, start, length);
		
		switch (type) 
		{
		case ID:
			jls.id = s;
			type = 0;
			break;
		case NAME:
			jls.Name = s;
			type = 0;
			break;
		case WEBSITE:
			jls.Website = s;
			type = 0;
			break;
		case CLASS1:
			jls.Class1 = s;
			type = 0;
			break;
		case PY:
			jls.Py = s;
			type = 0;
			break;
		case PX:
			jls.Px = s;
			type = 0;
			break;
		case GOV:
			jls.Gov = s;
			type = 0;
			break;
		case OPENTIME:
			jls.Opentime = s;
			type = 0;
			break;
		case TRAVELLINGINFO:
			jls.Travellinginfo = s;
			type = 0;
			break;			
		case ZIPCODE:
			jls.Zipcode = s;
			type = 0;
			break;
		case ADD:
			jls.Add = s;
			type = 0;
			break;
		case TEL:
			jls.Tel = s;
			type = 0;
			break;
		case DESCRIPTION:
			jls.Description = s;
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.toLowerCase().equals("row")) 
		{
			jlcs.addRXMLItem(jls);	
		}
	}

	@Override
	public void startDocument() throws SAXException 
	{
		jlcs = new FoodNoteListContainer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		
		if (localName.toLowerCase().equals("row")) 
		{
			jls = new FoodNoteStruct();
			return;
		}
		else if (localName.toLowerCase().equals("id")) 
		{
			type = ID;
			return;
		}
		else if (localName.toLowerCase().equals("name")) 
		{
			type = NAME;
			return;
		}
		else if (localName.toLowerCase().equals("website")) 
		{
			type = WEBSITE;
			return;
		}
		else if (localName.toLowerCase().equals("class1")) 
		{
			type = CLASS1;
			return;
		}
		else if (localName.toLowerCase().equals("py")) 
		{
			type = PY;
			return;
		}
		else if (localName.toLowerCase().equals("px")) 
		{
			type = PX;
			return;
		}
		else if (localName.toLowerCase().equals("gov")) 
		{
			type = GOV;
			return;
		}
		else if (localName.toLowerCase().equals("opentime")) 
		{
			type = OPENTIME;
			return;
		}
		else if (localName.toLowerCase().equals("travellinginfo")) 
		{
			type = TRAVELLINGINFO;
			return;
		}		
		else if (localName.toLowerCase().equals("zipcode")) 
		{
			type = ZIPCODE;
			return;
		}		
		else if (localName.toLowerCase().equals("add")) 
		{
			type = ADD;
			return;
		}		
		else if (localName.toLowerCase().equals("tel")) 
		{
			type = TRAVELLINGINFO;
			return;
		}		
		else if (localName.toLowerCase().equals("description")) 
		{
			type = DESCRIPTION;
			return;
		}		
		type = 0;
	}

}