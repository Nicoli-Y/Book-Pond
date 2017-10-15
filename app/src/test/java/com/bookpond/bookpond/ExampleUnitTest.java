package com.bookpond.bookpond;

import android.provider.MediaStore;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void readBookJsonToString() throws Exception {
        //reads file
        String jsonStr = FileUtils.readFileToString(new File("app/src/test/resources/Book.json"), "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        // assigns file
        Book bookJson = mapper.readValue(jsonStr, Book.class);
        //example books
        Book bookExpected = new Book("1", "Harry Potter Philosophers Stone", "Fantasy");
        //make an equation for equals.
        assertEquals(bookExpected, bookJson);
}


    /*
    creates the database of id, title, genre,
     */
    @Test
    public void readBookFilesToInfo() throws Exception {
//        String jsonStr = FileUtils.readFileToString(new File("app/src/test/resources/Books.json"), "UTF-8");
//
//        ObjectMapper mapper = new ObjectMapper();
//        Shelf books = mapper.readValue(jsonStr, Shelf.class);

        //List<Book> testPump = DataPump.getAvailableBookData();


        List<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("1", "Harry Potter Philosophers Stone", "Fantasy" ));
        expectedList.add(new Book("2", "Diary of a Wimpy Kid", "Comedy"));
        expectedList.add(new Book("3", "アカメが斬る!", "Gore"));


        //assertArrayEquals(expectedList.toArray(), testPump.toArray());

    }

    /**
     * TODO: make Test name to be better
     */
}