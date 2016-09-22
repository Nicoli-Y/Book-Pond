package com.bookpond.bookpond;

import android.content.res.AssetManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;

import java.util.*;

import com.google.common.base.Function;
import org.apache.commons.io.IOUtils;

public class ExpandableListDataPump {

	public static Map<String, List<Book>> getData(AssetManager assetManager) {
		Map<String, List<Book>> expandableListDetail;

		Shelf books;
		try {
			String jsonStr = IOUtils.toString(assetManager.open("Books.json"), "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			books = mapper.readValue(jsonStr, Shelf.class);
		} catch (Exception e) {
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

		expandableListDetail = new HashMap<>(Multimaps.asMap(index));


		return expandableListDetail;
	}

}
