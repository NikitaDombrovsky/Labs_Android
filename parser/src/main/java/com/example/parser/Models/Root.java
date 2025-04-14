package com.example.parser.Models; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Root{
    public String message;
    public String phoneNumber;
    // Изменили на Enum
    public Status status;
    public Name name;
    public ArrayList<Cart> cart;
    public ArrayList<String> emails;
    public Location location;
    // Изменили на дату
    public Date date;
    public boolean is_premium;
    public int total_orders;
    public double discount;
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

    public Root(String message, String phoneNumber, Status status, Name name, ArrayList<Cart> cart, ArrayList<String> emails, Location location, Date date, boolean is_premium, int total_orders, double discount, int loyalty_points) {
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
