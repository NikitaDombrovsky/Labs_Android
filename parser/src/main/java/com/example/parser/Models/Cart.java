package com.example.parser.Models;

import com.google.gson.annotations.SerializedName;

public class Cart{
    @SerializedName("book_title")
    public String title;
    @SerializedName("book_genre")
    public String genre;
    @SerializedName("book_author")
    public String author;
    @SerializedName("book_price")
    public double price;
    @SerializedName("book_rating")
    public double rating;
    public int page_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public Cart(String title, String genre, String author, double price, double rating, int page_count) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.page_count = page_count;
    }
}
