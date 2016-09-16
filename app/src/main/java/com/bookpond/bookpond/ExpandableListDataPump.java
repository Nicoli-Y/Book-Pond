package com.bookpond.bookpond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

	public static HashMap<String, List<String>> getData() {
		HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

		List<String> genre1 = new ArrayList<String>();
		for (int i = 1; i <= 10; i++)
			genre1.add("G1 Book " + i);

		List<String> genre2 = new ArrayList<String>();
		for (int i = 1; i <= 10; i++)
			genre2.add("G2 Book " + i);

		expandableListDetail.put("Genre 1", genre1);
		expandableListDetail.put("Genre 2", genre2);
		return expandableListDetail;
	}

}
