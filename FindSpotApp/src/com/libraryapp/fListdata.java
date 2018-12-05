package com.libraryapp;


import java.io.File;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlcleaner.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.board.mDatabase;
import com.xml.FoodNoteListHandler;
import com.xml.FoodNoteStruct;
import com.xml.LoginXMLHandler;
import com.xml.LoginXMLStruct;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class fListdata extends Activity 
{
	private static final int MSG_ADD_OK = 1;  
	private static final int MSG_ADD_FAIL = 2; 
		
	Intent intent;
	
	int index = 0;
	String IPAddress="";
	
	private ListView lv;
	
	TextView tv;
	
	int type;
	String msg;
	
	ArrayList<FoodNoteStruct> query_data = new ArrayList<FoodNoteStruct>();
	ArrayList<FoodNoteStruct> query_data2 = new ArrayList<FoodNoteStruct>();
	
	int ln;
	
	static fListdata fl;

	String id;
	String user;	
	
	LoginXMLStruct data;
	
	EditText s1;
	
	int level = -1;
	
	int counter = 0;
    
    Button search;
    
    Button back;
    
    EditText et;
    
    int flag = 0;	
    
	public LocationManager locationManager1;
	public Location location1 = null;
	public String locationProvider1;
	
	//int idx = 0;
	
	ProgressDialog myDialog;
	
	List<Map<String, Object>> mylist;
	
	String keywords = "";
	
	mDatabase rDatabase;
	
	private Cursor mCursor;
	
	private SharedPreferences settings;
    private static final String spdata = "DATA";
    private static final String nameField = "NAME";
    
    int idx;
    int rnd;
    
    //這頁就只是去json回來顯示
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_main2);
		
		fl = this;
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		IPAddress = (String) this.getResources().getText(R.string.url);
		
		rDatabase = new mDatabase(getApplicationContext());
		
		Bundle rdata = getIntent().getExtras();
		rnd = rdata.getInt("rnd");
		
		settings = getSharedPreferences(spdata,0);
        idx = settings.getInt(nameField, 0);
        
        Log.i("TAG", "idx = " + idx);
        
        if (idx == 0)
        {
        	//把json抓到的顯示
        	settings.edit().putInt(nameField, 1);
        	
        	try
            {
    	        JSONObject jsonObj = new JSONObject(loadJSONFromAsset());
    	        
    	        JSONObject root = jsonObj.getJSONObject("ROOT");
    	        // Getting JSON Array node
    	        JSONArray record = root.getJSONArray("RECORD");
    	     
    	        // looping through All Contacts
    	        for (int i = 0; i < record.length(); i++) {
    	           JSONObject c = record.getJSONObject(i);
    	           String name = c.getString("品牌名稱");
    	           
    	           if (name==null || name.equals("")) continue;
    	           
    	           //{"序號":"1","店家類型":"非連鎖","隸屬範圍":"繼光街商圈","店家型態":"飲料","品牌名稱":"幸發亭蜜豆冰本舖","門市名稱":"─","區域":"中區","連絡電>話":"04-22293257","聯絡地址":"臺中市中區臺灣大道一段137號","實施日期":"106年4月15日至今","好康類型":"現金回饋","好康內容":"自備碗、盤、碟及餐盒折價5元","備註":"內用外帶皆可"}
    	           String intr = c.getString("店家類型")+"\n" +  c.getString("好康內容") + c.getString("店家型態") + c.getString("備註") + "\n"+"電話:" + c.getString("連絡電話");
    	          
    	           String address = c.getString("聯絡地址");
    	           Log.i("TAG", name);
    	           rDatabase.inputBlood(name, intr, address, "");
    	        }
            }
            catch (Exception e)
            {
            	e.printStackTrace();
            }

        	
        	

        }
				
		lv = (ListView) findViewById(R.id.listView1);
		
		search = (Button) findViewById(R.id.button1);
		
		back = (Button) findViewById(R.id.button2);
		
		//editText1
		et = (EditText) findViewById(R.id.editText1);	
		
		//textView1
		tv = (TextView) findViewById(R.id.textView1);
		
		//若是隨機美食要標一下
		if (rnd == 1)
		{
			tv.setText("隨機美食");
		}
			
        locationManager1 = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		String provider = LocationManager.NETWORK_PROVIDER;
		location1 = locationManager1.getLastKnownLocation(provider);
    	locationManager1.requestLocationUpdates(provider, 0, 0, locationListener1);
		
	    if(location1 ==null)
	    {
	    	Toast.makeText(fListdata.this, "請先開啟GPS服務!!", Toast.LENGTH_LONG).show();
	    }
	    
	    //搜尋按鈕
		search.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) 
			{
				if (flag == 0)
				{
					search.setText("清除");
					flag = 1;
					reload(et.getText().toString());
				}
				else
				{
					search.setText("搜尋");
					flag = 0;
					reload("");
					
					lv.setAdapter(null);
					
				}
			}
		});
		
		
		reload("");
		
	}
	
	//只是去資料而己
	void reload(String str)
	{
		keywords = str;
		 
		//Progress
        myDialog = ProgressDialog.show
        (
        		fListdata.this,
        		"找JSON資料",
        		"請稍後",
            true
        );
        
        new Thread()
        {
          public void run()
          {
        	try 
      		{
        		mylist = getTalkList(keywords);
      		} 
        	catch (Exception e) 
      		{
      			myDialog.dismiss();
                
      			return;
      		}
      		finally
      		{
      			myDialog.dismiss();
                Message msg = new Message();
                msg.what = MSG_ADD_OK;
                myHandler.sendMessage(msg);
      		}
      		
          }
         }.start();      
	}
	
	//json抓資料
	private List<Map<String, Object>> getTalkList(String str) 
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        query_data2.clear();
        query_data.clear();
        
        mCursor = rDatabase.getBLOODList();

		mCursor.moveToFirst();
		
        //no data
        if (mCursor.isAfterLast())
        {
        	
        }
        mCursor.moveToFirst();
        
        while(!mCursor.isAfterLast())
        {
        	FoodNoteStruct mydata = new FoodNoteStruct();
        	
        	mydata.id = mCursor.getString(0);
        	mydata.Name = mCursor.getString(1);
        	mydata.Description = mCursor.getString(2);
        	mydata.Add = mCursor.getString(3);
        	mydata.Img = mCursor.getString(4);
        	
        	query_data.add(mydata);
	        
        	mCursor.moveToNext();
        }        	

		Log.i("TAG", "size: " + query_data.size());
		
		counter = 0;
        
        if (str.equals(""))
        {
			counter = 0;

			if (rnd == 0)
        	{
				for (int i=0; i<query_data.size(); i++)
				{
		            if (query_data.get(i).Add == null) continue;
		            
		            //System.out.println("Distance is: " + results[0]);	            
		            
		            //if (results[0] > 10000) continue;
		            
		            HashMap<String, Object> map = new HashMap<String, Object>();
		            map.put("ItemTitle", query_data.get(i).Name);
		            map.put("ItemText",  query_data.get(i).Add);
				    list.add(map);
				    
		            query_data2.add(query_data.get(i));
				    
				    counter++;
				}
				
				if (query_data.size() == 0)
				{
					HashMap<String, Object> map = new HashMap<String, Object>();
		            map.put("ItemTitle","not found");
		            map.put("ItemText", "");
				    list.add(map);			
				}
			}
			else
			{
				Random rand = new Random();
        		int i = rand.nextInt(query_data.size());
        		HashMap<String, Object> map = new HashMap<String, Object>();
	            map.put("ItemTitle", query_data.get(i).Name);
	            map.put("ItemText",  query_data.get(i).Add);
	            query_data2.add(query_data.get(i));
			    list.add(map);
			}
        }
        else
        {
        	if (rnd == 0)
        	{
				for (int i=0; i<query_data.size(); i++)
				{
		          
		            //if (results[0] > 10000) continue;				
					
		            if (query_data.get(i).Name.contains(str) ||
		            	query_data.get(i).Description.contains(str))
		            {
	    	            Log.i("TAG", query_data.get(i).Description);
	    	            
	    	            HashMap<String, Object> map = new HashMap<String, Object>();
	    	            map.put("ItemTitle", query_data.get(i).Name);
	    	            map.put("ItemText",  query_data.get(i).Add);
	    	            query_data2.add(query_data.get(i));
	    			    list.add(map);
		            }
				}
	    	
				
				if (query_data2.size() == 0)
				{
		            HashMap<String, Object> map = new HashMap<String, Object>();
		            map.put("ItemTitle","not found");
		            map.put("ItemText", "");
				    list.add(map);			
				}	
        	}
        	else
        	{
        		Random rand = new Random();
        		int i = rand.nextInt(query_data.size());
        		HashMap<String, Object> map = new HashMap<String, Object>();
	            map.put("ItemTitle", query_data.get(i).Name);
	            map.put("ItemText",  query_data.get(i).Add);
	            query_data2.add(query_data.get(i));
			    list.add(map);
        	}
        }
        
        return list;
	}
	
	public LocationListener locationListener1 =
			new LocationListener() 
			{
				
				//@Override
				public void onStatusChanged(String provider, int status, Bundle extras)
				{
				
				}
				
				//@Override
				public void onProviderEnabled(String provider)
				{
					
				}
				
				//@Override
				public void onProviderDisabled(String provider)
				{
					location1 = null;	
				}
				
				//@Override
				public void onLocationChanged(Location location)
				{
					location1 = location;
					
				}
	};
	
	//去讀json進來
	public String loadJSONFromAsset() {
	    String json = null;
	    try {
	        InputStream is = getAssets().open("data.json");
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        json = new String(buffer, "UTF-8");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;
	}
	//就顯示
    public Handler myHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
              case MSG_ADD_OK:
         		 SimpleAdapter adapter = new SimpleAdapter(fListdata.this,
       				  mylist,
       		          R.layout.no_listview_style,
       		          new String[]{"ItemTitle","ItemText"},
       		          new int[]{R.id.topTextView, R.id.bottomTextView});
         		 
         		 lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	       			  @Override
	       			  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
	       			  {
	       				  
	       				  Intent intent = new Intent();
	       	              intent.setClass(fListdata.this, ShowDetail.class);
	
	       	              Bundle bundle = new Bundle();
	       	              bundle.putString("s1", query_data2.get(position).Name);
	       	              bundle.putString("s2", query_data2.get(position).Description);
	       	              bundle.putString("s3", query_data2.get(position).Add);
	       	              bundle.putString("s4", query_data2.get(position).Img);
	
	       	              intent.putExtras(bundle);
	
	       	              startActivity(intent);
	       			  }
	       		  });
       		
         		  lv.setAdapter(adapter);	          		
            	  break;
              case MSG_ADD_FAIL:
  	        	Context context = getApplicationContext();
	            int duration = Toast.LENGTH_LONG;
	            Toast toast = Toast.makeText(context, "網路連線time out", duration);
	            toast.show();
            	  break;
            }
        }
    };

	
	
}
