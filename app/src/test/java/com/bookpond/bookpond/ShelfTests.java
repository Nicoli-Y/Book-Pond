package com.bookpond.bookpond;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ShelfTests {

    @Test
    public void shouldAddABookOnBlankGenre() {
        Map<String, List<Book>> genreAndBooks = new HashMap<>();

        Book book = new Book("1", "title", "genre");

        Map<String, List<Book>> expectedGenreAndBooks = new HashMap<>();
        expectedGenreAndBooks.put("genre", Arrays.asList(book));

        Map<String, List<Book>> resultGenreAndBooks = Shelf.addBook(genreAndBooks, book);

        assertArrayEquals(expectedGenreAndBooks.get("genre").toArray(), resultGenreAndBooks.get("genre").toArray());
    }

    @Test
    public void shouldRemoveABookOnAGenre() {

        Book book1 = new Book("1", "title", "genre");
        Book book2 = new Book("2", "title2", "genre");

        Map<String, List<Book>> expectedGenreAndBooks = new HashMap<>();
        expectedGenreAndBooks.put("genre", Arrays.asList(book1));

        Map<String, List<Book>> genreAndBooks = new HashMap<>();
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        genreAndBooks.put("genre", books);

        Map<String, List<Book>> resultGenreAndBooks = Shelf.removeBook(genreAndBooks, book2);

        assertArrayEquals(expectedGenreAndBooks.get("genre").toArray(), resultGenreAndBooks.get("genre").toArray());
    }

}
