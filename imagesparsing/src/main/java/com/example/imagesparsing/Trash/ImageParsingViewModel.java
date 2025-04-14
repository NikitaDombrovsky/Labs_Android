package com.example.imagesparsing.Trash;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import com.example.imagesparsing.Lib.ImageLibrary;
import com.example.imagesparsing.Lib.ImageLoadersClass;

public class ImageParsingViewModel extends ViewModel{
    private ImageLibrary selectedLibrary = ImageLibrary.NONE;
    private ImageView imageView ;
    private String url;
    private ImageLoadersClass imageLoaders;

    // TODO Получать контекст
    //  и проверять контекст

    private static ImageParsingViewModel instance;
    public static ImageParsingViewModel getInstance() {
        if (instance == null) {
            instance = new ImageParsingViewModel();
        }
        return instance;
    }
    public ImageLibrary getSelectedLibrary() {
        return selectedLibrary;
    }

    public void setSelectedLibrary(ImageLibrary selectedLibrary) {
        this.selectedLibrary = selectedLibrary;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageLoadersClass getImageLoaders() {
        return imageLoaders;
    }

    public void setImageLoaders(ImageLoadersClass imageLoaders) {
        this.imageLoaders = imageLoaders;
    }
}

/*

public class SharedViewModel extends ViewModel {
    private SelectedOption selectedOption = SelectedOption.NONE;

    public SelectedOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(SelectedOption selectedOption) {
        this.selectedOption = selectedOption;
    }
}*/
