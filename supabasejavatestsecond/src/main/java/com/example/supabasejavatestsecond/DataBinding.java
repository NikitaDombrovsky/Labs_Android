package com.example.supabasejavatestsecond;


public class DataBinding {

    private static String token;
    private static String uuidUser;

    public static String getUuidUser() {
        return uuidUser;
    }

    public static void saveUuidUser(String uuidUser) {
        DataBinding.uuidUser = uuidUser;
    }

    public static void saveBearerToken(String bearerToken) {
        token = "Bearer "+ bearerToken;
    }

    public static String getBearerToken() {
        return token;
    }
}
