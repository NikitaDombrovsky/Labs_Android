package com.example.supabasejavatestsecond.Models;

/*public class Auth {
    public int code;
    public String error_code;
    public String msg;
}*/
public class Auth{
    private String access_token;
    private String refresh_token;
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

