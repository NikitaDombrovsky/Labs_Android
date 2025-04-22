package com.example.imagesparsing.Lib;

import android.content.Context;
import android.widget.ImageView;

import com.example.imagesparsing.R;

public abstract class ImageLoadersClass  {

    protected Context context;

    public ImageLoadersClass(Context context) {
        this.context = context;
    }

    // Абстрактные методы, которые должны быть реализованы в наследниках
    public abstract void loadImage(String url, ImageView imageView);
    public abstract void loadImage(String url, ImageView imageView, int placeholderResId);
    public abstract void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId);
    public abstract void loadImage(String url, ImageView imageView, ImageLoadCallback callback);

    public abstract void resizeImageDefault(String url, ImageView imageView);
    public abstract void resizeImageCustom(String url, ImageView imageView, int resizeWight, int resizeHeight);

    public abstract void centerCrop(String url, ImageView imageView);
    public abstract void centerInside(String url, ImageView imageView);
    public abstract void fit(String url, ImageView imageView);

    public abstract void rotateDefault(String url, ImageView imageView);
    public abstract void rotateCustom(String url, ImageView imageView, float rotateF);

    public abstract void customTransform(String url, ImageView imageView);

    // Интерфейс колбэка для загрузки
    public interface ImageLoadCallback {
        void onSuccess();       // Вызывается при успешной загрузке
        void onFailure(Exception e);  // Вызывается при ошибке
    }

    public abstract void complexRotate(String url, ImageView imageView);
    public abstract void noFadePicasso(String url, ImageView imageView, int placeholderResId);
    public abstract void forGifsGlide(String url, ImageView imageView);

    // Общий метод для проверки URL
    // TODO Проверку на URL а не на пустоту
    protected void isValidUrl(String url) {
        if (url == null && url.isEmpty() ) {
            throw new IllegalStateException("Url cannot be null");
        }
    }
    // Опционально: можно добавить общие методы или реализацию по умолчанию
    protected void checkContext() {
        if (context == null) {
            throw new IllegalStateException("Context cannot be null");
        }
    }

}
