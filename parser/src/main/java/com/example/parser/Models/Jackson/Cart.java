package com.example.parser.Models.Jackson;
public class Cart{
    public String book_title;
    public String book_genre;
    public String book_author;
    public double book_price;
    public double book_rating;
    public int page_count;

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

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public double getBook_rating() {
        return book_rating;
    }

    public void setBook_rating(double book_rating) {
        this.book_rating = book_rating;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public Cart(String book_title, String book_genre, String book_author, double book_price, double book_rating, int page_count) {
        this.book_title = book_title;
        this.book_genre = book_genre;
        this.book_author = book_author;
        this.book_price = book_price;
        this.book_rating = book_rating;
        this.page_count = page_count;
    }
}
