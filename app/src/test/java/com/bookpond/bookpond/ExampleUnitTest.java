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
        System.out.println(jsonStr);

        ObjectMapper mapper = new ObjectMapper();
        Book jsonRite = mapper.readValue(jsonStr, Book.class);
        System.out.println(jsonRite.title);
        
//      creates a new test book
        Book testBook = new Book();
        testBook.title = "Hello World";
        testBook.genre = "test";
        testBook.id = "1";
        System.out.println(testBook.title);
        System.out.println(testBook.genre);
        System.out.println(testBook.id);
    }

    /*
    creates the database of id, title, genre
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