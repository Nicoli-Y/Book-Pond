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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getCanonicalName();

	Map<String, List<Book>> expandableListDetail;
	List<String> expandableListId;
	CustomExpandableListAdapter expandableListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		expandableListDetail = ExpandableListDataPump.getData(getAssets());
		expandableListId = new ArrayList<>(expandableListDetail.keySet());

		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, AddEditBookActivity.class);
				intent.putExtra(Constants.EXTRA_BOOK_OBJECT, new Book(UUID.randomUUID().toString(), null, null));
				intent.putExtra(Constants.EXTRA_IS_DELETE, false);

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

			    Shelf.addBook(expandableListDetail, expandableListId, book);

				Log.d(TAG, "added book " + book);

				Snackbar.make(mainView, "Book added", Snackbar.LENGTH_LONG)
						.setAction("Action", null)
                        .show();

			} else if (resultCode == Activity.RESULT_OK && requestCode == Constants.BOOK_EDIT) {
				boolean isDelete = data.getBooleanExtra(Constants.EXTRA_IS_DELETE, false);
				if (isDelete){
					Shelf.removeBook(expandableListDetail,expandableListId,book);

					Log.d(TAG, "Removed book " + book);
					Snackbar.make(mainView, "Book removed", Snackbar.LENGTH_LONG)
							.setAction("Action", null)
							.show();
				} else {
					Shelf.updateBook(expandableListDetail, expandableListId, book);
					Log.d(TAG, "edited book " + book);

					Snackbar.make(mainView, "Book edited", Snackbar.LENGTH_LONG)
							.setAction("Action", null)
							.show();
				}
			}
        }

		expandableListAdapter.notifyDataSetChanged();
    }

}

