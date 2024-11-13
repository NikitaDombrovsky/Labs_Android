package com.example.labs;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_image);


/*        ColorStateList colors;
        if (Build.VERSION.SDK_INT >= 23) {            colors = getResources().getColorStateList(R.color.blue, getTheme());
        }
        else {
            colors = getResources().getColorStateList(R.color.blue);
        }*/

/*        ColorStateList colors = getResources().getColorStateList(R.color.tab_selector, getTheme());
        Drawable icon = DrawableCompat.wrap(AppCompatResources.getDrawable(getApplicationContext(),
                R.drawable.image_cake));
        DrawableCompat.setTintList(icon, colors);
        ImageButton imageView = findViewById(R.id.tvFoo);
        imageView.setBackground(icon);*/
        //        ImageView imageView = findViewById(R.id.tvFoo);
        //        imageView.setBackground(icon);

        //ImageView lineColorCode = (ImageView)convertView.findViewById(R.id.line_color_code);



/*        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.image_cake, null);
        ImageView imageView = (ImageView) findViewById(R.id.tvFoo);
        imageView.setBackground(drawable);*/



        // on below line we are initializing variables with ids.


        // on below line we are setting background for
        // our relative layout on below line.
        // ContextCompat.getDrawable(context, R.drawable.ready)


// Use for pre-Lollipop devices
/*        Drawable drawable = AppCompatResources.getDrawable(R.drawable.image_cake);
// Wrap the drawable so that future tinting calls work on pre-v21 devices.
        Drawable icon = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(icon, colors);*/




        //Resources res = getResources();

/*        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.android_blue, null);
        ImageView imageView = (ImageView) findViewById(R.id.tvFoo);
        imageView.setBackground(drawable);*/


// Use AppCompatResource so that it will accurately use theme attributes
       // Drawable drawable = AppCompatResources.getDrawable(R.drawable.ic_heart);

// Use this drawable

/*       // TextView tvFoo = findViewById(R.id.tvFoo);
        ImageView tvFoo = findViewById(R.id.tvFoo);
        // Get drawable layer list from the background
        LayerDrawable bubble = (LayerDrawable) tvFoo.getBackground();
// Access
        GradientDrawable outerRect = (GradientDrawable)
                // bubble.findDrawableByLayerId(R.id.outerRectangle);
                bubble.findDrawableByLayerId(R.id.outerRectangle);
// Change the solid color of the drawable
        outerRect.setColor(Color.parseColor("#2f8f22"));*/

/*        ImageView iv = (ImageView) findViewById(R.id.image_heart);
        iv.setImageResource(R.drawable.ic_heart);*/

/*        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("message", "Hello from MainActivity!");
        startActivity(intent);*/

/*        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);

        Intent intentObj = new Intent(Intent.ACTION_VIEW);
        intentObj.setData(Uri.parse("https://www.oat.ru/"));
        startActivity(intentObj);*/

        //new HTTPReqTask().execute();
        //URL url = new URL("https://reqres.in/api/users?page=2");
/*        try {
        URL url = new URL("http://www.android.com/");
        HttpURLConnection urlConnection = null;

            urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
S
        } finally {
            urlConnection.disconnect();
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

    }
    public void onClick(View v) {

        EditText nameText = findViewById(R.id.name);
        EditText companyText = findViewById(R.id.group);
        EditText ageText = findViewById(R.id.score);

        String name = nameText.getText().toString();
        String group = companyText.getText().toString();
        int score = Integer.parseInt(ageText.getText().toString());

/*        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("group", group);
        intent.putExtra("score", score);
        startActivity(intent);*/

        Student student = new Student(name, group, score);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(Student.class.getSimpleName(), student);
        startActivity(intent);

        mStartForResult.launch(intent);
    }

    ActivityResultLauncher<Intent> mStartForResult =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    // обработка result
                }
            });

    private static class HTTPReqTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;

            try {
/*                URL url = new URL("https://www.oat.ru/");
                urlConnection = (HttpURLConnection) url.openConnection();*/
                JSONObject postData = new JSONObject();
                postData.put("name", "morpheus");
                postData.put("job", "leader");

                URL url = new URL("https://reqres.in/api/users");
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setChunkedStreamingMode(0);

                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        out, "UTF-8"));
                writer.write(postData.toString());
                writer.flush();
                int code = urlConnection.getResponseCode();
                if (code !=  201) {
                    throw new IOException("Invalid response from server: " + code);
                }
                    BufferedReader rd = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        Log.i("data", line);
                    }
            } catch (Exception e) {
                Log.e("ERROR", e.getLocalizedMessage());
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }
/*    private static class HTTPReqTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpsURLConnection urlConnection = null;

            try {
                URL url = new URL("https://www.oat.ru/");
               // URL url = new URL("https://reqres.in/api/users?page=2");
                urlConnection = (HttpsURLConnection) url.openConnection();
                try {
*//*                    urlConnection.setDoOutput(true);
                    urlConnection.setChunkedStreamingMode(0);*//*

                   // OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

                    //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader rd = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        Log.i("data", line);
                    }
                } finally {
                    urlConnection.disconnect();
                }

            } catch (Exception e) {
                Log.e("ERROR", e.getLocalizedMessage());
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }*/


}
