package com.example.parser.Models.Jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class RootJS {
    @JsonProperty("message")
    public String message;
    @JsonProperty("phoneNumber")
    public String phoneNumber;
    // Изменили на Enum
    @JsonProperty("status")
    public Status status;
    @JsonProperty("name")
    public Name name;
    @JsonProperty("cart")
    public ArrayList<Cart> cart;
    @JsonProperty("emails")
    public ArrayList<String> emails;
    @JsonProperty("location")
    public Location location;
    // Изменили на дату
    @JsonProperty("date")
    public Date date;
    @JsonProperty("is_premium")
    public boolean is_premium;
    @JsonProperty("phoneNumber")
    public int total_orders;
    @JsonProperty("phoneNumber")
    public double discount;
    @JsonProperty("phoneNumber")
    public int loyalty_points;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIs_premium() {
        return is_premium;
    }

    public void setIs_premium(boolean is_premium) {
        this.is_premium = is_premium;
    }

    public int getTotal_orders() {
        return total_orders;
    }

    public void setTotal_orders(int total_orders) {
        this.total_orders = total_orders;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getLoyalty_points() {
        return loyalty_points;
    }

    public void setLoyalty_points(int loyalty_points) {
        this.loyalty_points = loyalty_points;
    }

    public RootJS(String message, String phoneNumber, Status status, Name name, ArrayList<Cart> cart, ArrayList<String> emails, Location location, Date date, boolean is_premium, int total_orders, double discount, int loyalty_points) {
        this.message = message;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.name = name;
        this.cart = cart;
        this.emails = emails;
        this.location = location;
        this.date = date;
        this.is_premium = is_premium;
        this.total_orders = total_orders;
        this.discount = discount;
        this.loyalty_points = loyalty_points;
    }
}
