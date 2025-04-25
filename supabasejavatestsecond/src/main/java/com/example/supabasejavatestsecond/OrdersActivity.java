package com.example.supabasejavatestsecond;

import static com.example.supabasejavatestsecond.SupabaseClient.SUPABASE_DOMEN;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.supabasejavatestsecond.Adapters.OrderAdapter;
import com.example.supabasejavatestsecond.Models.Order;
import com.example.supabasejavatestsecond.Models.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    public void getOrders() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchUserOrders(new SupabaseClient.SBCallback() {
            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> {
                    Log.e("getOrders", "" + t);
                });
            }

            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Order>>() {
                    }.getType();
                    List<Order> ordersList = gson.fromJson(response, type);
                    OrderAdapter adapter = new OrderAdapter(getApplicationContext(), ordersList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    Log.e("!", "1");

                });
            }
        });
    }
    public void getUser() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.fetchProfile(new SupabaseClient.SBCallback() {
            @Override
            public void onSuccess(String response) {

                runOnUiThread(() -> {
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<Profile>>() {
                    }.getType();
                    List<Profile> users = gson.fromJson(response, userListType);

                    if (users != null && !users.isEmpty()) {
                        Profile user = users.get(0);
                        String url = SUPABASE_DOMEN + "storage/v1/object/public/avatars/" + user.getAvatar_url();
                        textView.setText(user.getUsername());


                        Glide.with(OrdersActivity.this)
                                .load(url)
                                .transform(new CircleCrop())
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.error)
                                .into(imageView);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("FetchProfile", "Каво?");
            }
        });
    }

    RecyclerView recyclerView;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        recyclerView = findViewById(R.id.ordersRecV);
        textView = findViewById(R.id.userNameTxV);
        imageView = findViewById(R.id.avatarImV);

        getUser();
        getOrders();

    }
}