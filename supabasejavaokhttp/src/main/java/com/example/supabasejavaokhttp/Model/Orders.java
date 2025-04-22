package com.example.supabasejavaokhttp.Model;

public class Orders {


    private int id;
    private String name;
    private int id_category;
    private String date;
    private String image;
    private String id_user;
    private Category categoryes;

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdCategory() {
        return id_category;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getIdUser() {
        return id_user;
    }

    public Category getCategoryes() {
        return categoryes;
    }
}
