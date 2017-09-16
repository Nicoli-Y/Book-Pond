package com.bookpond.bookpond;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Shelf {

    public List<Book> books;

    public static Map<String, List<Book>> addBook(Map<String, List<Book>> genreAndBooks, Book book) {
        List<Book> bookList = genreAndBooks.get(book.genre);

        if (bookList == null) { // create a new genre if it doesn't exists yet
            bookList = new ArrayList<Book>();
            genreAndBooks.put(book.genre, bookList);
        }

        bookList.add(book);
        return genreAndBooks;
    }

    public static Map<String, List<Book>> updateBook(Map<String, List<Book>> genreAndBooks, Book book) {
        removeBook(genreAndBooks, book);
        addBook(genreAndBooks, book);

        return genreAndBooks;
    }

    public static Map<String, List<Book>> removeBook(Map<String, List<Book>> genreAndBooks, Book book) {

        List<Book> bookList = genreAndBooks.get(book.genre);
        if (bookList != null) bookList.remove(book);

        return genreAndBooks;
    }


}
