package com.bookpond.bookpond;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class BorrowFragment extends Fragment {

	private BookArrayAdapter listViewAdapter; // -> Book Arr Adapter

	public BorrowFragment() {
	}

	public void setListViewAdapter(BookArrayAdapter listViewAdapter ) {
		this.listViewAdapter = listViewAdapter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_borrow, container, false);

		ListView listView = (ListView) view.findViewById((R.id.borrowMenu));
		listView.setAdapter(listViewAdapter);
		return view;
	}


}

