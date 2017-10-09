package com.bookpond.bookpond;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Shelf {

    public List<Book> books;

    /**
     * add a book
     * @param genreAndBooks This param is a map type taking a key set and a list of book.
     * @param genres This is to keep track of the genres being added to the application.
     * @param book this is what book is being added with the order of (id, title, genre).
     * @return
     */
    public static Map<String, List<Book>> addBook(Map<String, List<Book>> genreAndBooks, List<String> genres, Book book) {
        List<Book> bookList = genreAndBooks.get(book.genre);

        if (bookList == null) { // create a new genre if it doesn't exists yet.
            bookList = new ArrayList<Book>();
            genreAndBooks.put(book.genre, bookList);
            genres.add(book.genre);
        }

        bookList.add(book);
        return genreAndBooks;
    }

    /**
     *
     * @param genreAndBooks this is needed to locate where all the books are.
     * @param genres The selected genre the book is under.
     * @param book The selected book to update.
     * @return
     */
    public static Map<String, List<Book>> updateBook(Map<String, List<Book>> genreAndBooks, List<String> genres, Book book) {
        removeBook(genreAndBooks, genres, book);
        addBook(genreAndBooks, genres, book);

        return genreAndBooks;
    }

	/**
	 *
	 * @param genreAndBooks
	 * @param genres genres wont be needed here.
	 * @param book the targeted book to be removed.
	 * @return
	 */
	//** Genre needs to be used and deleting the genre
    public static Map<String, List<Book>> removeBook(Map<String, List<Book>> genreAndBooks, List<String> genres, Book book) {

        List<Book> bookList = genreAndBooks.get(book.genre);
        if (bookList != null) {
            bookList.remove(book);
        }

        return genreAndBooks;
    }
//?
	public static List<String> getTitles(List<Book> bookList){
		List<String> titles = new ArrayList<>();
		for ( Book aBook : bookList) {
			titles.add(aBook.title);
		}
		return titles;
	}

}
