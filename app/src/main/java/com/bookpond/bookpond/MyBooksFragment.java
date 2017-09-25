package com.bookpond.bookpond;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyBooksFragment extends Fragment {

	public MyBooksFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_my_books, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		MainActivity activity = (MainActivity) getActivity();
		final Map<String, List<Book>> expandableListDetail = activity.expandableListDetail;
		final List<String> expandableListId = activity.expandableListId;

		ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
		activity.expandableListAdapter = new CustomExpandableListAdapter(this.getContext(), expandableListId, expandableListDetail);
		expandableListView.setAdapter(activity.expandableListAdapter);
		expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(MyBooksFragment.this.getContext().getApplicationContext(),
						"Opened " + expandableListId.get(groupPosition) + " :)",
						Toast.LENGTH_SHORT).show();
			}
		});

		expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(MyBooksFragment.this.getContext().getApplicationContext(),
						"Closed " + expandableListId.get(groupPosition) + " :)",
						Toast.LENGTH_SHORT).show();

			}
		});

		//this is where we get the book data from the json file and transfer it to the xml
		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				Book book = expandableListDetail.get(
						expandableListId.get(groupPosition)).get(
						childPosition);

				Intent intent = new Intent(MyBooksFragment.this.getContext(), AddEditBookActivity.class);
				intent.putExtra(Constants.EXTRA_BOOK_OBJECT, book);

				MyBooksFragment.this.getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
				MyBooksFragment.this.getActivity().startActivityForResult(intent, Constants.BOOK_EDIT);

				return false;
			}
		});

		FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MyBooksFragment.this.getContext(), AddEditBookActivity.class);
				intent.putExtra(Constants.EXTRA_BOOK_OBJECT, new Book(UUID.randomUUID().toString(), null, null));
				intent.putExtra(Constants.EXTRA_IS_DELETE, false);

				MyBooksFragment.this.getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
				MyBooksFragment.this.getActivity().startActivityForResult(intent, Constants.BOOK_ADD);

			}
		});

	}
}
