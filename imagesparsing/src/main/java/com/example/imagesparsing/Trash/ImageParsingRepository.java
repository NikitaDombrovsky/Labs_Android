package com.example.imagesparsing.Trash;

import com.example.imagesparsing.Lib.ImageLibrary;

public interface ImageParsingRepository {
    void saveSelectedLibrary(ImageLibrary selectedLibrary);
    ImageLibrary getSelectedLibrary();
    //void loadImage(String url, ImageView ivageView);
}
