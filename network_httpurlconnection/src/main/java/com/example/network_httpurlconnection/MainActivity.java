package com.example.network_httpurlconnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    private ImageView ivBasicImage;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        ivBasicImage = (ImageView) findViewById(R.id.imageView);


        String url = "https://i.imgur.com/5yeBVeM.jpeg";
        new ImageDownloadTask(ivBasicImage).execute(url);


        downloadResponseFromNetwork();

    }

    private void downloadResponseFromNetwork() {
        new OpenGoogleTask().execute("https://google.com");
    }


    // String == URL, Void == Отслеживание прогресса, String == Полученный ответ
    private class OpenGoogleTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            HttpsURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                // Просто наведитесь на HttpURLConnection и посмотрите документацию
                // TODO Ужатый вариант
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream())
                );
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
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
            textView.setText(result);
        }
    }

    // Указанные здесь типы — это тип входных данных, тип прогресса и тип результата
    private class TestTask extends AsyncTask<String, Integer, String> {
        protected void onPreExecute() {
            // Выполняется в основном потоке перед doInBackground
            // Например инициализировать progressBar
            progressBar.setProgress(0);
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        protected String doInBackground(String... strings) {
            // Сама фоновая задача
            try {
                URL url = new URL(strings[0]);
                /// .....
            } catch (Exception e) {
            }
            return "test";
        }

        protected void onProgressUpdate(Integer... values) {
            // Выполняется при вызове publishProgress из doInBackground
            // Например для обновление progressBar
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(String result) {
            // Этот метод выполняется в основном потоке
            // с доступом к результату фоновой задачи
            textView.setText(result);
        }
    }

    private class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public ImageDownloadTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... addresses) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                URL url = new URL(addresses[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                        Log.e("ERROR", "Exception while closing inputstream" + e);
                    }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}