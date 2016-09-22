package com.bookpond.bookpond;

import java.io.Serializable;

public final class Book implements Serializable {

    public String title;
    public String genre;
    public String id;

    @Override
    public String toString() {
        return id + " : " + title + " : " + genre;
    }

}