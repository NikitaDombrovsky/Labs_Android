package com.example.network_httpurlconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TestActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textView);
        new TestTask().execute("https://starkovden.github.io/what-is-rest-api.html#rest-%D1%84%D0%BE%D0%BA%D1%83%D1%81%D0%B8%D1%80%D1%83%D0%B5%D1%82%D1%81%D1%8F-%D0%BD%D0%B0-%D1%80%D0%B5%D1%81%D1%83%D1%80%D1%81%D0%B0%D1%85-%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF-%D0%BA-%D0%BA%D0%BE%D1%82%D0%BE%D1%80%D1%8B%D0%BC-%D0%BE%D1%81%D1%83%D1%89%D0%B5%D1%81%D1%82%D0%B2%D0%BB%D1%8F%D0%B5%D1%82%D1%81%D1%8F-%D1%87%D0%B5%D1%80%D0%B5%D0%B7-url");

    }
    private class TestTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();

                InputStream inputStream =  httpURLConnection.getInputStream();//new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                }
            }
            catch (Exception e){
                Log.e("ERROR", e.getLocalizedMessage());
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.e("ERROR", s);
            textView.setText(s);
            super.onPostExecute(s);
        }
    }
}