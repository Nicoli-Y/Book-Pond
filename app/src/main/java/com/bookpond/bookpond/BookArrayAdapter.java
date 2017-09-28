package com.bookpond.bookpond;

import android.content.Context;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookArrayAdapter extends ArrayAdapter {
	private final Context context;
	private final List<Book> values;

	public BookArrayAdapter(Context context, @LayoutRes int resource, List<Book> values) {
		// for now use home fragment
		super(context, resource, values); // make the layout as parameter
		this.context = context;
		this.values = values;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Book book = values.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.available_book, parent,false);
		TextView titleView = (TextView) rowView.findViewById(R.id.title);
		TextView genreView = (TextView) rowView.findViewById(R.id.genre);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.bookPhoto);
		Button button = (Button) rowView.findViewById(R.id.button);

		// only set the image if there is a imagePath
		if (book.imagePath != null) {
			int picId = context.getResources().getIdentifier(book.imagePath, "drawable", context.getApplicationContext().getPackageName());
			imageView.setImageResource(picId);
		}
		titleView.setText(book.title);
		genreView.setText(book.genre);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		// button view, add a click listener
		// pass the book into a method of MainActivity


		 return rowView;



	}


}
