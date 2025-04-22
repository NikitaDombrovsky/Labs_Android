package com.example.heterogenrecyclermodule;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Test1();
/*        ComplexRecyclerViewAdapter complexRecyclerViewAdapter =
                new ComplexRecyclerViewAdapter(this, getSampleArrayList());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(complexRecyclerViewAdapter);*/

    }
    public void Test1(){
        ComplexRecyclerViewAdapter complexRecyclerViewAdapter =
                new ComplexRecyclerViewAdapter(this, getSampleArrayList());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(complexRecyclerViewAdapter);
    }
    public void Test2(){
        ComplexRecyclerViewAdapter complexRecyclerViewAdapter =
                new ComplexRecyclerViewAdapter(this, getSampleArrayList());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(complexRecyclerViewAdapter);
    }
    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new User("Anna Ivanova", "Moscow"));
        items.add(new User("Sergey Petrov", "Saint Petersburg"));
        items.add(R.drawable.test_image);
        items.add(new User("Olga Smirnova", "Novosibirsk"));
        items.add(R.drawable.test_image2);
        items.add(new User("Alexey Volkov", "Yekaterinburg"));
        return items;
    }
    private ArrayList<User> getSampleUsersArrayList() {
        ArrayList<User> items = new ArrayList<>();
        items.add(new User("Anna Ivanova", "Moscow"));
        items.add(new User("Sergey Petrov", "Saint Petersburg"));
        items.add(new User("Sergey1 Petrov1", R.drawable.test_image));
        items.add(new User("Olga Smirnova", "Novosibirsk"));
        items.add(new User("Alexey Volkov", R.drawable.test_image2));
        items.add(new User("Alexey Volkov", "Yekaterinburg"));
        return items;
    }

/*    private void bindDataToAdapter() {
        // Bind adapter to recycler view object
        recyclerView.setAdapter(new ComplexRecyclerViewAdapter(getSampleArrayList()));
    }*/
}