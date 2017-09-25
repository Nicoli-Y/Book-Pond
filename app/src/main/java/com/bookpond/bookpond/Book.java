package com.bookpond.bookpond;

import java.io.Serializable;

public final class Book implements Serializable {

    public String title;
    public String genre;
    public String id;

    public Book() {
        super();
    }

    /**
     *
     * @param id the id of the book
     * @param title title of the book
     * @param genre the genre of the book
     */
    public Book (String id, String title, String genre){
        this();

        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return id + " : " + title + " : " + genre;
    }

    /**
     *
     * @param that this is how we make books "equals" to each other.By using the id
     * if the id is the same then it must mean the book is the same.
     */
    @Override
    public boolean equals(Object that){
        if (that instanceof Book) {
            Book book = (Book) that;
            if (book.id.equals(this.id))
                return true;
            else
                return false;

        } else {
            return false;
        }

    }


}