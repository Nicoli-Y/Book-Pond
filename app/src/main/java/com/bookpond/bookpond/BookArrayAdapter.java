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
	//private final MainActivity mainActivity; // change main activity
	private final List<Book> values;
	MainActivity mainActivity = new MainActivity();
	//To use for the if statement
	boolean availableButton;

	public BookArrayAdapter(MainActivity mainActivity, @LayoutRes int resource, List<Book> values, boolean availableButton) {
		// (this.) is only for this class
		super(mainActivity, resource, values); // make the layout as parameter
		this.mainActivity = mainActivity;
		this.values = values;
		this.availableButton = availableButton;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		final Book book = values.get(position);
		LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View rowView = inflater.inflate(R.layout.available_book, parent,false);
		//All of the views in the availablebook tab (title,genre,photo, borrowing button)
		TextView titleView = (TextView) rowView.findViewById(R.id.title);
		TextView genreView = (TextView) rowView.findViewById(R.id.genre);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.bookPhoto);
		Button button = (Button) rowView.findViewById(R.id.button);

		//Removing the button in the other screens
		if (!availableButton) {
			button.setVisibility(View.INVISIBLE);
		}
		// This is how to get the pictures using id
		if (book.imagePath != null) {
			int picId = mainActivity.getResources().getIdentifier(book.imagePath, "drawable", mainActivity.getApplicationContext().getPackageName());
			imageView.setImageResource(picId);
		}
		titleView.setText(book.title);
		genreView.setText(book.genre);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//This needs method needs to be in the mainactivity because the datapump is within the class
				mainActivity.transferBook(book);


			}
		});


		 return rowView;



	}


}
