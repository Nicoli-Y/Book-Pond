package com.bookpond.bookpond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AddEditBookActivity extends AppCompatActivity {

	private static final String TAG = AddEditBookActivity.class.getCanonicalName();
	TextView genre;
	TextView title;

	Book book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_book);

		View view = findViewById(R.id.add_edit_book_fragment);

		Intent intent = getIntent();
		book = (Book) intent.getSerializableExtra(Constants.EXTRA_BOOK_OBJECT);

		title = (TextView) findViewById(R.id.edit_title);
		title.setText(book.title);

		genre = (TextView) findViewById(R.id.edit_genre);
		genre.setText(book.genre);
	}

	public void save(View view) {


		book.title = title.getText().toString();
		book.genre = genre.getText().toString();

		Intent intent = new Intent();
		intent.putExtra(Constants.EXTRA_BOOK_OBJECT, book);

		setResult(Activity.RESULT_OK, intent);

		Log.d(TAG, "book saved");

		finish();
	}


}
