package com.bookpond.bookpond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class AddEditBookActivity extends AppCompatActivity {

	private static final String TAG = AddEditBookActivity.class.getCanonicalName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_book);

		View view = findViewById(R.id.add_edit_book_fragment);

		Snackbar.make(view, "Add a book to the pond", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();

	}

	public void save(View view) {

		Log.d(TAG, "book saved");

		Intent intent = new Intent();
		intent.putExtra(Constants.EXTRA_BOOK_OBJECT, "the book");

		setResult(Activity.RESULT_OK, intent);

		finish();
	}


}
