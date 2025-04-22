package com.example.heterogenouslistviewmodule;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvColors = (ListView) findViewById(R.id.lvColors);
        ArrayList<SimpleColor> aColors = new ArrayList<SimpleColor>();
        // Заполняем цвета в массив
        for (int i = 0; i < 10; i++){
            aColors.add(new SimpleColor("Blue " + i, SimpleColor.ColorValues.BLUE));
            aColors.add(new SimpleColor("Green " + i, SimpleColor.ColorValues.GREEN));
            aColors.add(new SimpleColor("Red " + i, SimpleColor.ColorValues.RED));
        }

        // Прикрепляем адаптер
        ColorArrayAdapter adapterColors = new ColorArrayAdapter(this, aColors);
        lvColors.setAdapter(adapterColors);
    }
}
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int type = 0;
        if (type == SimpleColor.ColorValues.RED.ordinal()) {
            Log.e("ColorValues", "RED");
        } else if (type == SimpleColor.ColorValues.BLUE.ordinal()) {
            Log.e("ColorValues", "BLUE");
        } else if (type == SimpleColor.ColorValues.GREEN.ordinal()) {
            Log.e("ColorValues", "RED");
        }
        SimpleColor.ColorValues day = SimpleColor.ColorValues.valueOf("RED");
        Log.e("ColorValues1", day.name());

        Log.e("ColorValues2", SimpleColor.ColorValues.equalsColor(
                SimpleColor.ColorValues.RED,
                SimpleColor.ColorValues.RED));
        Log.e("ColorValues3", SimpleColor.ColorValues.equalsColor(
                SimpleColor.ColorValues.RED,
                SimpleColor.ColorValues.GREEN));

    }*/
