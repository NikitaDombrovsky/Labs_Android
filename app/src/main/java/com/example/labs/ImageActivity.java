package com.example.labs;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

/*public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ImageView imageView = new ImageView(this);
        // применение ресурса
        imageView.setImageResource(R.drawable.photo);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        imageView.setLayoutParams(layoutParams);
        constraintLayout.addView(imageView);

        setContentView(constraintLayout);
    }
}*/

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);


/*        ImageView image = findViewById(R.id.test_image);
        image.setImageResource(R.drawable.photo);*/

        ImageView image1 = findViewById(R.id.test_image1);
        ImageView image2 = findViewById(R.id.test_image2);
        ImageView image3 = findViewById(R.id.test_image3);
        ImageView image4 = findViewById(R.id.test_image4);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.photo);

        Bitmap bScaleFitWidth = BitmapScaler.scaleToFitWidth(bMap, 250);
        image1.setImageBitmap(bScaleFitWidth);
        Bitmap bScaleFitHeight = BitmapScaler.scaleToFitHeight(bMap, 250);
        image2.setImageBitmap(bScaleFitHeight);
        Bitmap bScaleToFill= BitmapScaler.scaleToFill(bMap, 250, 250);
        image3.setImageBitmap(bScaleToFill);
        Bitmap bStrechToFill = BitmapScaler.strechToFill(bMap, 250, 250);
        image4.setImageBitmap(bStrechToFill);

        // Получите ширину и высоту экрана устройства
        int screenWidth = DeviceDimensionsHelper.getDisplayWidth(this);
        int screenHeight = DeviceDimensionsHelper.getDisplayHeight(this);

        Bitmap bScaleFitWidth1 = BitmapScaler.scaleToFitWidth(bMap, screenWidth);
        image1.setImageBitmap(bScaleFitWidth1);
        Bitmap bScaleFitHeight1 = BitmapScaler.scaleToFitHeight(bMap, screenHeight);
        image2.setImageBitmap(bScaleFitHeight1);

/*        // Измените размер bitmap до 50x100 (ширина x высота)
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 50, 100, true);
        ImageView image = findViewById(R.id.test_image1);
        image.setImageBitmap(bMap);
        ImageView image2 = findViewById(R.id.test_image2);
        image2.setImageBitmap(bMapScaled);*/

/*        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.photo);
        ImageView image = findViewById(R.id.test_image);
        image.setImageBitmap(bitmap);*/

/*

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.id.test_image, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ImageView imageView = new ImageView(this);
        Resources res = getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.photo, null);
        //  применение ресурса
        imageView.setImageDrawable(drawable);

        imageView.getLayoutParams().height = 100; // Или
        imageView.getLayoutParams().width = 100;

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        imageView.setLayoutParams(layoutParams);
        constraintLayout.addView(imageView);

        setContentView(constraintLayout);*/
    }
}


