package com.example.smartkasse.Act_RegisterMain;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.animation.Transformation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.smartkasse.R;
import com.example.smartkasse.R.id;
import com.example.smartkasse.R.layout;
import com.example.smartkasse.R.menu;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	
	
	SlidingMenu slidingMenu;
	ActionBar actionBar;
	MenuItem menu;
	
	MainFragment mainFragment;
	ListFragment listFragment;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainfragmentdynamic);
		setBehindContentView(R.layout.behind_layout);
		
		
		//Versuchen Fragmente aus dem Layout laden, beim Smartphone sind diese null
		mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
		listFragment = (ListFragment)getSupportFragmentManager().findFragmentById(R.id.listFragment);
		
		//Füge Fragmente hizu
		addFragments();
		
		//Tablets sollen nur im Landscape Modus laufen
		setLargeInLandscape();
		

		slidingMenu = getSlidingMenu();
		actionBar = getSupportActionBar();

		// SlidingMenu einrichten
		slidingMenu.setBehindWidth(350); // Wie breit wird das SlidingMenu
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);   // Modus für öffnen festlegen
																		   // wenn Menu geschlossen																																			
		slidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);  // Modus für öffnen festlegen
		 																   // wenn Menu geöffnet	

		// ActionBar einrichten
		actionBar.setHomeButtonEnabled(true); // Das Icon oben links soll als
											  // HomeButton verwendet werden
		Drawable d = getResources()
				.getDrawable(
						com.actionbarsherlock.R.drawable.abs__ab_stacked_solid_dark_holo);
		actionBar.setBackgroundDrawable(d); // Hintergrund für den Actionbar
											// festlegen

		// Header des SlidingMenus genau so hoch machen wie der Actionbar
		int actionBarHeight = getActionBarHeight();
		
		// Überschrift im Header an die ausgelesene Höhe anpassen
		TextView header = (TextView) findViewById(R.id.sm_header_text);
		header.setHeight(actionBarHeight);
		
		//Hintergrund des Oberen Balkens im LinearLayout grau machen
		LinearLayout header1 = (LinearLayout) findViewById(R.id.sm_header);
		header1.setBackgroundColor(Color.DKGRAY);

	}

	@Override //Sorgt dafür, das die Menüpunkte im ActionBar angezeigt werden
	public boolean onCreateOptionsMenu(Menu menu) {
		//Wenn das List
		if(listFragment.isInLayout()){
		getSupportMenuInflater().inflate(R.menu.main, menu);
		}
		else {
		getSupportMenuInflater().inflate(R.menu.main2, menu);
		}
		this.menu = menu.findItem(R.id.action_change_fragment);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			slidingMenu.toggle();
			break;
			
			
			
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
		
		case R.id.action_change_fragment:
			changeFragment(item);
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


	private int getActionBarHeight() {
		// Dieser Block ließt die Höhe des Actionsbar aus
		// Hierzu werden die Höhen aus dem Layout herausgezogen und in Pixel umgerechnet
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					getResources().getDisplayMetrics());

		if (actionBarHeight == 0
				&& getTheme().resolveAttribute(
						com.actionbarsherlock.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}
	
	private void addFragments(){

		//Zuerst versuchen Fragmente aus dem Layout zu laden
		mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
		listFragment = (ListFragment)getSupportFragmentManager().findFragmentById(R.id.listFragment);
		
		//Wenn die Fragmente nicht im Layout sind, füge sie dynamisch hinzu
		if(mainFragment==null&&listFragment==null){
			
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			
		mainFragment = new MainFragment();
		listFragment = new ListFragment();
		
		ft.add(R.id.linearLayout, mainFragment);
		ft.add(R.id.linearLayout, listFragment);
		ft.show(mainFragment);
		ft.hide(listFragment);
		ft.commit();
		}
	}

	private void changeFragment(MenuItem item){
		//MenuIcon ändern, je nachdem ob MainFragment oder Listfragment sichtbar ist
		if(mainFragment.isVisible()){
			item.setIcon(android.R.drawable.ic_media_play);
		}
		else{
		item.setIcon(android.R.drawable.ic_media_ff);
		}
		
		//Fragmente austauschen
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if(mainFragment.isVisible()){
		ft.hide(mainFragment);
		ft.show(listFragment);
			if(getSupportFragmentManager().findFragmentByTag("MainFragment")==null){
			ft.addToBackStack("MainFragment");
			}
		}
		else{
		ft.hide(listFragment);
		fm.popBackStack();
		}
		
		ft.commit();
	}
	
	private void  setLargeInLandscape(){
		if ((getResources().getConfiguration().screenLayout&Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {     
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		if ((getResources().getConfiguration().screenLayout&Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {     
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		
	}
}
