package com.example.parser.Models;

public class Cart1 {
    private String book_title;
    private String book_genre;
    private String book_author;

    public Cart1(String book_title, String book_genre, String book_author) {
        this.book_title = book_title;
        this.book_genre = book_genre;
        this.book_author = book_author;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_genre() {
        return book_genre;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }
}
