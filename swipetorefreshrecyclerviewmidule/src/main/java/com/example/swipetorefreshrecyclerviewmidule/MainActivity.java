package com.example.swipetorefreshrecyclerviewmidule;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.divider.MaterialDividerItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private ArrayList<User> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Настройка RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adapter = new UserAdapter(this, getSampleUsersArrayList());
        recyclerView.setAdapter(adapter);

        MaterialDividerItemDecoration divider = new MaterialDividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(divider);

        // Найти представление SwipeContainer
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        // Настроить слушателя обновления
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Ваш код для обновления списка здесь.
                // Убедитесь, что вы вызываете swipeContainer.setRefreshing(false)
                // после успешного завершения.
                refreshData();
            }
        });
        // Настроить цвета обновления
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
    private ArrayList<User> getSampleUsersArrayList() {
        items = new ArrayList<>();
        items.add(new User("Anna Ivanova", "Hello", R.drawable.image1));
        items.add(new User("Sergey Petrov", "Hi", R.drawable.image2));
        items.add(new User("Olga Smirnova", "Privet", R.drawable.image3));
        items.add(new User("Alexey Volkov", "Hello!", R.drawable.test_image));
        return items;
    }

    private void refreshData() {
        // Здесь вы можете обновить данные в адаптере
        // Например, загрузить новые данные с сервера или из базы данных

        // Имитация задержки для демонстрации
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Обновите данные в адаптере
                adapter.updateData(getNewData(items));

                // Остановите анимацию обновления
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000); // Задержка в 2 секунды
    }
    private ArrayList<User> getNewData(ArrayList<User> oldData) {
        ArrayList<User> newData = new ArrayList<>(oldData);
        newData.add(new User("New User", "OK", R.drawable.test_image));
        return newData;
    }
/*    private List<User> getNewData(List<User> oldData) {
        List<User> newData = new ArrayList<>(oldData);


        // Добавляем новые данные
        newData.add(new User("New User", "OK", R.drawable.test_image));

        // Обновляем items, чтобы они содержали новые данные
        //items = new ArrayList<>(newData);
*//*
        // Верните новые данные, которые нужно отобразить
        List<User> newData = items;
        newData.add(new User("New User", "Omsk", R.drawable.test_image));

        // Заполните newData новыми данными*//*
        return newData;
    }*/
}