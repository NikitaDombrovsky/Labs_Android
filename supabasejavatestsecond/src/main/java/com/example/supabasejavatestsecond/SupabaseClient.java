package com.example.supabasejavatestsecond;

import androidx.annotation.NonNull;

import com.example.supabasejavatestsecond.Models.LoginRequest;
import com.example.supabasejavatestsecond.Models.Profile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseClient {
    public static final String SUPABASE_DOMEN = "https://wbxvsdaywhpmmxwjthcg.supabase.co/";
    public static final String SUPABASE_REST = "rest/v1/";
    public static final String SUPABASE_AUTH = "auth/v1/";
    public static final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndieHZzZGF5d2hwbW14d2p0aGNnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU0NjIyNDIsImV4cCI6MjA2MTAzODI0Mn0.1OLpZnsHWnbB4LscK48GvnFUIUiE4l2LJX_zuAaKMmo";

    public interface SBCallback {
        void onFailure(Throwable t);

        void onSuccess(String response);
    }

    private final OkHttpClient client = new OkHttpClient();

    public void fetchProfile(final SBCallback callback) {
        String bearerToken = DataBinding.getBearerToken();
        String uuidUser = DataBinding.getUuidUser();
        Request request = new Request.Builder()
                .url(SUPABASE_DOMEN + SUPABASE_REST + "profiles?id=eq." + uuidUser)
                .addHeader("Authorization", bearerToken)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    callback.onSuccess(responseData);
                } else {
                    callback.onFailure(new IOException("Unexpected code " + response));
                }
            }
        });
    }

    public void updateProfile(Profile profile, final SBCallback callback) {
        String bearerToken = DataBinding.getBearerToken();
        String uuidUser = DataBinding.getUuidUser();

        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        RequestBody body = RequestBody.create(json, mediaType);

        String url = SUPABASE_DOMEN + SUPABASE_REST + "profiles?id=eq." + uuidUser;

        Request request = new Request.Builder()
                .url(url)
                .method("PATCH", body)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", bearerToken)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=minimal")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } else {
                    callback.onFailure(new IOException("Exception code: " + response));
                }

            }
        });
    }


    public void loginUserWithEmail(LoginRequest loginRequest, final SBCallback callback) {
        MediaType mediaType = MediaType.parse("application/json");

        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(SUPABASE_DOMEN + SUPABASE_AUTH + "token?grant_type=password")
                .method("POST", body)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } else {
                    callback.onFailure(new IOException("Exception code: " + response));
                }

            }
        });
    }


    public void authUserWithEmail(LoginRequest loginRequest, final SBCallback callback) {
        MediaType mediaType = MediaType.parse("application/json");

        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);

        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(SUPABASE_DOMEN + SUPABASE_AUTH + "signup")
                .method("POST", body)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } else {
                    callback.onFailure(new IOException("Exception code: " + response));
                }

            }
        });
    }

    public void fetchUserOrders(final SBCallback callback) {

        String bearerToken = DataBinding.getBearerToken();
        String uuidUser = DataBinding.getUuidUser();

        Request request = new Request.Builder()

                .url(SUPABASE_DOMEN + SUPABASE_REST + "orders?select=*,category(*)&id_profile=eq." + uuidUser)
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", bearerToken)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } else {
                    callback.onFailure(new IOException("Exception code: " + response));
                }

            }
        });
    }

    public void fetchOrders(final SBCallback callback) {
        String bearerToken = DataBinding.getBearerToken();

        Request request = new Request.Builder()
                .url(SUPABASE_DOMEN + SUPABASE_REST + "orders?select=*,category(*)")
                .addHeader("apikey", SUPABASE_API_KEY)
                .addHeader("Authorization", bearerToken)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } else {
                    callback.onFailure(new IOException("Exception code: " + response));
                }

            }
        });
    }
}
