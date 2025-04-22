package com.example.heterogenrecyclermodule;


public class User {
    public String name;
    public String hometown;
    public int avatarRes;

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }
    public User(String name, int  avatarRes) {
        this.name = name;
        this.avatarRes = avatarRes;
    }
}


