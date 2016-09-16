package com.bookpond.bookpond;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

	ExpandableListView expandableListView;
	ExpandableListAdapter expandableListAdapter;
	List<String> expandableListTitle;
	HashMap<String, List<String>> expandableListDetail;
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

		expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
		expandableListDetail = ExpandableListDataPump.getData();
		expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
		expandableListAdapter = new CustomExpandableListAdapter(this.getContext(), expandableListTitle, expandableListDetail);
		expandableListView.setAdapter(expandableListAdapter);
		expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(MainActivityFragment.this.getContext().getApplicationContext(),
						expandableListTitle.get(groupPosition) + " List Expanded.",
						Toast.LENGTH_SHORT).show();
			}
		});

		expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(MainActivityFragment.this.getContext().getApplicationContext(),
						expandableListTitle.get(groupPosition) + " List Collapsed.",
						Toast.LENGTH_SHORT).show();

			}
		});

		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				Toast.makeText(
						MainActivityFragment.this.getContext().getApplicationContext(),
						expandableListTitle.get(groupPosition)
								+ " -> "
								+ expandableListDetail.get(
								expandableListTitle.get(groupPosition)).get(
								childPosition), Toast.LENGTH_SHORT
				).show();
				return false;
			}
		});

	}
}
