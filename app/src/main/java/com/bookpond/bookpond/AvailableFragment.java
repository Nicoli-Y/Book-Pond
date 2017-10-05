package com.bookpond.bookpond;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class AvailableFragment extends Fragment{

	private BookArrayAdapter listViewAdapter;


	public AvailableFragment() {
	}

	public void setListViewAdapter(BookArrayAdapter listViewAdapter ) {
		this.listViewAdapter = listViewAdapter;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_available_books, container, false);

		ListView listView = (ListView) view.findViewById(R.id.homeMenu) ;
		listView.setAdapter(listViewAdapter);

		return view;
	}

}