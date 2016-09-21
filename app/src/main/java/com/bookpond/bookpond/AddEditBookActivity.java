package com.bookpond.bookpond;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddEditBookActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_book);

		View view = findViewById(R.id.add_edit_book_fragment);

		Snackbar.make(view, "Add a book to the pond", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();

	}

}
