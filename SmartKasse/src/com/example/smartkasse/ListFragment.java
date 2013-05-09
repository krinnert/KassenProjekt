package com.example.smartkasse;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		String[] arr = {"Artikelübersicht","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test"};
		
		View view = inflater.inflate(R.layout.list, container,false);
		

		ListView list = (ListView)view.findViewById(R.id.list);
	
		ListAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, arr);
		
		list.setAdapter(adapter);
		
		return view;
	}

}
