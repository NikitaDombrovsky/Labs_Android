package com.example.network_get_post_method.POST;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.network_get_post_method.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class POSTActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postactivity);
        new PostTask().execute("https://fakestoreapi.com/products");
    }

    public class PostTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject productObject = new JSONObject(s);
                product = new Product().fromJSON(productObject);

            } catch (Exception e) {
                Log.e("ERROR", e.getLocalizedMessage());
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
          //  return putProduct(strings[0]);
           // return postProduct(strings[0]);
            return deleteProduct(strings[0]);

        }
    }

    private void sendRequest(HttpsURLConnection conn, String data)
            throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(conn.getOutputStream(), "utf-8")
        );
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void getResponce(HttpsURLConnection conn, StringBuilder response)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
    }
    public String deleteProduct(String string) {
        String result = null;
        try {
            int id = 1;

            URL url = new URL(string + "/" + id);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            // Получение данных
            result = conn.getResponseMessage();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    public String putProduct(String string) {
        StringBuilder result = new StringBuilder();
        try {
            int id = 1;
            String postData = new Product().toJSON(
                    id,
                    "Test",
                    0.3,
                    "vvvvvvvawwacas",
                    "cat",
                    "test"
            ).toString();

            URL url = new URL(string + "/" + id);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            // Отправка данных
            sendRequest(conn, postData);
            // Получение данных
            int statusCode = conn.getResponseCode();
            if (statusCode == HttpsURLConnection.HTTP_OK) {
                getResponce(conn, result);
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String postProduct(String string) {
        StringBuilder result = new StringBuilder();
        try {
            // Подготовка данных
            String postData = "{\n" +
                    "    \"id\": 0,\n" +
                    "    \"title\": \"TestTitle\",\n" +
                    "    \"price\": 0.7,\n" +
                    "    \"description\": \"bebebebebebebebebebebebebebebebebebebebe\",\n" +
                    "    \"category\": \"TestCategory\",\n" +
                    "    \"image\": \"https://tenor.com/ru/view/крокодил-взрыв-что-как-дела-капибара-gif-9459038745379592723\"\n" +
                    "}";

            URL url = new URL(string);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Настройка подключения
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            // Устанавливаются уникальные заголовки (перезаписываются если были)
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept-Charset", "UTF-8");

            // Добавляются новые заголовки
            conn.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            conn.addRequestProperty("User-Agent", "Android");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            // Отправка данных
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(conn.getOutputStream(), "utf-8")
            );
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();


            // Получение данных
            int statusCode = conn.getResponseCode();
            if (statusCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            }
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    public String postToApi() {
        StringBuilder response = new StringBuilder();
        HttpsURLConnection connection = null;

        try {
            // 1. Подготовка данных
            String postData = "title=Bebe&price=0.2&description=string&category=sssss&image=http://example.com";
            URL url = new URL("https://fakestoreapi.com/products");

            // 2. Настройка соединения
            connection = (HttpsURLConnection) url.openConnection();
            configureConnection(connection);

            // 3. Отправка данных
            sendPostData(connection, postData);

            // 4. Обработка ответа
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpsURLConnection.HTTP_OK) {
                readResponse(connection, response);
            } else {
                response.append("Error: HTTP ").append(statusCode);
            }
        } catch (IOException e) {
            response.append("Error: ").append(e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

    private void configureConnection(HttpsURLConnection connection) throws IOException {
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
    }

    private void sendPostData(HttpsURLConnection connection, String postData) throws IOException {
        try (OutputStream outputStream = connection.getOutputStream();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.write(postData);
        }
    }

    private void readResponse(HttpsURLConnection connection, StringBuilder response) throws IOException {
        try (InputStream inputStream = connection.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
    }

}