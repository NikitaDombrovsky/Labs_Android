package com.example.supabasejavatestsecond.Models;

public class Profile{
    private String username;
    private String avatar_url;

    public Profile(String username, String avatar_url) {
        this.username = username;
        this.avatar_url = avatar_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
