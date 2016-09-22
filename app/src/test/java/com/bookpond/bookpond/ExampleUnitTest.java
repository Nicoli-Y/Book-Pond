package com.bookpond.bookpond;

import android.provider.MediaStore;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void readBookJsonToString() throws Exception {
        String jsonStr = FileUtils.readFileToString(new File("Book.json"), "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        Book bookJson = mapper.readValue(jsonStr, Book.class);

        Book bookExpected = new Book("1", "Harry Potter Philosophers Stone", "Fantasy");

        assertEquals(bookExpected, bookJson);
}


    /*
    creates the database of id, title, genre,
     */
    @Test
    public void readBookFilesToInfo() throws Exception {
        String jsonStr = FileUtils.readFileToString(new File("Books.json"), "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        Shelf shelf = mapper.readValue(jsonStr, Shelf.class);
        for (int i = 0; i < shelf.books.size(); i++) {
            Book bookJson = shelf.books.get(i);

            System.out.println(bookJson);

        }

    }
}