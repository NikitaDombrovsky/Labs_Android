package com.example.parser.Models; 
public class Location{
    public String street;
    public String city;
    public String country;
    public Coordinates coordinates;
    public boolean delivery_available;
    public double delivery_radius;
    public double delivery_cost;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isDelivery_available() {
        return delivery_available;
    }

    public void setDelivery_available(boolean delivery_available) {
        this.delivery_available = delivery_available;
    }

    public double getDelivery_radius() {
        return delivery_radius;
    }

    public void setDelivery_radius(double delivery_radius) {
        this.delivery_radius = delivery_radius;
    }

    public double getDelivery_cost() {
        return delivery_cost;
    }

    public void setDelivery_cost(double delivery_cost) {
        this.delivery_cost = delivery_cost;
    }

    public Location(String street, String city, String country, Coordinates coordinates, boolean delivery_available, double delivery_radius, double delivery_cost) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.coordinates = coordinates;
        this.delivery_available = delivery_available;
        this.delivery_radius = delivery_radius;
        this.delivery_cost = delivery_cost;
    }
}
