package com.example.imagesparsing.Lib.ImageLoaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.imagesparsing.Lib.ImageLoadersClass;
import com.example.imagesparsing.R;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;

public class GlideImageLoader extends ImageLoadersClass {
    public GlideImageLoader(Context context) {
        super(context);
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholderResId) // can also be a drawable
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId) {
        Glide.with(context).load(url)
                // will be displayed if the image cannot be loaded
                .placeholder(placeholderResId)
                .error(errorResId) // will be displayed if the image cannot be loaded
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, ImageLoadCallback callback) {
        Glide.with(context).load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("!", "Error");
                        callback.onFailure(e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        Log.e("!", "Succesful");
                        callback.onSuccess();
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public void resizeImageDefault(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .override(300, 300)
                .into(imageView);
    }

    @Override
    public void resizeImageCustom(String url, ImageView imageView, int resizeWight, int resizeHeight) {
        if (resizeWight != 0 && resizeHeight != 0) {
            Glide.with(context)
                    .load(url)
                    .override(resizeWight, resizeHeight)// resizes the image to these dimensions (in pixel). does not respect aspect ratio
                    .into(imageView);
        } else {
            resizeImageDefault(url, imageView);
        }
    }

    @Override
    public void centerCrop(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void centerInside(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .override(100, 100)
                .centerInside().
                into(imageView);
    }

    @Override
    public void fit(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fitCenter()
                .into(imageView);
    }
    class RotateTransformation extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation(float rotateRotationAngle) {
            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            Matrix matrix = new Matrix();

            matrix.postRotate(rotateRotationAngle);

            return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
            messageDigest.update(("rotate" + rotateRotationAngle).getBytes());
        }
    }
    @Override
    public void rotateDefault(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new RotateTransformation(90)) // Кастомная трансформация
                .into(imageView);
    }

    @Override
    public void rotateCustom(String url, ImageView imageView, float rotateF) {
        if (rotateF != 0) {
            Glide.with(context)
                    .load(url)
                    .transform(new RotateTransformation(rotateF)) // Кастомная трансформация
                    .into(imageView);
        } else {
            rotateDefault(url, imageView);
        }

    }

    @Override
    public void complexRotate(String url, ImageView imageView) {
        Log.e("!", "А оно и не должно работать");
        // TODO Сделать
    }

    @Override
    public void customTransform(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new CircleCrop()) // Встроенная трансформация
                .into(imageView);
    }
    // TODO: Перенести в родителя
    public void noFade(String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())// Встроенная трансформация
                .into(imageView);

    }

    ///
    public void forBitmap(String url, ImageView imageView) {
        // For Bitmaps:
        Glide.with(context)
                // Обязательно перед load
                .asBitmap().load(url)
                // .transform(new MyTransformation(context))
                .into(imageView);
    }

    public void forGifs(String url, ImageView imageView) {
        // For Gifs:
        Glide.with(context)
                // Обязательно перед load
                .asGif()
                //.load("https://media1.tenor.com/m/IXcAfMNnsBMAAAAd/crocodile-explosion.gif")
                .load("https://media1.tenor.com/m/RR-8CBT-yb4AAAAd/plane-memes.gif")
                //.override(600, 600)
                .error(R.drawable.ic_launcher_background).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }
}
