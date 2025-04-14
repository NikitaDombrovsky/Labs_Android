package com.example.imagesparsing;

import android.widget.ImageView;

import com.example.imagesparsing.Lib.ImageLibrary;
import com.example.imagesparsing.Lib.ImageLoaderFactory;
import com.example.imagesparsing.Lib.ImageLoadersClass;

// TODO Можно удалить репозиторий сам и оставить только это
// TODO Надо как-то объединит с классом для парсинга
public class ImageParsingImpl {//implements ImageParsingRepository{

    private static ImageLibrary imageLibrary;

  //  @Override
    public static void saveSelectedLibrary(ImageLibrary selectedLibrary) {
        imageLibrary = selectedLibrary;
    }

    //@Override
    public static ImageLibrary getSelectedLibrary() {
        return imageLibrary;
    }
/*    @Override
    public void saveSelectedOption(ImageLibrary selectedLibrary) {
        ImageLoadersClass imageLoaders = ImageLoaderFactory.create(this, selectedLibrary);


    }

    @Override
    public void loadImage(String url, ImageView ivageView) {

    }*/
}
