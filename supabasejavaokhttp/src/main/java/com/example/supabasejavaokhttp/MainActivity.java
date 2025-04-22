package com.example.supabasejavaokhttp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.supabasejavaokhttp.Model.Orders;
import com.example.supabasejavaokhttp.Model.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements SupabaseClient.Callback {

    enum TypeRequest{
        TODO,
        ORDER
    }
    TypeRequest type;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.userNameTxV);
        imageView = findViewById(R.id.imageProfile);
        type = TypeRequest.ORDER;
        SupabaseClient supabaseClient = new SupabaseClient();

        supabaseClient.fetchProfile(new SupabaseClient.Callback() {
            @Override
            public void onSuccess(String response) {

                runOnUiThread(() -> {
                    //String json = "[{\"id\":\"4125a011-75aa-4f45-a573-317dcc0242f0\",\"username\":\"among\",\"avatar_url\":\"http://url.jpg\"}]";

                    // Парсинг JSON с помощью GSON
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<Profile>>() {
                    }.getType();
                    List<Profile> users = gson.fromJson(response, userListType);

                    if (users != null && !users.isEmpty()) {
                        Profile firstUser = users.get(0);

                        // Установка текста
                        textView.setText(firstUser.getUsername());

                        // Загрузка изображения с помощью Glide
                        Glide.with(MainActivity.this)
                                .asGif()
                                .load(firstUser.getAvatar_url())
                                .transform(new CircleCrop())
                                .placeholder(R.drawable.ic_launcher_foreground) // изображение-заглушка
                                .error(R.drawable.ic_launcher_background) // изображение при ошибке
                                .into(imageView);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("FetchProfile", "Каво?");
            }
        });

        switch (type){
            case TODO:
                supabaseClient.fetchTodos(this);
                break;
            case ORDER:
                supabaseClient.getOrders(this);
                break;
        }


    }
    @Override
    public void onSuccess(String response) {
        runOnUiThread(() -> {
            try {
                Gson gson = new Gson();
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                switch (type){
                    case TODO:
                        Type listType = new TypeToken<List<TodoItem>>(){}.getType();
                        List<TodoItem> todoItems = gson.fromJson(response, listType);
                        // Теперь у вас есть список объектов TodoItem
                        for (TodoItem item : todoItems) {
                            Log.d("TodoItem", "ID: " + item.getId() + ", Task: " + item.getTask());
                        }
                        // Пример отображения в RecyclerView

                        TodoAdapter adapter = new TodoAdapter(todoItems);
                        recyclerView.setAdapter(adapter);
                        break;
                    case ORDER:
                        Type ordersType = new TypeToken<List<Orders>>(){}.getType();
                        List<Orders> items = gson.fromJson(response, ordersType);

                        OrdersAdapter ordersAdapter = new OrdersAdapter(this, items);
                        recyclerView.setAdapter(ordersAdapter);
                        break;
                }

/*
                Type listType = new TypeToken<List<TodoItem>>(){}.getType();
                List<TodoItem> todoItems = gson.fromJson(response, listType);
*/





            } catch (Exception e) {
                Log.e("GsonError", "Error parsing JSON", e);
                Toast.makeText(this, "Error parsing data", Toast.LENGTH_SHORT).show();
            }
        });
    }
/*    @Override
    public void onSuccess(String response) {
        runOnUiThread(() -> {
            // Обработка успешного ответа
            Log.d("SupabaseResponse", response);
            Toast.makeText(this, "Data fetched successfully", Toast.LENGTH_SHORT).show();

            // Здесь можно парсить JSON и обновлять UI
        });
    }*/

    @Override
    public void onFailure(Throwable t) {
        runOnUiThread(() -> {
            // Обработка ошибки
            Log.e("SupabaseError", "Error fetching data", t);
            Toast.makeText(this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}*/
