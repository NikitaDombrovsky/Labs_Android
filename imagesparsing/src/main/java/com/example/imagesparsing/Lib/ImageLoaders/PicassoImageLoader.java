package com.example.imagesparsing.Lib.ImageLoaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

import com.example.imagesparsing.Lib.ImageLoadersClass;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PicassoImageLoader extends ImageLoadersClass {

    public PicassoImageLoader(Context context) {
        super(context);
    }

    ///
    @Override
    public void noFadePicasso(String url, ImageView imageView, int placeholderResId) {
        isValidUrl(url);
        checkContext();
        Picasso.get().load(url)
                //.placeholder(R.mipmap.ic_launcher) // can also be a drawable
                .placeholder(placeholderResId) // can also be a drawable
                .noFade().into(imageView);
    }



    //
    @Override
    public void loadImage(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        // Получить
        Picasso.get()
                // Что
                .load(url)
                // Куда
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId) {
        //isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                // Ссылка на заглушку
                // ccылка на res из drawable
                .placeholder(placeholderResId)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId) {
       // isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                .placeholder(placeholderResId)
                // Отобразится если изображение не загрузится
                .error(errorResId)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, ImageLoadCallback callback) {
        // isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                .into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.e("!", "Succesful");
                callback.onSuccess();
            }

            @Override
            public void onError(Exception e) {
                Log.e("!", "Error");
                callback.onFailure(e);
            }
        });
    }


    @Override
    public void resizeImageDefault(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                // изменяет размер в пикселях
                .resize(300, 300)
                .into(imageView);
    }

    @Override
    public void resizeImageCustom(String url, ImageView imageView, int resizeWight, int resizeHeight) {
        isValidUrl(url);
        checkContext();
        if (resizeWight != 0 && resizeHeight != 0) {
            Picasso.get()
                    .load(url)
                    .resize(resizeWight, resizeHeight)
                    .into(imageView);
        } else {
            resizeImageDefault(url, imageView);
        }
    }

    @Override
    public void centerCrop(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                .resize(100, 100)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void centerInside(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                .resize(100, 100)
                .centerInside()
                .into(imageView);
    }

    @Override
    public void fit(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                .fit()
                .into(imageView);
    }


    @Override
    public void rotateDefault(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        Picasso.get()
                .load(url)
                .rotate(90f)
                .into(imageView);
    }

    @Override
    public void rotateCustom(String url, ImageView imageView, float rotateF) {
        isValidUrl(url);
        checkContext();
        if (rotateF != 0) {
            Picasso.get()
                    .load(url)
                    .rotate(rotateF)
                    .into(imageView);
        } else {
            rotateDefault(url, imageView);
        }
    }

    @Override
    public void complexRotate(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        Picasso.get().load(url).rotate(45f, 220f, 100f).into(imageView);
    }

    @Override
    public void customTransform(String url, ImageView imageView) {
        isValidUrl(url);
        checkContext();
        class CircleTransform implements Transformation {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());

                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);

                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle";
            }
        }

        Picasso.get()
                .load(url)
                .transform(new CircleTransform())
                .into(imageView);
    }
    @Override
    public void forGifsGlide(String url, ImageView imageView) {
        Log.e("!", "А не и должно");
    }
}

