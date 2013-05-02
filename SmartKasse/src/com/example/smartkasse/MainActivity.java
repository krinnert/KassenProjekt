package com.example.smartkasse;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
		Drawable d=getResources().getDrawable(com.actionbarsherlock.R.drawable.abs__ab_stacked_solid_dark_holo);  
		
		
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(d);
		
		
		//Header des SlidingMenus genau so hoch machen wie der Actionbar	
		int actionBarHeight=0;
		//Dieser Block lie�t die H�he des Actionsbar aus
	    TypedValue tv = new TypedValue();
	    if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
	        actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
	    
	    if(actionBarHeight ==0 && getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize, tv, true)){
	            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
	    }
	    
	    //�berschrift im Header an die ausgelesene H�he anpassen
		 TextView header = (TextView)findViewById(R.id.sm_header_text);
		 header.setHeight(actionBarHeight);
		
		 
		 LinearLayout header1 = (LinearLayout)findViewById(R.id.sm_header);
		 header1.setBackgroundColor(Color.DKGRAY);
		
		// Adapter um GridView damit zu f�llen()
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
		
		super.onPause();
	}
		
}
