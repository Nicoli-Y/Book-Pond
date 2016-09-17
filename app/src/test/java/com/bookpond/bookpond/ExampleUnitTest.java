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
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void readFileToString() throws Exception {
        String jsonStr = FileUtils.readFileToString(new File("Book.json"), "UTF-8");
        System.out.println(jsonStr);

        Book book1 = new Book();
        book1.title = "Hello World";
        System.out.println(book1.title);

        ObjectMapper mapper = new ObjectMapper();
        Book jsnRite = mapper.readValue(jsonStr, Book.class);
        System.out.println(jsnRite.title);

    }
}