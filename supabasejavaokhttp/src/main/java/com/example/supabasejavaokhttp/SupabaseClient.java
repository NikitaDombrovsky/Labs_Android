package com.example.supabasejavaokhttp;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupabaseClient {
    private static final String SUPABASE_URL = "https://nyfvntiwcbozwdzxguuh.supabase.co/rest/v1/todos?select=*";
    private static final String SUPABASE_URL_ = "https://nyfvntiwcbozwdzxguuh.supabase.co/rest/v1/";
    private static final String SUPABASE_URL_A = "https://nyfvntiwcbozwdzxguuh.supabase.co/";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY";



    public void fetchProfile(final Callback callback){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
      //  MediaType mediaType = MediaType.parse("text/plain");
       // RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://nyfvntiwcbozwdzxguuh.supabase.co/rest/v1/profiles?id=eq.4125a011-75aa-4f45-a573-317dcc0242f0")
                //.method("GET", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
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
    public void updateProfile(final Callback callback){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"username\": \"among\",\r\n    \"avatar_url\": \"us\"\r\n}");
        Request request = new Request.Builder()
                .url("https://nyfvntiwcbozwdzxguuh.supabase.co/rest/v1/profiles?id=eq.4125a011-75aa-4f45-a573-317dcc0242f0")
                .method("PATCH", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6Im1kZUV3Zi9zOHpzMEU5SWUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL255ZnZudGl3Y2JvendkenhndXVoLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI0MTI1YTAxMS03NWFhLTRmNDUtYTU3My0zMTdkY2MwMjQyZjAiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzQ1MjkyNzkyLCJpYXQiOjE3NDUyODkxOTIsImVtYWlsIjoibmlraXRhLm5pa2l0YTU1QG1haWwucnUiLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsIjoibmlraXRhLm5pa2l0YTU1QG1haWwucnUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGhvbmVfdmVyaWZpZWQiOmZhbHNlLCJzdWIiOiI0MTI1YTAxMS03NWFhLTRmNDUtYTU3My0zMTdkY2MwMjQyZjAifSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc0NTI4OTE5Mn1dLCJzZXNzaW9uX2lkIjoiMTRiZDgyMWYtZDkwNS00MDIyLTkyN2MtNjUxOWRhNWU4OGQ3IiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.v1hJWBnf_3yo9Xi1FTTh8bqTEwSQcIaMn_Zwe3dFnQM")
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=minimal")
                .build();
       // Response response = client.newCall(request).execute();
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
    public void loginWithEmail(String email, String password, final Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        // TODO Поменять на toJSON
        RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"nikita.nikita55@mail.ru\",\n  \"password\": \"BfqSzeuiPCzhurwWIQKE\"\n}");
        //  RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"" + email + "\",\n  \"password\": \"" + password + "\"\n}");
        Request request = new Request.Builder()
                //.url("https://nyfvntiwcbozwdzxguuh.supabase.co/auth/v1/signup")
                .url(SUPABASE_URL_A + "auth/v1/token?grant_type=password")
                .method("POST", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
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
    public void authWithEmail(String email, String password, final Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        // TODO Поменять на toJSON
        RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"nikita.nikita55@mail.ru\",\n  \"password\": \"BfqSzeuiPCzhurwWIQKE\"\n}");
      //  RequestBody body = RequestBody.create(mediaType, "{\n  \"email\": \"" + email + "\",\n  \"password\": \"" + password + "\"\n}");
        Request request = new Request.Builder()
                //.url("https://nyfvntiwcbozwdzxguuh.supabase.co/auth/v1/signup")
                .url(SUPABASE_URL_A + "auth/v1/signup")
                .method("POST", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
               // .addHeader("Content-Type", "application/json")
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

    public void getOrders(final Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
/*        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");*/
        Request request = new Request.Builder()
                //.url("https://nyfvntiwcbozwdzxguuh.supabase.co/rest/v1/orders?select=*,categoryes(*)")
                .url(SUPABASE_URL_ + "orders?select=*,categoryes(*)")
                //.method("GET", body)
                .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im55ZnZudGl3Y2JvendkenhndXVoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUyMDM5NjUsImV4cCI6MjA2MDc3OTk2NX0.PKIvlCiOShzvKxyTwA8bBXUYr0xW5FTCvYQhIJ9vxfY")
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

    public void fetchTodos(final Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        //MediaType mediaType = MediaType.parse("text/plain");
        //  RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(SUPABASE_URL)
                //.method("GET", body)
                // .method("GET")
                .addHeader("apikey", API_KEY)
                //.addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    callback.onSuccess(responseData);
                } else {
                    callback.onFailure(new IOException("Unexpected code " + response));
                }
            }
        });
    }

    public interface Callback {
        void onSuccess(String response);

        void onFailure(Throwable t);
    }
}
