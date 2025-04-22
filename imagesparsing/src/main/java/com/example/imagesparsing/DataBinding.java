package com.example.imagesparsing;

import com.example.imagesparsing.Lib.ImageLibrary;


public class DataBinding {

    private static ImageLibrary imageLibrary;

    public static void saveSelectedLibrary(ImageLibrary selectedLibrary) {
        imageLibrary = selectedLibrary;
    }

    public static ImageLibrary getSelectedLibrary() {
        return imageLibrary;
    }
}
