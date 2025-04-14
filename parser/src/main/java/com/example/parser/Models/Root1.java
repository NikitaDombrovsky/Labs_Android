package com.example.parser.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Root1 {
    private String message;
    private String status;
    private List<String> emails;
    // Аннотация позволяющаая написать к какому элементу это принадлежит
    @SerializedName("name")
    // Сделать пример без и с serializedname
    private Name1 full_name1;

    private List<Cart1> cart1;
    // GSON сам разбил дату
    private Date date;

    public Root1(String message, String status, List<String> emails, Name1 full_name1, List<Cart1> cart1, Date date) {
        this.message = message;
        this.status = status;
        this.emails = emails;
        this.full_name1 = full_name1;
        this.cart1 = cart1;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Name1 getFull_name() {
        return full_name1;
    }

    public void setFull_name(Name1 full_name1) {
        this.full_name1 = full_name1;
    }

    public List<Cart1> getCart() {
        return cart1;
    }

    public void setCart(List<Cart1> cart1) {
        this.cart1 = cart1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    /*    @Override
    public String toString() {
        return "Root{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", emails=" + emails +
                ", name=" + full_name +
                ", cart=" + cart +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Name getFull_name() {
        return full_name;
    }

    public void setFull_name(Name full_name) {
        this.full_name = full_name;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public Root(String message, String status, List<String> emails, Name full_name, List<Cart> cart) {
        this.message = message;
        this.status = status;
        this.emails = emails;
        this.full_name = full_name;
        this.cart = cart;
    }*/
}
