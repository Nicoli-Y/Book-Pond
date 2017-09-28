package com.bookpond.bookpond;

import android.content.res.AssetManager;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

import java.util.*;

import com.google.common.base.Function;
import org.apache.commons.io.IOUtils;

public class DataPump {

	public static final String TAG = DataPump.class.getCanonicalName();

	public static List<Book> getMyBorrowedBookData(AssetManager assetManager) {


		List<Book> borrowedBooks = GetBookData.getData(assetManager, "BorrowedBooks");
		return borrowedBooks;

	}
	public static List<Book> getMyBookData(AssetManager assetManager) {

		List<Book> AvailableBooks = GetBookData.getData(assetManager, "AvailableBooks");
		return AvailableBooks;
	}
	public static Map<String, List<Book>> getMyGenreAndBooksData(AssetManager assetManager) {
		Map<String, List<Book>> genreAndBooks;

		Shelf shelf;
		try {
			String jsonStr = IOUtils.toString(assetManager.open("Books.json"), "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			shelf = mapper.readValue(jsonStr, Shelf.class);
		} catch (Exception e) {
			Log.e(TAG, "error in getting data", e);
			shelf = new Shelf();
		}

		Function<Book, String> sameGenre = new Function<Book, String>() {
			@Override
			public String apply(Book book) {
				return book.genre;
			}
		};

		ArrayListMultimap<String, Book> index =
				ArrayListMultimap.create(Multimaps.index(shelf.books, sameGenre));

		genreAndBooks = new HashMap<>(Multimaps.asMap(index));


		return genreAndBooks;
	}


}
