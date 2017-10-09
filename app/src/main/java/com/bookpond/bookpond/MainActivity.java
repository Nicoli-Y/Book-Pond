package com.bookpond.bookpond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getCanonicalName();

	//For the expandable listView
	Map<String, List<Book>> genreAndBookMap;
	List<String> genreList;
	//The adapter is customised for the books instead of strings.
	GenreAndBookExpandableListAdapter GenreAndBookExpandableListAdapter;
	//Two separate adapters since different parameters for each list
	BookArrayAdapter borrowListAdapter;
	List<Book> borrowedBooks;
	BookArrayAdapter availableListAdapter;
	List<Book> availableBooks;
	//Calling the TabLayout
	TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Using the dataPump class it gathers the books from the json files
		borrowedBooks = DataPump.getMyBorrowedBookData(getAssets());
		availableBooks = DataPump.getMyBookData(getAssets());
		genreAndBookMap = DataPump.getMyGenreAndBooksData(getAssets());
		genreList = new ArrayList<>(genreAndBookMap.keySet());
		//All of the necessary view code
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);
		setupTabIcons();
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
		//Listing actions of the editBookView (AddEditBookActivity)
		View mainView = findViewById(R.id.fragment_my_books);

		if (data != null) {
			Book book = (Book) data.getSerializableExtra(Constants.EXTRA_BOOK_OBJECT);

			if (resultCode == Activity.RESULT_OK && requestCode == Constants.BOOK_ADD) {

			    Shelf.addBook(genreAndBookMap, genreList, book);

				Log.d(TAG, "added book " + book);

				Snackbar.make(mainView, "Book added", Snackbar.LENGTH_LONG)
						.setAction("Action", null)
                        .show();

			} else if (resultCode == Activity.RESULT_OK && requestCode == Constants.BOOK_EDIT) {
				//This is where books are edited. this can be both removing or changing the name of the book/genre
				//

				boolean isDelete = data.getBooleanExtra(Constants.EXTRA_IS_DELETE, false);
				if (isDelete){
					Shelf.removeBook(genreAndBookMap, genreList,book);

					Log.d(TAG, "Removed book " + book);
					Snackbar.make(mainView, "Book removed", Snackbar.LENGTH_LONG)
							.setAction("Action", null)
							.show();
				} else {
					Shelf.updateBook(genreAndBookMap, genreList, book);
					Log.d(TAG, "edited book " + book);

					Snackbar.make(mainView, "Book edited", Snackbar.LENGTH_LONG)
							.setAction("Action", null)
							.show();
				}
			}
        }

		GenreAndBookExpandableListAdapter.notifyDataSetChanged();
    }

    // Notification for the view to refresh data
	public Book transferBook(Book transferBook){

		borrowedBooks.add(transferBook);
		borrowListAdapter.notifyDataSetChanged();

		availableBooks.remove(transferBook);
		availableListAdapter.notifyDataSetChanged();

		return transferBook;
	}
	//Creating Icons in each separate tab
	private void setupTabIcons(){
		tabLayout.getTabAt(0).setIcon(R.drawable.available);
		tabLayout.getTabAt(1).setIcon(R.drawable.my_books);
		tabLayout.getTabAt(2).setIcon(R.drawable.booked);
	}
	private void setupViewPager(ViewPager viewPager) {
		// Adding fragments
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

		AvailableFragment availableFragment = new AvailableFragment();
		availableListAdapter = new BookArrayAdapter(this, R.layout.fragment_available_books, availableBooks, true);
		availableFragment.setListViewAdapter(availableListAdapter);
		adapter.addFragment(availableFragment, "Available books");

		adapter.addFragment(new MyBooksFragment(), "My Books");

		BorrowFragment borrowFragment = new BorrowFragment();
		borrowListAdapter = new BookArrayAdapter(this, R.layout.fragment_borrow, borrowedBooks, false);
		borrowFragment.setListViewAdapter(borrowListAdapter);
		adapter.addFragment(borrowFragment, "Borrowed books");

		viewPager.setAdapter(adapter);
	}

	//Creating a customised adapter for the viewPager
	class ViewPagerAdapter extends FragmentPagerAdapter {
		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		public void addFragment(Fragment fragment, String title) {
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitleList.get(position);
		}
	}
}


