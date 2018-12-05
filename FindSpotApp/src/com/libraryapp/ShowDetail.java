package com.libraryapp;


import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.xml.FoodNoteListHandler;
import com.xml.FoodNoteStruct;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class ShowDetail extends Activity {
	
	
	Intent intent;
	
	int index = 0;
	String IPAddress="";
	
	TextView res, tel, address, content;
	
	int type;
	String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
	
	ArrayList<FoodNoteStruct> query_data = new ArrayList<FoodNoteStruct>();
	
	int ln;
	
	static ShowDetail fl;
	
	ImageView iv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdetail);
		
		fl = this;
		
		Bundle bundle0311 =this.getIntent().getExtras();

        s1 = bundle0311.getString("s1");
        s2 = bundle0311.getString("s2");
        s3 = bundle0311.getString("s3");
        s4 = bundle0311.getString("s4");
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		IPAddress = (String) this.getResources().getText(R.string.url);
		
		res = (TextView) findViewById(R.id.TextView01);
		res.setText(s1);
		address = (TextView) findViewById(R.id.TextView04);
		address.setText(s3);
		content = (TextView) findViewById(R.id.textView5);
		content.setText(s2);
		
		//iv = (ImageView) findViewById(R.id.imageView1);
		
		/*
		try {
	        URL url = new URL(s4);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        BitmapFactory.Options opts = new BitmapFactory.Options();
	        
	        opts.inSampleSize = 2;

	        Bitmap myBitmap = BitmapFactory.decodeStream(input, null, opts);
	        iv.setImageBitmap(myBitmap);
			
	  } catch (Exception e) {
	        e.printStackTrace();		        
	  }*/
		
		
		Button ImageButton1 = (Button) findViewById(R.id.button1);
		ImageButton1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View view) 
			{
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, s3);
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, s1);
				//shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

				PackageManager pm = getBaseContext().getPackageManager();
				List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
				for (final ResolveInfo app : activityList) 
				{
					 if ((app.activityInfo.name).contains("facebook")) 
					 {
						final ActivityInfo activity = app.activityInfo;
						final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
						shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
						shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
						shareIntent.setComponent(name);
						startActivity(shareIntent);
						break;
					}
				}				
			}
	
		});
		
		Button ImageButton2 = (Button) findViewById(R.id.button2);
		ImageButton2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View view) 
			{			
				Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + s3);
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
				mapIntent.setPackage("com.google.android.apps.maps");
				startActivity(mapIntent);
			}
	
		});
		

	}
	
	public int toweb(String uriAPI) {
		int error = 0;
		HttpGet httpRequest = new HttpGet(uriAPI);

		try {
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
			} else {
				// mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString());
			}
		} catch (ClientProtocolException e) {
			// mTextView1.setText(e.getMessage().toString());
			e.printStackTrace();
			error = 1;
		} catch (IOException e) {
			// mTextView1.setText(e.getMessage().toString());
			e.printStackTrace();
			error = 1;
		} catch (Exception e) {
			// mTextView1.setText(e.getMessage().toString());
			e.printStackTrace();
			error = 1;
		}

		return error;
	}	
	
	
}
