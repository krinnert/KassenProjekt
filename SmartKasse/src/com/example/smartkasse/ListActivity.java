package com.example.smartkasse;


import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class ListActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// TODO Auto-generated method stub
		setContentView(R.layout.listfragment);
		
		
		getSupportActionBar().setHomeButtonEnabled(true);
		
		
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
}
