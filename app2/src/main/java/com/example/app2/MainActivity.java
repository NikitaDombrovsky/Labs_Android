package com.example.app2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем источник данных
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        // Создаем адаптер для преобразования массива в представления
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
        // Присоединяем адаптер к ListView
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(adapter);

        // Добавляем элемент в адаптер
        User newUser = new User("Nathan", "San Diego");
        User newUser1 = new User("Oleg", "Snowfield");
        adapter.add(newUser);
        adapter.add(newUser1);
        //adapter.clear();
    }
}