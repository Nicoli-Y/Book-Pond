package com.bookpond.bookpond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getCanonicalName();
	Map<String, List<Book>> expandableListDetail = ExpandableListDataPump.getData();
	CustomExpandableListAdapter expandableListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent(MainActivity.this, AddEditBookActivity.class);
				intent.putExtra(Constants.EXTRA_BOOK_OBJECT, new Book(UUID.randomUUID().toString(), null, "Genre 1"));
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

				startActivityForResult(intent, Constants.BOOK_ADD);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		View mainView = findViewById(R.id.fragment);

		if (data != null) {
			Book book = (Book) data.getSerializableExtra(Constants.EXTRA_BOOK_OBJECT);

			if (resultCode == Activity.RESULT_OK && requestCode == Constants.BOOK_ADD) {
				List<Book> books = expandableListDetail.get(book.genre);

				if (books == null) { // create a new genre if it doesn't exists yet
					books = new ArrayList<Book>();
					expandableListDetail.put(book.genre, books);
				}

				books.add(book);

				Log.d(TAG, "added book " + book);

				Snackbar.make(mainView, "Book added", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();

			} else if (resultCode == Activity.RESULT_OK && requestCode == Constants.BOOK_EDIT) {
				List<Book> books = expandableListDetail.get(book.genre);

				if (books == null) { // create a new genre if it doesn't exists yet
					books = new ArrayList<Book>();
					expandableListDetail.put(book.genre, books);
				}

				if (books.remove(book)) // update by remove and add
					books.add(book);

				Log.d(TAG, "edited book " + book);

				Snackbar.make(mainView, "Book edited", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}

			expandableListAdapter.notifyDataSetChanged();
		}

	}
}
