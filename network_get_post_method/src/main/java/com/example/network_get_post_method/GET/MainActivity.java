package com.example.network_get_post_method.GET;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.network_get_post_method.GET.Models.Product;
import com.example.network_get_post_method.GET.Models.Rating;
import com.example.network_get_post_method.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> productArrayList;
    private ProductsAdapter productsAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productArrayList = new ArrayList<Product>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsAdapter = new ProductsAdapter(productArrayList);
        recyclerView.setAdapter(productsAdapter);


        new GetAppProductTask().execute("https://fakestoreapi.com/products");


    }


    public String postAPI(){
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        String str = "";
        try {
            URL url = new URL("http://httpbin.org/post");
            // 接続先URLへのコネクションを開く．まだ接続されていない
            urlConnection = (HttpURLConnection) url.openConnection();
            // リクエストボディに格納するデータ
            String postData = "name=foge&type=fogefoge";
            // 接続タイムアウトを設定 //+
            urlConnection.setConnectTimeout(10000);
            // レスポンスデータの読み取りタイムアウトを設定 //+
            urlConnection.setReadTimeout(10000);
            // ヘッダーにUser-Agentを設定 //TODO ?
            urlConnection.addRequestProperty("User-Agent", "Android");
            // ヘッダーにAccept-Languageを設定 //TODO ?
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            // HTTPメソッドを指定 //+
            urlConnection.setRequestMethod("POST");
            //レスポンスボディの受信を許可する //+
            urlConnection.setDoInput(true);
            // リクエストボディの送信を許可する //TODO ?
            urlConnection.setDoOutput(true);
            // 通信開始 //+
            urlConnection.connect();
            // リクエストボディの書き込みを行う
            outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();

            // レスポンスコードを取得
            int statusCode = urlConnection.getResponseCode();
            // レスポンスコード200は通信に成功したことを表す
            if (statusCode == 200){
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // 1行ずつレスポンス結果を取得しstrに追記
                result = bufferedReader.readLine();
                while (result != null){
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // レスポンス結果のJSONをString型で返す
        return str;
    }



    private class GetAppProductTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            HttpsURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream())
                );
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                // создать JSONArray из JSON-файла
                JSONArray arr = new JSONArray(result);
                // Цикл с кол-вом итераций равным размеру массива
                for (int i = 0; i < arr.length(); i++) {
                    // Создание JSONObject по текущему индексу
                    JSONObject productObj = arr.getJSONObject(i);
                    // Создание еще одного JSONObject по ключу rating
                    JSONObject ratingObj = productObj.getJSONObject("rating");
                    productArrayList.add(new Product(
                            productObj.getInt("id"),
                            productObj.getString("title"),
                            productObj.getInt("price"),
                            productObj.getString("description"),
                            productObj.getString("category"),
                            productObj.getString("image"),
                            new Rating(
                                    ratingObj.getInt("rate"),
                                    ratingObj.getInt("count")
                            )
                    ));
                    productsAdapter.notifyItemChanged(i);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("!", "1");
        }
    }
}