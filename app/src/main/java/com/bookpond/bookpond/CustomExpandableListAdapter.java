package com.bookpond.bookpond;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> expandableListId;
	private Map<String, List<Book>> expandableListDetail;

	public CustomExpandableListAdapter(Context context, List<String> expandableListId,
									   Map<String, List<Book>> expandableListDetail) {
		this.context = context;
		this.expandableListId = expandableListId;
		this.expandableListDetail = expandableListDetail;
	}

	@Override
	public Object getChild(int listPosition, int expandedListPosition) {
		return this.expandableListDetail.get(this.expandableListId.get(listPosition))
				.get(expandedListPosition);
	}

	@Override
	public long getChildId(int listPosition, int expandedListPosition) {
		return expandedListPosition;
	}

	@Override
	public View getChildView(int listPosition, final int expandedListPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		final Book child = (Book) getChild(listPosition, expandedListPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.list_item, null);
		}
		TextView expandedListTextView = (TextView) convertView
				.findViewById(R.id.expandedListItem);
		expandedListTextView.setText(child.title);
		return convertView;
	}

	@Override
	public int getChildrenCount(int listPosition) {
		return this.expandableListDetail.get(this.expandableListId.get(listPosition))
				.size();
	}

	@Override
	public Object getGroup(int listPosition) {
		return this.expandableListId.get(listPosition);
	}

	@Override
	public int getGroupCount() {
		return this.expandableListId.size();
	}

	@Override
	public long getGroupId(int listPosition) {
		return listPosition;
	}

	@Override
	public View getGroupView(int listPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		String listTitle = (String) getGroup(listPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.list_group, null);
		}
		TextView listTitleTextView = (TextView) convertView
				.findViewById(R.id.listTitle);
		listTitleTextView.setTypeface(null, Typeface.BOLD);
		listTitleTextView.setText(listTitle);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int listPosition, int expandedListPosition) {
		return true;
	}
}