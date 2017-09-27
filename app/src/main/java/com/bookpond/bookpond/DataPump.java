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

	private static final String TAG = DataPump.class.getCanonicalName();

	public static List<Book> getMyBorrowedBookData(AssetManager assetManager) {

		Shelf shelf;
		try {
			String jsonStr = IOUtils.toString(assetManager.open("BorrowedBooks.json"), "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			shelf = mapper.readValue(jsonStr, Shelf.class);
		} catch (Exception e) {
			Log.e(TAG, "error in getting data", e);
			shelf = new Shelf();
		}

		return shelf.books;
	}
	public static List<Book> getMyBookData(AssetManager assetManager) {

		Shelf shelf;
		try {
			String jsonStr = IOUtils.toString(assetManager.open("AvailableBooks.json"), "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			shelf = mapper.readValue(jsonStr, Shelf.class);
		} catch (Exception e) {
			Log.e(TAG, "error in getting data", e);
			shelf = new Shelf();
		}

		return shelf.books;
	}
	public static Map<String, List<Book>> getMyGenreAndBooksData(AssetManager assetManager) {
		Map<String, List<Book>> genreAndBooks;

		Shelf books;
		try {
			String jsonStr = IOUtils.toString(assetManager.open("Books.json"), "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			books = mapper.readValue(jsonStr, Shelf.class);
		} catch (Exception e) {
			Log.e(TAG, "error in getting data", e);
			books = new Shelf();
		}

		Function<Book, String> sameGenre = new Function<Book, String>() {
			@Override
			public String apply(Book book) {
				return book.genre;
			}
		};

		ArrayListMultimap<String, Book> index =
				ArrayListMultimap.create(Multimaps.index(books.books, sameGenre));

		genreAndBooks = new HashMap<>(Multimaps.asMap(index));


		return genreAndBooks;
	}


}
