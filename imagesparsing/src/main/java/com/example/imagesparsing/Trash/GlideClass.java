package com.example.imagesparsing.Trash;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.imagesparsing.R;
import com.squareup.picasso.Picasso;

public class GlideClass {

    Context context;
    private ImageView iv;
    private String base_url;

    public GlideClass(Context context, ImageView iv, String base_url) {
        this.context = context;
        this.iv = iv;
        this.base_url = base_url;
    }

    //// Picasso
    //.transform(new CircleTransform())
    //

    /// / Glide
    //.transform(new CircleTransform(context))
    public void startGlide() {
        Glide.with(context)
                .load(base_url)
                .into(iv);

    }
    public void resizeGlideDefault() {
        Glide.with(context)
                .load(base_url)
                .override(600, 600)
                .into(iv);
    }

    public void resizeGlideCustom(int resizeWight, int resizeHeight) {
        if (resizeWight != 0 && resizeHeight != 0) {
            Picasso.get()
                    .load(base_url)
                    .resize(resizeWight, resizeHeight)// resizes the image to these dimensions (in pixel). does not respect aspect ratio
                    .into(iv);
        } else {
            resizeGlideDefault();
        }
    }
    public void centerCropGlide() {
        Glide.with(context)
                .load(base_url)
                .centerCrop()
                .into(iv);

    }

    public void centerInsideGlide() {
        Glide.with(context)
                .load(base_url)
                .override(100, 100)
                .centerInside()
                .into(iv);
    }

    public void fitGLide() {
        Glide.with(context)
                .load(base_url)
                .fitCenter()
                .into(iv);

    }

    public void placeholderGlide() {
        Glide.with(context)
                .load("false_url")
                .placeholder(R.drawable.ic_launcher_foreground) // can also be a drawable
                .into(iv);
    }

    public void errorGlide() {
        Glide.with(context)
                .load("false_url")
                .error(R.mipmap.ic_launcher) // will be displayed if the image cannot be loaded
                .into(iv);
    }
    public void forBitmap(){
        // For Bitmaps:
        Glide.with(context)
                // Обязательно перед load
                .asBitmap()
                .load(base_url)
               // .transform(new MyTransformation(context))
                .into(iv);
    }
    public void forGifs(){
        // For Gifs:
        Glide.with(context)
                // Обязательно перед load
                .asGif()
                //.load("https://media1.tenor.com/m/IXcAfMNnsBMAAAAd/crocodile-explosion.gif")
                .load("https://media1.tenor.com/m/RR-8CBT-yb4AAAAd/plane-memes.gif")
                .override(600, 600)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(iv);
    }




}
