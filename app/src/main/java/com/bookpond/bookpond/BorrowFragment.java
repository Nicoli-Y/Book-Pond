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
	public BorrowFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_borrow, container, false);
		MainActivity activity = (MainActivity) getActivity();
		final List<Book> borrowedBooks = activity.borrowedBooks;

		List<String> testItems = Shelf.getTitles(borrowedBooks);

		ListView listView = (ListView) view.findViewById((R.id.borrowMenu));

		ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testItems);
		listView.setAdapter(listViewAdapter);
		return view;
	}


}

