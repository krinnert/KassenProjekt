package com.example.smartkasse;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.GridView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class MainActivity extends SlidingActivity {

	SlidingMenu slidingMenu;
	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.behind_layout);

		slidingMenu = getSlidingMenu();
		actionBar = getSupportActionBar();

		// SlidingMenu einrichten
		slidingMenu.setBehindOffset(150);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

		
		
		// ActionBar einrichten
		Drawable d=getResources().getDrawable(com.actionbarsherlock.R.drawable.abs__ab_stacked_solid_dark_holo);;  
		
		
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(d);

		// Display soll anbleiben
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		
		// Adapter um GridView damit zu füllen
		ButtonAdapter adapter = new ButtonAdapter(this, null);
		GridView gridView = (GridView) findViewById(R.id.buttonframe);
		gridView.setAdapter(adapter);
		
	
		   


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getSupportMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			slidingMenu.toggle();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			slidingMenu.toggle();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// Display soll anbleiben
				//getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				
				//WindowManager.LayoutParams lp = getWindow().getAttributes();
				//lp.screenBrightness = 0.01f;
				//getWindow().setAttributes(lp);
		((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG").acquire();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		Log.v("Test","Test");
		super.onPause();
	}
		
}
