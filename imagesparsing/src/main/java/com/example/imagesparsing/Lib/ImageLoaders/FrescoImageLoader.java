/*
package com.example.imagesparsing.Lib.ImageLoaders;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.widget.ImageView;


import com.example.imagesparsing.Lib.ImageLoadersClass;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

public class FrescoImageLoader extends ImageLoadersClass {
    public FrescoImageLoader(Context context) {
        super(context);
        // Делать это прям сразу перед setContentView
        Fresco.initialize(context); // Инициализация Fresco (лучше сделать в Application классе)
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        isValidUrl(url);
        if (imageView instanceof SimpleDraweeView) {
            Uri uri = Uri.parse(url);
            ((SimpleDraweeView) imageView).setImageURI(uri);
        } else {
            throw new IllegalArgumentException("ImageView must be SimpleDraweeView for Fresco");
        }

    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId) {
        //isValidUrl(url);
        if (imageView instanceof SimpleDraweeView) {
            Uri uri = Uri.parse(url);
            ((SimpleDraweeView) imageView).getHierarchy().setPlaceholderImage(placeholderResId);
            ((SimpleDraweeView) imageView).setImageURI(uri);
        } else {
            throw new IllegalArgumentException("ImageView must be SimpleDraweeView for Fresco");
        }
       // mSimpleDraweeView.getHierarchy().setPlaceholderImage(placeholderImage);
*/
/*        if (imageView instanceof SimpleDraweeView) {
            Uri uri = Uri.parse(url);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setControllerListener(new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFailure(String id, Throwable throwable) {
                            super.onFailure(id, throwable);
                            imageView.setImageResource(placeholderResId); // Fallback
                        }
                    })
                    .build();
            ((SimpleDraweeView) imageView).setController(controller);
        } else {
            throw new IllegalArgumentException("ImageView must be SimpleDraweeView for Fresco");
        }*//*

    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId) {
*/
/*        isValidUrl(url);
        if (imageView instanceof SimpleDraweeView) {
            Uri uri = Uri.parse(url);
            ((SimpleDraweeView) imageView).getHierarchy().setPlaceholderImage(placeholderResId);
            ((SimpleDraweeView) imageView).setImageURI(uri);
        } else {
            throw new IllegalArgumentException("ImageView must be SimpleDraweeView for Fresco");
        }*//*

        if (imageView instanceof SimpleDraweeView) {
            Uri uri = Uri.parse(url);
            // Можно и как выше можно и так
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(context.getResources())
                    .setPlaceholderImage(placeholderResId)
                    .setFailureImage(errorResId)
                    .build();
            ((SimpleDraweeView) imageView).setHierarchy(hierarchy);
            ((SimpleDraweeView) imageView).setImageURI(uri);
        } else {
            throw new IllegalArgumentException("ImageView must be SimpleDraweeView for Fresco");
        }
    }

    @Override
    public void loadImage(String url, ImageView imageView, ImageLoadCallback callback) {
        if (imageView instanceof SimpleDraweeView) {
            Uri uri = Uri.parse(url);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setControllerListener(new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                            callback.onSuccess();
                        }

                        @Override
                        public void onFailure(String id, Throwable throwable) {
                            callback.onFailure(new Exception(throwable));
                        }
                    })
                    .build();
            ((SimpleDraweeView) imageView).setController(controller);
        } else {
            throw new IllegalArgumentException("ImageView must be SimpleDraweeView for Fresco");
        }
    }


    @Override
    public void resizeImageDefault(String url, ImageView imageView) {

    }

    @Override
    public void resizeImageCustom(String url, ImageView imageView, int resizeWight, int resizeHeight) {

    }

    @Override
    public void centerCrop(String url, ImageView imageView) {

    }

    @Override
    public void centerInside(String url, ImageView imageView) {

    }

    @Override
    public void fit(String url, ImageView imageView) {

    }

    @Override
    public void rotateDefault(String url, ImageView imageView) {

    }

    @Override
    public void rotateCustom(String url, ImageView imageView, float rotateF) {

    }

    @Override
    public void complexRotate(String url, ImageView imageView) {

    }

    @Override
    public void customTransform(String url, ImageView imageView) {

    }
}
*/
