package com.bookpond.bookpond;


import android.content.res.AssetManager;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.util.List;

import static com.bookpond.bookpond.DataPump.TAG;
//Method to get the Book Data
public class GetBookData {
	public static List<Book> getData(AssetManager assetManager, String books) {
		Shelf shelf;
		try {
			String jsonStr = IOUtils.toString(assetManager.open(books + ".json"), "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			shelf = mapper.readValue(jsonStr, Shelf.class);
		} catch (Exception e) {
			Log.e(TAG, "error in getting data", e);
			shelf = new Shelf();
		}

		return shelf.books;

	}
}