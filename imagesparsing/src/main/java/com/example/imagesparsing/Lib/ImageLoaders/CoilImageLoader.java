package com.example.imagesparsing.Lib.ImageLoaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imagesparsing.Lib.ImageLoadersClass;


import coil.request.ImageRequest;
import coil.ImageLoader;

import coil.size.Scale;
import coil.size.Size;
import coil.target.Target;
import coil.transform.CircleCropTransformation;
import coil.transform.Transformation;
import kotlin.coroutines.Continuation;
import coil.transform.RoundedCornersTransformation;

public class CoilImageLoader extends ImageLoadersClass {

    public CoilImageLoader(Context context) {
        super(context);
    }

    ImageLoader imageLoader = new ImageLoader.Builder(context).build();
    @Override
    public void loadImage(String url, ImageView imageView) {
        //Coil.load(imageView, "https://example.com/image.jpg");
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(imageView)
                .build();

        imageLoader.enqueue(request);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(imageView)
                .placeholder(placeholderResId)
                .build();

        imageLoader.enqueue(request);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(imageView)
                .placeholder(placeholderResId)
                .error(errorResId)
                .build();

        imageLoader.enqueue(request);
    }

    @Override
    public void loadImage(String url, ImageView imageView, ImageLoadCallback callback) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(
                        new Target() {
                            @Override
                            public void onError(@Nullable Drawable error) {
                                // Действия при ошибке
                                callback.onFailure(new Exception());
                                Target.super.onError(error);
                            }

                            @Override
                            public void onStart(@Nullable Drawable placeholder) {
                                // Действия при начале загрузки
                                Target.super.onStart(placeholder);
                            }

                            @Override
                            public void onSuccess(@NonNull Drawable result) {
                                // Действия при успешной загрузке
                                callback.onSuccess();
                                Target.super.onSuccess(result);
                            }
                        }
                )
                .build();
        imageLoader.enqueue(request);
    }

    @Override
    public void resizeImageDefault(String url, ImageView imageView) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(imageView)
                .size(300, 300)
                .build();

        imageLoader.enqueue(request);
    }

    @Override
    public void resizeImageCustom(String url, ImageView imageView, int resizeWight, int resizeHeight) {
        isValidUrl(url);
        //checkContext();
        if (resizeWight != 0 && resizeHeight != 0) {
            ImageRequest request = new ImageRequest.Builder(context)
                    .data(url)
                    .target(imageView)
                    .size(resizeWight, resizeHeight)
                    .build();

            imageLoader.enqueue(request);
        } else {
            resizeImageDefault(url, imageView);
        }

    }

    @Override
    public void centerCrop(String url, ImageView imageView) {
        // Вариант 1: CENTER_CROP (обрезает, заполняя весь View)
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(imageView)
                .build();

        imageLoader.enqueue(request);
    }

    @Override
    public void centerInside(String url, ImageView imageView) {
        // Вариант 2: CENTER_INSIDE (вписывает без обрезки)
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .target(imageView)
                .build();
        imageLoader.enqueue(request);
    }

    @Override
    public void fit(String url, ImageView imageView) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .scale(Scale.FIT) // Вписывает, но может оставлять пустые области
                .target(imageView)
                .build();
        imageLoader.enqueue(request);
    }
    // Кастомный трансформатор для поворота
    class RotateTransformation implements Transformation {
        private final float degrees;

        public RotateTransformation(float degrees) {
            this.degrees = degrees;
        }
        @NonNull
        @Override
        public String getCacheKey() {
            return "rotate_" + degrees;
        }

        @Nullable
        @Override
        public Object transform(@NonNull Bitmap bitmap, @NonNull Size size, @NonNull Continuation<? super Bitmap> continuation) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        }
    }
    @Override
    public void rotateDefault(String url, ImageView imageView) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                .transformations(new RotateTransformation(90f)) // Поворот на 90°
                .target(imageView)
                .build();
        imageLoader.enqueue(request);

    }

    @Override
    public void rotateCustom(String url, ImageView imageView, float rotateF) {
        if (rotateF != 0) {
            ImageRequest request = new ImageRequest.Builder(context)
                    .data(url)
                    .transformations(new RotateTransformation(rotateF)) // Поворот на 90°
                    .target(imageView)
                    .build();
            imageLoader.enqueue(request);
        } else {
            rotateDefault(url, imageView);
        }
    }

    @Override
    public void complexRotate(String url, ImageView imageView) {
        // TODO Убрать метод
    }

    @Override
    public void customTransform(String url, ImageView imageView) {
        ImageRequest request = new ImageRequest.Builder(context)
                .data(url)
                // Закругленные края
                //.transformations(new RoundedCornersTransformation(16f))
                .transformations(new CircleCropTransformation())
                .target(imageView)
                .build();
        imageLoader.enqueue(request);
    }
}
