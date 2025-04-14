package com.example.imagesparsing;

import static android.view.View.INVISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imagesparsing.Lib.ImageLibrary;
import com.example.imagesparsing.Lib.ImageLoaderFactory;
import com.example.imagesparsing.Lib.ImageLoaders.GlideImageLoader;
import com.example.imagesparsing.Lib.ImageLoaders.PicassoImageLoader;
import com.example.imagesparsing.Lib.ImageLoadersClass;
import com.example.imagesparsing.Trash.ImageParsingViewModel;


public class MainActivity extends AppCompatActivity {
    Button defaultBtn, resize, centrecrop, centreinside, fit, placeholder, error, fade, callback, rotate, complexrotate, customTransform, gif;
    ImageView iv, ivSimpleDraweeView;


    EditText resizeWightEdt, resizeHeightEdt, rotateEdt;
    int resizeWight = 0, resizeHeight = 0;
    float rotateF = 0;

    int placeholderResId = R.drawable.placeholder_fill_svgrepo_com
            , errorResId = R.drawable.baseline_error_24;

    //public ImageLibrary imageLibraryName = ImageLibrary.COIL;

    public String base_url = "https://i.imgur.com/DvpvklR.png";
    public String error_url = "123456789";
    ImageLoadersClass imageLoaders;
/*    PicassoClass picassoClass;
    GlideClass glideClass;*/
    // PicassoImageLoader picassoClass1;

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
        iv = findViewById(R.id.iv);
        //ivSimpleDraweeView = findViewById(R.id.ivSimpleDraweeView);
        complexrotate = findViewById(R.id.complexrotation);
        resizeWightEdt = findViewById(R.id.resizeWidthEdt);
        resizeHeightEdt = findViewById(R.id.resizeHeightEdt);
        rotateEdt = findViewById(R.id.rotateEdt);
        customTransform = findViewById(R.id.customTransform);
        gif = findViewById(R.id.gif);
        defaultBtn = findViewById(R.id.defaulBtn);


        // TODO Мусор
/*        picassoClass = new PicassoClass(iv, base_url);
        glideClass = new GlideClass(this, iv, base_url);*/

        // picassoClass1 = new PicassoImageLoader(this);

    }

    // TODO Переделать
    private void enabledChanger(ImageLibrary imageLibraryName) {
        switch (imageLibraryName) {
            case COIL:
                break;
            case GLIDE:
                //glideClass.startGlide();
                break;
            case FRESCO:
                iv.setVisibility(INVISIBLE);
                break;
            case PICASSO:
                gif.setEnabled(false);
                break;
        }
    }


    ImageParsingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fresco.initialize(this);
        // TODO Из viewModel
        // Перенести сюда в случае FRESCO
        // Выбираем библиотеку (можно вынести в настройки)


/*        if (libraryName == Library.FRESCO){
            imageLoaders = ImageLoaderFactory.create(this, libraryName);
        }*/
        setContentView(R.layout.image_activity);

        // Во второй Activity
 /*       String imageUrl = DataHolder.getInstance().getImageUrl();
        Glide.with(this).load(imageUrl).into(imageView);*/
        //viewModel = new ViewModelProvider(this).get(ImageParsingViewModel.class);
        //viewModel = ImageParsingViewModel.getInstance();
        //libraryName = Library.COIL;
        init();
        //if (libraryName != Library.FRESCO){
       // viewModel.setSelectedLibrary(ImageLibrary.PICASSO);

       // imageLoaders = ImageLoaderFactory.create(this, imageLibraryName);
        ImageLibrary library = ImageParsingImpl.getSelectedLibrary();
        imageLoaders = ImageLoaderFactory.create(this, library);
        //}


        // TODO Убрать
        // libraryName = Library.PICASSO;

        loading();
        // TODO Передалать
        enabledChanger(library);
        getData();
    }

    private void loading() {
        // Используем единый интерфейс
        imageLoaders.loadImage(base_url, iv);
/*        switch (libraryName){
            case COIL:
                break;
            case GLIDE:
                glideClass.startGlide();
                break;
            case FRESCO:
                break;
            case PICASSO:
                picassoClass1.loadImage(base_url, iv);
               // picassoClass.startPicasso();
                break;
        }*/
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
                imageLoaders.resizeImageCustom(base_url, iv, resizeWight, resizeHeight);
                Toast.makeText(MainActivity.this, "Resize called ", Toast.LENGTH_SHORT).show();
            }
        });

        centrecrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.centerCrop(base_url, iv);
                Toast.makeText(MainActivity.this, "Centrecrop called ", Toast.LENGTH_SHORT).show();
            }
        });
        centreinside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.centerInside(base_url, iv);
                Toast.makeText(MainActivity.this, "Centreinside called ", Toast.LENGTH_SHORT).show();
            }
        });
        fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.fit(base_url, iv);
                Toast.makeText(MainActivity.this, "Fit called ", Toast.LENGTH_SHORT).show();
            }
        });

        placeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.loadImage(error_url, iv, placeholderResId);
                Toast.makeText(MainActivity.this, "Placeholder called ", Toast.LENGTH_SHORT).show();
            }
        });
        //
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.loadImage(error_url, iv, placeholderResId, errorResId);
                Toast.makeText(MainActivity.this, "Error called ", Toast.LENGTH_SHORT).show();
            }
        });
        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.loadImage(error_url, iv, new ImageLoadersClass.ImageLoadCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MainActivity.this, "Error:" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                imageLoaders.rotateCustom(base_url, iv, rotateF);
                // picassoClass.rotatePisassoDefaultCustom(rotateF);
                Toast.makeText(MainActivity.this, "Rotate Called", Toast.LENGTH_SHORT).show();
            }
        });
        complexrotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoaders.complexRotate(base_url, iv);
                //picassoClass.complexrotatePicasso();
                Toast.makeText(MainActivity.this, "Complex Rotate Called", Toast.LENGTH_SHORT).show();
            }
        });
        //
        customTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoaders.customTransform(base_url, iv);
                //picassoClass.customTransform();
            }
        });
        //
        // TODO Убрать к хуям
        fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageLoaders instanceof GlideImageLoader) {
                    ((GlideImageLoader) imageLoaders).noFade(base_url, iv);
                } else if (imageLoaders instanceof PicassoImageLoader) {
                    ((PicassoImageLoader) imageLoaders).noFadePicasso(base_url, iv, R.drawable.ic_launcher_foreground);
                }
/*

                PicassoImageLoader picassoImageLoader = new PicassoImageLoader(getApplicationContext());
                picassoImageLoader.noFadePicasso(base_url, iv, R.drawable.ic_launcher_foreground);
*/

/*                GlideImageLoader glideImageLoader = new GlideImageLoader(getApplicationContext());
                glideImageLoader.noFade(base_url, iv);*/

                Toast.makeText(MainActivity.this, "Fade called ", Toast.LENGTH_SHORT).show();
            }
        });
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideImageLoader glideImageLoader = new GlideImageLoader(getApplicationContext());
                glideImageLoader.forGifs(base_url, iv);
            }
        });
    }
}