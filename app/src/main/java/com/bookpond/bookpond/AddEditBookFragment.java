package com.bookpond.bookpond;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddEditBookFragment extends Fragment {

	private static final String TAG = AddEditBookFragment.class.getCanonicalName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.add_edit_book, container, false);
	}

}
