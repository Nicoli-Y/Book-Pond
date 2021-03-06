package com.bookpond.bookpond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
// this is the code of the view of add_edit_book.
//* why is delete button in this class
public class AddEditBookActivity extends AppCompatActivity {

	private static final String TAG = AddEditBookActivity.class.getCanonicalName();
	TextView genre;
	TextView title;

	Book book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_book);

		Intent intent = getIntent();

		book = (Book) intent.getSerializableExtra(Constants.EXTRA_BOOK_OBJECT);

		//removing the button from the adding screen. instead of creating a new screen the remove
		// button is removed if isdelete is false/(!isdelete)
		Button deleteButton  = (Button) findViewById(R.id.button_delete);
		boolean isDelete = intent.getBooleanExtra(Constants.EXTRA_IS_DELETE, true);

		if (!isDelete) deleteButton.setVisibility(View.GONE);
		//setting the data values
		title = (TextView) findViewById(R.id.edit_title);
		if (book.title != ""){title.setText(book.title);}

		genre = (TextView) findViewById(R.id.edit_genre);
		if (book.genre != ""){genre.setText(book.genre);}
	}

    public void delete(View view) {

		Intent intent = new Intent();
		intent.putExtra(Constants.EXTRA_BOOK_OBJECT, book);
		intent.putExtra(Constants.EXTRA_IS_DELETE, true);

		setResult(Activity.RESULT_OK, intent);

		Log.d(TAG, "book deleted");
		finish();
	}
	public void save(View view) {
		//saving the data values to place in the xml
		book.title = title.getText().toString();
		book.genre = genre.getText().toString();

		Intent intent = new Intent();
		intent.putExtra(Constants.EXTRA_BOOK_OBJECT, book);

		setResult(Activity.RESULT_OK, intent);

		Log.d(TAG, "book saved");

		finish();
	}


}
