package com.example.network_okhttp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.testText);

        //new TestTask().execute("https://raw.github.com/square/okhttp/master/README.md");
        new TestTask().execute("https://httpbin.org/get");
        //  new TestTask().execute("https://api.github.com/markdown/raw");
        // fetchDataFromServer();
        //testSerializable();

/*        Runnable runnable =  new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClass okHttpClass = new OkHttpClass();
                    okHttpClass.testAsyncGetRequest();
                } catch (Exception e){
                    Log.e("ERROR", e.getMessage());
                }
                //Result<LoginResponse> ignoredResponse = makeSynchronousLoginRequest(jsonBody);
            }
        };
        runnable.run();*/
/*
        try {
            OkHttpClass okHttpClass = new OkHttpClass();
            okHttpClass.testAsyncGetRequest(textView);
        } catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
*/


/*        try {
            OkHttpClass example = new OkHttpClass();
            String response = example.testAsyncGetRequest();
            textView.setText(response);
        } catch (Exception e) {
            textView.setText(e.getLocalizedMessage());
        }*/


    }

    private OkHttpClient client = new OkHttpClient();

    private void fetchDataFromServer() {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call call, IOException e) {
                // Обработка ошибки в UI потоке
                runOnUiThread(() -> {
                    textView.setText("Error: " + e.getMessage());
                });
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        // Вывод ошибки
                        runOnUiThread(() -> {
                            textView.setText("Неудачный запрос к серверу: " +
                                    response.code() + "|" + response.message());
                        });
                        return;
                    }
                    Headers responseHeaders = response.headers();

                    for (int i = 0; i < responseHeaders.size(); i++) {
                        Log.i("onResponse:Headers", responseHeaders.name(i) + ": "
                                + responseHeaders.value(i));
                    }

                    // Вывод тела ответа
                    final String responseData = responseBody.string();
                    runOnUiThread(() -> {
                        textView.setText(responseData);
                    });
                }
            }
        });
    }

    static class GitUser {
        String name;
        String url;
        String id;
    }

    public void testSerializable() {
        Request request = new Request.Builder()
                .url("https://api.github.com/users/codepath")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            final Gson gson = new Gson();

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    // Использование базовых классов
                    final String owner = json.getString("name");

                    // Использование библиотеки GSON
                    // Создайте GitUser с какими нибудь ключами из JSON-файла
                    GitUser user = gson.fromJson(json.toString(), GitUser.class);
                    Log.e("!", user.name);

                    // Вывод в нужном формате
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class TestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String response = null;
            try {
                OkHttpClass example = new OkHttpClass();
                //response = example.testGetRequest(strings[0]);
                //response = example.testGetRequestWithQuery(strings[0]);
                response = example.testGetRequestWithHeaders(strings[0]);
                Log.e("!", response);
                //response = example.testPostRequest(strings[0]);
            } catch (Exception e) {
                Log.e("ERROR", e.getLocalizedMessage());
            }
            return response;

        }

        @Override
        protected void onPostExecute(String string) {
            textView.setText(string);
            super.onPostExecute(string);
        }
    }
}
