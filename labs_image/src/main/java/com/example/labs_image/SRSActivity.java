package com.example.labs_image;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SRSActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private TextView textImage, textScale;
    Button arrayButtons[] = new Button[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2);

        img = findViewById(R.id.st_image);
        textImage = findViewById(R.id.textviewCurrentImage);
        textScale = findViewById(R.id.textviewCurrentScale);

        arrayButtons[0] = findViewById(R.id.st_center);
        arrayButtons[0].setOnClickListener((View.OnClickListener) this);

        arrayButtons[1] = findViewById(R.id.st_center_crop);
        arrayButtons[1].setOnClickListener((View.OnClickListener) this);

        arrayButtons[2] = findViewById(R.id.st_center_inside);
        arrayButtons[2].setOnClickListener((View.OnClickListener) this);

        arrayButtons[3] = findViewById(R.id.st_fit_center);
        arrayButtons[3].setOnClickListener((View.OnClickListener) this);

        arrayButtons[4] = findViewById(R.id.st_fit_end);
        arrayButtons[4].setOnClickListener((View.OnClickListener) this);

        arrayButtons[5] = findViewById(R.id.st_center_inside);
        arrayButtons[5].setOnClickListener((View.OnClickListener) this);

        arrayButtons[6] = findViewById(R.id.st_fit_start);
        arrayButtons[6].setOnClickListener((View.OnClickListener) this);

        arrayButtons[7] = findViewById(R.id.st_fit_xy);
        arrayButtons[7].setOnClickListener((View.OnClickListener) this);

        arrayButtons[8] = findViewById(R.id.st_matrix);
        arrayButtons[8].setOnClickListener((View.OnClickListener) this);
    }

    private void textChanger(String str, boolean type){
        if (type == true){
            textImage.setText("Текущая картинка: " + str);
        }
        else {
            textScale.setText("Текущее маштабирование:" + str);
        }

    }
    private void imageChange(int num) {

        if (num == 1) {
            img.setImageResource(R.drawable.one);
        } else if (num == 2) {
            img.setImageResource(R.drawable.two);
        } else if (num == 3) {
            img.setImageResource(R.drawable.three);
        } else if (num == 4) {
            img.setImageResource(R.drawable.four);
        } else {
            img.setImageResource(R.drawable.five);
        }
    }
    private void imageScaleChange(Button btn){
        String str = "";
        if (btn.equals(arrayButtons[0])) {
            img.setScaleType(ImageView.ScaleType.CENTER);
            str = "CENTER";
        } else if (btn.equals(arrayButtons[1])) {
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            str = "CENTER_CROP";
        } else if (btn.equals(arrayButtons[2])) {
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            str = "CENTER_INSIDE";
        } else if (btn.equals(arrayButtons[3])) {
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            str = "FIT_CENTER";
        } else if (btn.equals(arrayButtons[4])) {
            img.setScaleType(ImageView.ScaleType.FIT_END);
            str = "FIT_END";
        } else if (btn.equals(arrayButtons[5])) {
            img.setScaleType(ImageView.ScaleType.FIT_START);
            str = "FIT_START";
        } else if (btn.equals(arrayButtons[6])) {
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            str = "FIT_XY";
        } else if (btn.equals(arrayButtons[7])) {
            img.setScaleType(ImageView.ScaleType.MATRIX);
            str = "MATRIX";
        }
        textChanger(str, false);


    }
    public void onClickChange(View view){
        int rnd = (int) (Math.random() * 5 + 1);
        imageChange(rnd);
        textChanger(String.valueOf(rnd), true);

    }
    public void onClick(View view){
        Button btn = findViewById(view.getId());
        imageScaleChange(btn);
    }

}
