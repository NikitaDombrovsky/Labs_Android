package com.example.network_okhttp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpClass {
    private final OkHttpClient client = new OkHttpClient();

    public String testGetRequest(String urlString) throws IOException {
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Log.e("Code", "" + response.code() + "|" + response.message());
            return response.body().string();
        }
    }
    public String testGetRequestWithQuery(String urlString) throws IOException {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlString).newBuilder();
        urlBuilder.addQueryParameter("website", "www.journaldev.com");
        urlBuilder.addQueryParameter("tutorials", "android");
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Log.e("Code", "" + response.code() + "|" + response.message());
            return response.body().string();
        }
    }
    public String testGetRequestWithHeaders(String urlString) throws IOException {

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Lab")
                .addHeader("Content-Type", "text/plain")
                .build();
/*        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlString).newBuilder();
        urlBuilder.addQueryParameter("website", "www.journaldev.com");
        urlBuilder.addQueryParameter("tutorials", "android");
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();*/
        try (Response response = client.newCall(request).execute()) {
            Log.e("Code", "" + response.code() + "|" + response.message());
            return response.body().string();
        }
    }
    /*

     */


    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.get("text/x-markdown; charset=utf-8");
    // В данном API это бы тоже сработало
    public static final MediaType MEDIA_TYPE = MediaType.get("text/plain");

    public String testPostRequest(String urlString) throws IOException {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url(urlString)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    public void testAsyncGetRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        String result;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("onFailure", e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response)
                    throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Неудачный запрос к серверу: " +
                                response.code() + "|" + response.message());
                    }
                    Headers responseHeaders = response.headers();


                    for (int i = 0; i < responseHeaders.size(); i++) {
                        Log.i("onResponse:Headers", responseHeaders.name(i) + ": "
                                + responseHeaders.value(i));
                    }
                    Log.e("", "-----------------------------------------------------------");
                    Log.i("onResponse:Body", responseBody.string());
                    //result = responseBody.string();
                }
            }
        });

    }
}





