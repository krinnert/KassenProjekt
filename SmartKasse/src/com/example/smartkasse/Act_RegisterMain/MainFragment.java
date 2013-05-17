package com.example.smartkasse.Act_RegisterMain;

import com.example.smartkasse.R;
import com.example.smartkasse.R.id;
import com.example.smartkasse.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MainFragment extends Fragment{
	
	public static final String TAG = "MainFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRetainInstance(true);
		
		View view = inflater.inflate(R.layout.activity_main, container,false);
		
		GridView grid = (GridView)view.findViewById(R.id.buttonframe);
		
		ButtonAdapter adapter = new ButtonAdapter(getActivity(), null);
		grid.setAdapter(adapter);
		
		return view;
	}
}
