/*
package com.example.imagesparsing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imagesparsing.Lib.ImageLibrary;
import com.example.imagesparsing.Lib.ImageLoaderFactory;
import com.example.imagesparsing.Lib.ImageLoaders.GlideImageLoader;
import com.example.imagesparsing.Lib.ImageLoaders.PicassoImageLoader;
import com.example.imagesparsing.Lib.ImageLoadersClass;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoActivity extends AppCompatActivity {
    Button defaultBtn, resize, centrecrop, centreinside, fit, placeholder, error, fade, callback, rotate, complexrotate, customTransform, gif;
    //  ImageView iv;//, ivSimpleDraweeView;
    SimpleDraweeView draweeView;

    EditText resizeWightEdt, resizeHeightEdt, rotateEdt;
    int resizeWight = 0, resizeHeight = 0;
    float rotateF = 0;


    public String base_url = "https://i.imgur.com/DvpvklR.png";
    public String error_url = "123456789";
    ImageLoadersClass imageLoaders;


    private void init() {
        resize = findViewById(R.id.resize);
        centrecrop = findViewById(R.id.centrecrop);
        centreinside = findViewById(R.id.centreinside);
        fit = findViewById(R.id.fit);
        placeholder = findViewById(R.id.placeholder);
        error = findViewById(R.id.error);
        fade = findViewById(R.id.fade);
        callback = findViewById(R.id.callback);
        rotate = findViewById(R.id.rotate);
        draweeView = (SimpleDraweeView) findViewById(R.id.ivSimpleDraweeView);
        //iv = findViewById(R.id.ivSimpleDraweeView);
        //ivSimpleDraweeView = findViewById(R.id.ivSimpleDraweeView);
        complexrotate = findViewById(R.id.complexrotation);
        resizeWightEdt = findViewById(R.id.resizeWidthEdt);
        resizeHeightEdt = findViewById(R.id.resizeHeightEdt);
        rotateEdt = findViewById(R.id.rotateEdt);
        customTransform = findViewById(R.id.customTransform);
        gif = findViewById(R.id.gif);
        defaultBtn = findViewById(R.id.defaulBtn);


    }

    // TODO Переделать
    private void enabledChanger(ImageLibrary imageLibraryName) {
        switch (imageLibraryName) {
            case COIL:
                break;
            case GLIDE:
                //glideClass.startGlide();
                break;
*/
/*            case FRESCO:

                break;*//*

            case PICASSO:
                gif.setEnabled(false);
                break;
        }
    }


    // ImageParsingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fresco.initialize(this);
        // TODO Из viewModel
        // Перенести сюда в случае FRESCO
        // Выбираем библиотеку (можно вынести в настройки)


*/
/*        if (libraryName == Library.FRESCO){
            imageLoaders = ImageLoaderFactory.create(this, libraryName);
        }*//*

        ImageLibrary library = ImageParsingImpl.getSelectedLibrary();
        imageLoaders = ImageLoaderFactory.create(this, library);
        Fresco.initialize(this);
        setContentView(R.layout.fresco_activity);

        // Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/main/docs/static/logo.png");
        init();
        // draweeView.setImageURI(uri);
        //  imageLoaders.loadImage(base_url, draweeView);


        // TODO Убрать
        // libraryName = Library.PICASSO;

        loading();
        // TODO Передалать
        enabledChanger(ImageLibrary.PICASSO);
        getData();
    }

    private void loading() {
        // Используем единый интерфейс
        imageLoaders.loadImage(base_url, draweeView);
    }

    private void getResizeSize() {
        if (!resizeHeightEdt.getText().toString().equals("") && !resizeWightEdt.getText().toString().equals("")) {
            resizeWight = Integer.parseInt(resizeWightEdt.getText().toString());
            resizeHeight = Integer.parseInt(resizeHeightEdt.getText().toString());
        }
    }

    private void getRotateSize() {
        if (!rotateEdt.getText().toString().equals("")) {
            rotateF = Float.parseFloat(rotateEdt.getText().toString());
        }
    }

    private void getData() {
        defaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
            }
        });
        resize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResizeSize();
                imageLoaders.resizeImageCustom(base_url, draweeView, resizeWight, resizeHeight);
                Toast.makeText(FrescoActivity.this, "Resize called ", Toast.LENGTH_SHORT).show();
            }
        });

        centrecrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.centerCrop(base_url, draweeView);
                Toast.makeText(FrescoActivity.this, "Centrecrop called ", Toast.LENGTH_SHORT).show();
            }
        });
        centreinside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.centerInside(base_url, draweeView);
                Toast.makeText(FrescoActivity.this, "Centreinside called ", Toast.LENGTH_SHORT).show();
            }
        });
        fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.fit(base_url, draweeView);
                Toast.makeText(FrescoActivity.this, "Fit called ", Toast.LENGTH_SHORT).show();
            }
        });

        placeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.loadImage(error_url, draweeView, R.drawable.ic_launcher_foreground);
                Toast.makeText(FrescoActivity.this, "Placeholder called ", Toast.LENGTH_SHORT).show();
            }
        });
        //
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.loadImage(error_url, draweeView, R.drawable.ic_launcher_foreground, R.mipmap.ic_launcher);
                Toast.makeText(FrescoActivity.this, "Error called ", Toast.LENGTH_SHORT).show();
            }
        });
        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.loadImage(error_url, draweeView, new ImageLoadersClass.ImageLoadCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(FrescoActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(FrescoActivity.this, "Error:" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                //picassoClass.callbackPicasso();
            }
        });
        //
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRotateSize();
                imageLoaders.rotateCustom(base_url, draweeView, rotateF);
                // picassoClass.rotatePisassoDefaultCustom(rotateF);
                Toast.makeText(FrescoActivity.this, "Rotate Called", Toast.LENGTH_SHORT).show();
            }
        });
        complexrotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.complexRotate(base_url, draweeView);
                //picassoClass.complexrotatePicasso();
                Toast.makeText(FrescoActivity.this, "Complex Rotate Called", Toast.LENGTH_SHORT).show();
            }
        });
        //
        customTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoaders.customTransform(base_url, draweeView);
                //picassoClass.customTransform();
            }
        });
        //
        // TODO Убрать к хуям
        fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageLoaders instanceof GlideImageLoader) {
                    ((GlideImageLoader) imageLoaders).noFade(base_url, draweeView);
                } else if (imageLoaders instanceof PicassoImageLoader) {
                    ((PicassoImageLoader) imageLoaders).noFadePicasso(base_url, draweeView, R.drawable.ic_launcher_foreground);
                }
*/
/*

                PicassoImageLoader picassoImageLoader = new PicassoImageLoader(getApplicationContext());
                picassoImageLoader.noFadePicasso(base_url, iv, R.drawable.ic_launcher_foreground);
*//*


*/
/*                GlideImageLoader glideImageLoader = new GlideImageLoader(getApplicationContext());
                glideImageLoader.noFade(base_url, iv);*//*


                Toast.makeText(FrescoActivity.this, "Fade called ", Toast.LENGTH_SHORT).show();
            }
        });
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideImageLoader glideImageLoader = new GlideImageLoader(getApplicationContext());
                glideImageLoader.forGifs(base_url, draweeView);
            }
        });
    }
}*/
