package com.bookpond.bookpond;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HomeFragment extends Fragment{

	public HomeFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		String[] testItems = {"item 1", "item 2", "item 3"};

		ListView listView= (ListView) view.findViewById((R.id.homeMenu));
		ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
				getActivity(),
				android.R.layout.simple_list_item_1,
				testItems
		);
		listView.setAdapter((listViewAdapter));

		return view;
	}

}