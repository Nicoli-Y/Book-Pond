package com.bookpond.bookpond;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


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

		MainActivity activity = (MainActivity) getActivity();
		final List<Book> availableBooks = activity.availableBooks;

		ListView listView = (ListView) view.findViewById(R.id.homeMenu) ;

		ArrayAdapter listAdapter = new BookArrayAdapter(activity, availableBooks);
		listView.setAdapter(listAdapter);

		return view;
	}

}