package com.example.smartkasse;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	SlidingMenu slidingMenu;
	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainfragmentdynamic);
		addDynamicFragment();
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
		//Dieser Block ließt die Höhe des Actionsbar aus
	    TypedValue tv = new TypedValue();
	    if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
	        actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
	    
	    if(actionBarHeight ==0 && getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize, tv, true)){
	            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
	    }
	    
	    //Überschrift im Header an die ausgelesene Höhe anpassen
		 TextView header = (TextView)findViewById(R.id.sm_header_text);
		 header.setHeight(actionBarHeight);
		
		 
		 LinearLayout header1 = (LinearLayout)findViewById(R.id.sm_header);
		 header1.setBackgroundColor(Color.DKGRAY);
		
		// Adapter um GridView damit zu füllen()
		//ButtonAdapter adapter = new ButtonAdapter(this, null);
		//GridView gridView = (GridView) findViewById(R.id.buttonframe);
		//gridView.setAdapter(adapter);
		
	
		   


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);

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
		case R.id.action_new_customer:
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ListFragment listFragment = new ListFragment();
			ft.replace(R.id.linearLayout, listFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(null);
			ft.commit();

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
	
	private void addDynamicFragment(){
		//Neue Instanz vom Fragment erzeugen
		Fragment fragment = new MainFragment();
		//Fragment zu layout hinzufügen --> Layout ist "leeres" LinearLayout
		getSupportFragmentManager().beginTransaction().add(R.id.linearLayout, fragment).commit();
	}
	
}
