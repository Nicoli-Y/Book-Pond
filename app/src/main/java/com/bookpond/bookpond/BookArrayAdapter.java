package com.bookpond.bookpond;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookArrayAdapter extends ArrayAdapter {
	private final Context context;
	private final List<Book> values;

	public BookArrayAdapter(Context context, List<Book> values) {
		// for now use home fragment
		super(context, R.layout.fragment_home,values);
		this.context = context;
		this.values = values;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Book book = values.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.home_book, parent,false);
		TextView titleView = (TextView) rowView.findViewById(R.id.title);
		TextView genreView = (TextView) rowView.findViewById(R.id.genre);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.bookPhoto);

		int picId = context.getResources().getIdentifier(book.imagePath, "drawable", context.getApplicationContext().getPackageName());

		titleView.setText(book.title);
		genreView.setText(book.genre);
		imageView.setImageResource(picId);


		 return rowView;



	}


}
