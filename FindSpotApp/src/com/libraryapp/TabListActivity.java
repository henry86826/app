package com.libraryapp;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabListActivity extends Activity {

	int language;
	
	String button_language[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_activity);
		
		//主選單, 按鈕對應要切到那, 目前就3頁而己
		//imageButton1
		ImageButton food = (ImageButton) findViewById(R.id.imageButton1);
		food.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) 
			{
				Intent app = new Intent(TabListActivity.this, fListdata.class);
				Bundle rdata = new Bundle();
				rdata.putInt("rnd", 0);
				app.putExtras(rdata);
				startActivity(app);
				
			}
		});
		
		
		ImageButton pedometer = (ImageButton) findViewById(R.id.ImageButton02);
		pedometer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) 
			{
				Intent app = new Intent(TabListActivity.this, WalKStepActivity.class);
				startActivity(app);
			}
		});
		
		ImageButton exit = (ImageButton) findViewById(R.id.ImageButton05);
		exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) 
			{
				finish();
			}
		});
		
		ImageButton random = (ImageButton) findViewById(R.id.imageView4);
		random.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) 
			{
				Intent app = new Intent(TabListActivity.this, fListdata.class);
				Bundle rdata = new Bundle();
				rdata.putInt("rnd", 1);
				app.putExtras(rdata);
				startActivity(app);
			}
		});
		
		
	}
	
	
}
