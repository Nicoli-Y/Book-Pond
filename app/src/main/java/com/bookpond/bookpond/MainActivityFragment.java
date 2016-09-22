package com.bookpond.bookpond;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

	ExpandableListView expandableListView;
	ExpandableListAdapter expandableListAdapter;
	List<String> expandableListId;
	View view;

	public MainActivityFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_main, container, false);

		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		MainActivity activity = (MainActivity) getActivity();
		final Map<String, List<Book>> expandableListDetail = activity.expandableListDetail;

		expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
		expandableListId = new ArrayList<>(expandableListDetail.keySet());
		expandableListAdapter = new CustomExpandableListAdapter(this.getContext(), expandableListId, expandableListDetail);
		expandableListView.setAdapter(expandableListAdapter);
		expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(MainActivityFragment.this.getContext().getApplicationContext(),
						expandableListId.get(groupPosition) + " List Expanded.",
						Toast.LENGTH_SHORT).show();
			}
		});

		expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(MainActivityFragment.this.getContext().getApplicationContext(),
						expandableListId.get(groupPosition) + " List Collapsed.",
						Toast.LENGTH_SHORT).show();

			}
		});

		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				Book book = expandableListDetail.get(
						expandableListId.get(groupPosition)).get(
						childPosition);

				Toast.makeText(
						MainActivityFragment.this.getContext().getApplicationContext(),
						expandableListId.get(groupPosition)
								+ " -> "
								+ book.title, Toast.LENGTH_SHORT
				).show();

				Intent intent = new Intent(MainActivityFragment.this.getContext(), AddEditBookActivity.class);
				intent.putExtra(Constants.EXTRA_BOOK_OBJECT, book);

				MainActivityFragment.this.getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
				MainActivityFragment.this.getActivity().startActivityForResult(intent, Constants.BOOK_EDIT);

				return false;
			}
		});

	}
}
