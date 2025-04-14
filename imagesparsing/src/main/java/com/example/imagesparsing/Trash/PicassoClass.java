package com.example.imagesparsing.Trash;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

import com.example.imagesparsing.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PicassoClass {
    private ImageView iv;
    private String base_url;

    public PicassoClass(ImageView iv, String base_url) {
        this.iv = iv;
        this.base_url = base_url;
    }

    public void startPicasso() {
        Picasso.get()
                .load(base_url)
                .into(iv);
    }

    public void resizePisassoDefault() {
        Picasso.get()
                .load(base_url)
                .resize(600, 600)// resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(iv);
    }

    public void resizePicassoCustom(int resizeWight, int resizeHeight) {
        if (resizeWight != 0 && resizeHeight != 0) {
            Picasso.get()
                    .load(base_url)
                    .resize(resizeWight, resizeHeight)// resizes the image to these dimensions (in pixel). does not respect aspect ratio
                    .into(iv);
        } else {
            resizePisassoDefault();
        }
    }


    public void centerCropPicasso() {
        Picasso.get()
                .load(base_url)
                .resize(100, 100)
                .centerCrop()
                .into(iv);
    }

    public void centerInsidePicasso() {
        Picasso.get()
                .load(base_url)
                .resize(100, 100)
                .centerInside()
                .into(iv);
    }

    public void fitPicasso() {
        Picasso.get()
                .load(base_url)
                .fit()
                .into(iv);
    }

    public void noFadePicasso() {
        Picasso.get()
                .load(base_url)
                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                .noFade()
                .into(iv);
    }

    public void placeholderPicasso() {
        Picasso.get()
                .load("false_url")
                .placeholder(R.drawable.ic_launcher_foreground) // can also be a drawable
                .into(iv);
    }

    public void errorPicasso() {
        Picasso.get()
                .load("false_url")
                .error(R.mipmap.ic_launcher) // will be displayed if the image cannot be loaded
                .into(iv);
    }

    public void callbackPicasso() {
        Picasso.get()
                .load(base_url)
                .into(iv, new Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                        Log.e("!", "Succesful");
                        //Toast.makeText(MainActivity.this, "Image is loaded succesfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        //do smth when there is picture loading error
                        Log.e("!", "Error");
                        // Toast.makeText(MainActivity.this, "some Error occured", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void rotatePisassoDefault() {
        com.squareup.picasso.Picasso.get()
                .load(base_url)
                .rotate(90f)
                .into(iv);
    }

    public void rotatePisassoDefaultCustom(float rotateF) {
        if (rotateF != 0) {
            com.squareup.picasso.Picasso.get()
                    .load(base_url)
                    .rotate(rotateF)
                    .into(iv);
        } else {
            rotatePisassoDefault();
        }

    }

    public void complexrotatePicasso() {
        com.squareup.picasso.Picasso.get()
                .load(base_url)
                .rotate(45f, 220f, 100f)
                .into(iv);
    }
    public void customTransform(){
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
                BitmapShader shader = new BitmapShader(squaredBitmap,
                        BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
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
                .load(base_url)
                .transform(new CircleTransform())
                .into(iv);
    }

}
