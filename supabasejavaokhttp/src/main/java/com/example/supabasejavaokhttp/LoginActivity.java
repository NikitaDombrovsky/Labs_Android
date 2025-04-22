package com.example.supabasejavaokhttp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supabasejavaokhttp.Model.Auth;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    enum TypeRequest {
        LOGIN,
        AUTH
    }

    String email = "nikita.nikita55@mail.ru";
    String password = "123456789";

    public void AuthImpl() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.authWithEmail(email, password, new SupabaseClient.Callback() {
            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> {
                    Gson gson = new Gson();
                    Auth auth = gson.fromJson(response, Auth.class);
                    Log.e("Supabase", "Супергуд" + auth.access_token);
                    Toast.makeText(LoginActivity.this, "" + auth.user.email, Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> {
                    Log.e("SupabaseError", "Error fetching data", t);
                    Toast.makeText(LoginActivity.this, "ОЙ йой", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    public void LoginImpl() {
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.loginWithEmail(email, password, new SupabaseClient.Callback() {
            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> {
                    Gson gson = new Gson();
                    Auth auth = gson.fromJson(response, Auth.class);
                    Log.e("Supabase", "Супергуд" + auth.access_token);
                    Toast.makeText(LoginActivity.this, "" + auth.user.email, Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> {
                    Log.e("SupabaseError", "Error fetching data", t);
                    Toast.makeText(LoginActivity.this, "ОЙ йой", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
    public void updateProfile(){
        SupabaseClient supabaseClient = new SupabaseClient();
        supabaseClient.updateProfile(new SupabaseClient.Callback() {
            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> {
/*                    Gson gson = new Gson();
                    Auth auth = gson.fromJson(response, Auth.class);*/
                    Log.e("Supabase", "Супергуд" );
                    Toast.makeText(LoginActivity.this, "Супергуд", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> {
                    Log.e("SupabaseError", "Error fetching data", t);
                    Toast.makeText(LoginActivity.this, "ОЙ йой", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TypeRequest type = TypeRequest.LOGIN;
        switch (type) {
            case LOGIN:
                LoginImpl();
                break;
            case AUTH:
                AuthImpl();
                break;
        }
        updateProfile();

    }
}