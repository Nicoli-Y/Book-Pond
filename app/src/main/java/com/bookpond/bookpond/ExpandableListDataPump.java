package com.bookpond.bookpond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ExpandableListDataPump {

	public static Map<String, List<Book>> getData() {
		Map<String, List<Book>> expandableListDetail = new HashMap<>();

		List<Book> genre1 = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Book book = new Book();
			book.title = "G1 Book " + i;
			book.genre = "Genre 1";
			book.id = UUID.randomUUID().toString();
			genre1.add(book);
		}

		List<Book> genre2 = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Book book = new Book();
			book.title = "G2 Book " + i;
			book.genre = "Genre 2";
			book.id = UUID.randomUUID().toString();
			genre2.add(book);
		}

		expandableListDetail.put("Genre 1", genre1);
		expandableListDetail.put("Genre 2", genre2);
		return expandableListDetail;
	}

}
