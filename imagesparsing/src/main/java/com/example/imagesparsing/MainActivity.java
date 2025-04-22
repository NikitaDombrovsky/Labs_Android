package com.example.imagesparsing;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imagesparsing.Lib.ImageLibrary;
import com.example.imagesparsing.Lib.ImageLoaderFactory;
import com.example.imagesparsing.Lib.ImageLoadersClass;


public class MainActivity extends AppCompatActivity {
    Button defaultBtn, resize, centrecrop, centreinside, fit, placeholder, error, /*fade,*/ callback, rotate, /*complexrotate,*/ customTransform, gif;
    ImageView iv, ivSimpleDraweeView;

    EditText resizeWightEdt, resizeHeightEdt, rotateEdt;
    int resizeWight = 0, resizeHeight = 0;
    float rotateF = 0;

    int placeholderResId = R.drawable.placeholder_fill_svgrepo_com, errorResId = R.drawable.baseline_error_24;

    //public ImageLibrary imageLibraryName = ImageLibrary.COIL;

    public String base_url = "https://i.imgur.com/DvpvklR.png";
    public String error_url = "123456789";
    ImageLoadersClass imageLoader;

    private void init() {
        resize = findViewById(R.id.resize);
        centrecrop = findViewById(R.id.centrecrop);
        centreinside = findViewById(R.id.centreinside);
        fit = findViewById(R.id.fit);
        placeholder = findViewById(R.id.placeholder);
        error = findViewById(R.id.error);
        //fade = findViewById(R.id.fade);
        callback = findViewById(R.id.callback);
        rotate = findViewById(R.id.rotate);
        iv = findViewById(R.id.iv);
       // complexrotate = findViewById(R.id.complexrotation);
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
               // fade.setEnabled(false);
               // complexrotate.setEnabled(false);
                break;
            case GLIDE:
               // fade.setEnabled(false);
               // complexrotate.setEnabled(false);
                //glideClass.startGlide();
                break;
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


        ImageLibrary library = DataBinding.getSelectedLibrary();
        imageLoader = ImageLoaderFactory.create(this, library);
        // imageLoaders.loadImage();

        // TODO Убрать
        // libraryName = Library.PICASSO;

        loading();
        // TODO Передалать
        enabledChanger(library);
        getData();
    }

    private void loading() {
        // Используем единый интерфейс
        imageLoader.loadImage(base_url, iv);
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
        defaultBtn.setOnClickListener(v -> loading());
        resize.setOnClickListener(view -> {
            getResizeSize();
            imageLoader.resizeImageCustom(base_url, iv, resizeWight, resizeHeight);
            Toast.makeText(MainActivity.this, "Resize called ", Toast.LENGTH_SHORT).show();
        });
        //
        centrecrop.setOnClickListener(view -> {
            imageLoader.centerCrop(base_url, iv);
            Toast.makeText(MainActivity.this, "Centrecrop called ", Toast.LENGTH_SHORT).show();
        });
        centreinside.setOnClickListener(view -> {
            imageLoader.centerInside(base_url, iv);
            Toast.makeText(MainActivity.this, "Centreinside called ", Toast.LENGTH_SHORT).show();
        });
        fit.setOnClickListener(view -> {
            imageLoader.fit(base_url, iv);
            Toast.makeText(MainActivity.this, "Fit called ", Toast.LENGTH_SHORT).show();
        });
        //
        placeholder.setOnClickListener(view -> {
            imageLoader.loadImage(error_url, iv, placeholderResId);
            Toast.makeText(MainActivity.this, "Placeholder called ", Toast.LENGTH_SHORT).show();
        });
        error.setOnClickListener(view -> {
            imageLoader.loadImage(error_url, iv, placeholderResId, errorResId);
            Toast.makeText(MainActivity.this, "Error called ", Toast.LENGTH_SHORT).show();
        });
        callback.setOnClickListener(view -> {
            imageLoader.loadImage(error_url, iv, new ImageLoadersClass.ImageLoadCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(MainActivity.this, "Error:" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        //
        rotate.setOnClickListener(view -> {
            getRotateSize();
            imageLoader.rotateCustom(base_url, iv, rotateF);
            Toast.makeText(MainActivity.this, "Rotate Called", Toast.LENGTH_SHORT).show();
        });
        //
        customTransform.setOnClickListener(v -> {
            imageLoader.customTransform(base_url, iv);
            Toast.makeText(MainActivity.this, "Custom Transform Called", Toast.LENGTH_SHORT).show();
        });
        //
/*        complexrotate.setOnClickListener(view -> {
            imageLoader.complexRotate(base_url, iv);
            Toast.makeText(MainActivity.this, "Complex Rotate Called", Toast.LENGTH_SHORT).show();
        });*/
/*        fade.setOnClickListener(view -> {
            imageLoader.noFadePicasso(base_url, iv, placeholderResId);
            Toast.makeText(MainActivity.this, "Fade called ", Toast.LENGTH_SHORT).show();
        });*/
        gif.setOnClickListener(v -> {
            imageLoader.forGifsGlide(base_url, iv);
            Toast.makeText(MainActivity.this, "Gif called ", Toast.LENGTH_SHORT).show();
        });
    }
}