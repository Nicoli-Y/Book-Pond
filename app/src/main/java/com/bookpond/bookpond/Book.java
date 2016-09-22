package com.bookpond.bookpond;

import java.io.Serializable;

public final class Book implements Serializable {

    public String title;
    public String genre;
    public String id;

    public Book() {
        super();
    }

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