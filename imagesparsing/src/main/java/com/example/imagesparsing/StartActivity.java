package com.example.imagesparsing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imagesparsing.Lib.ImageLibrary;

public class StartActivity extends AppCompatActivity {

    Button pickPicassoBtn,pickGlideBtn, pickFrescoBtn, pickCoilBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Create your buttons and set their onClickListener to "this"
        pickPicassoBtn = (Button) findViewById(R.id.pickPicassoBtn);
        pickGlideBtn = (Button) findViewById(R.id.pickGlideBtn);
        pickFrescoBtn = (Button) findViewById(R.id.pickFrescoBtn);
        pickCoilBtn = (Button) findViewById(R.id.pickCoilBtn);

        //ImageParsingViewModel viewModel = new ViewModelProvider(this).get(ImageParsingViewModel.class);

        //ImageParsingImpl imageParsingRepository ;



        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String text = clickedButton.getText().toString();

                switch(text) {
                    case "Picasso":
/*                        // В первой Activity
                        DataHolder.getInstance().setImageUrl("https://example.com/image.jpg");
                        startActivity(new Intent(this, SecondActivity.class));*/

                        DataBinding.saveSelectedLibrary(ImageLibrary.PICASSO);
                        //viewModel.setSelectedLibrary(ImageLibrary.PICASSO);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));//.putExtra("Type", "Picasso"));
                        break;
                    case "Glide":
                        //viewModel.setSelectedLibrary(ImageLibrary.GLIDE);
                        DataBinding.saveSelectedLibrary(ImageLibrary.GLIDE);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));//.putExtra("Type", "Glide"));
                        break;
                    case "Fresco":
                        DataBinding.saveSelectedLibrary(ImageLibrary.GLIDE);
                       // startActivity(new Intent(getApplicationContext(), FrescoActivity.class));
                        break;
                    case "Coil":
                        //viewModel.setSelectedLibrary(ImageLibrary.COIL);
                        DataBinding.saveSelectedLibrary(ImageLibrary.COIL);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));//.putExtra("Type", "Glide"));
                        break;
                }
            }
        };
        pickPicassoBtn.setOnClickListener(onClickListener);
        pickGlideBtn.setOnClickListener(onClickListener);
        pickFrescoBtn.setOnClickListener(onClickListener);
        pickCoilBtn.setOnClickListener(onClickListener);
    }



}