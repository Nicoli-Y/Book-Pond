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
    /*
    This shows that there is a set of data premade in the test file then testing the
    addbook making an array equals to see if it has the right information
    */
    public void shouldAddABookOnBlankGenre() {
        Map<String, List<Book>> genreAndBooks = new HashMap<>();

        Book book = new Book("1", "title", "genre");

        Map<String, List<Book>> expectedGenreAndBooks = new HashMap<>();
        expectedGenreAndBooks.put("genre", Arrays.asList(book));

        List<String> genres = new ArrayList<>(genreAndBooks.keySet());

        Map<String, List<Book>> resultGenreAndBooks = Shelf.addBook(genreAndBooks, genres, book);

        assertArrayEquals(expectedGenreAndBooks.get("genre").toArray(), resultGenreAndBooks.get("genre").toArray());

        String[] expectedGenres = {"genre"};
        assertArrayEquals(expectedGenres, genres.toArray());
    }

    @Test
    /*
    Removing a book is also tested from the shelf class
    */

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

        List<String> genres = new ArrayList<>(genreAndBooks.keySet());

        Map<String, List<Book>> resultGenreAndBooks = Shelf.removeBook(genreAndBooks, genres, book2);

        assertArrayEquals(expectedGenreAndBooks.get("genre").toArray(), resultGenreAndBooks.get("genre").toArray());
    }

    @Test
    public void shouldUpdateABookOnAGenre() {
	    //updating a book needs to be also tested

        Book book1 = new Book("1", "title", "genre");
        Book book2 = new Book("2", "title2", "genre");
        Book updatedBook = new Book("2", "updated title", "genre");
        Book updatedBook2 = new Book("2", "updated title", "genre");

        Map<String, List<Book>> expectedGenreAndBooks = new HashMap<>();
        expectedGenreAndBooks.put("genre", Arrays.asList(book1,updatedBook));

        Map<String, List<Book>> genreAndBooks = new HashMap<>();
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        genreAndBooks.put("genre", books);

        List<String> genres = new ArrayList<>(genreAndBooks.keySet());

        Map<String, List<Book>> resultGenreAndBooks = Shelf.updateBook(genreAndBooks, genres, updatedBook2);

        List<String> expectedTitles = getTitles(expectedGenreAndBooks.get("genre"));
        List<String> resultTitles = getTitles(resultGenreAndBooks.get("genre"));

        assertArrayEquals(expectedTitles.toArray(), resultTitles.toArray());

    }
// this is within the shelf class.
    public List<String> getTitles(List<Book> bookList){
        List<String> titles = new ArrayList<>();
        for ( Book aBook : bookList) {
            titles.add(aBook.title);
        }
        return titles;
    }

}