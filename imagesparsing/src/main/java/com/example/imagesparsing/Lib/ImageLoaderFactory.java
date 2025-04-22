package com.example.imagesparsing.Lib;

import android.content.Context;
import android.widget.ImageView;


import com.example.imagesparsing.Lib.ImageLoaders.CoilImageLoader;
//import com.example.imagesparsing.Lib.ImageLoaders.FrescoImageLoader;
import com.example.imagesparsing.Lib.ImageLoaders.GlideImageLoader;
import com.example.imagesparsing.Lib.ImageLoaders.PicassoImageLoader;
import com.example.imagesparsing.Lib.ImageLoadersClass.ImageLoadCallback;


public class ImageLoaderFactory {
    public static ImageLoadersClass create(Context context, ImageLibrary loaderType) {
        switch (loaderType) {
            case GLIDE:
                return new GlideImageLoader(context);
            case PICASSO:
                return new PicassoImageLoader(context);
            case COIL:
                return new CoilImageLoader(context);
            default:
                throw new IllegalArgumentException("Unknown loader type");
        }
    }
}
/*public class ImageLoaderFactory {
    public static ImageLoadersClass create(Context context, ImageLibrary loaderType) {
        switch (loaderType) {
            case GLIDE:
                return new GlideImageLoader(context);
            case PICASSO:
                return new PicassoImageLoader(context);
            case FRESCO:
                return new FrescoImageLoader(context);
            case COIL:
                return new CoilImageLoader(context);
            default:
                throw new IllegalArgumentException("Unknown loader type");
        }
    }
}*/
