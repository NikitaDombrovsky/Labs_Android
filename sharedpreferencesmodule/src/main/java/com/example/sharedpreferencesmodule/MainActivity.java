package com.example.sharedpreferencesmodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    // Класс для организации ключей SharedPreferences
    public static class Profile {
        // Имя файла SharedPreferences
        public static final String PREFS_NAME = "ProfileData";
        // Ключи для хранения данных
        public static final String KEY_USERNAME = "userName";
        public static final String DEF_USERNAME = "defaultUsername";
        public static final String KEY_EMAIL = "userEmail";
        public static final String DEF_EMAIL = "defaultEmail";
        public static final String KEY_PASSWORD = "userPassword";
        public static final String DEF_PASSWORD = "defaultPassword";
    }

    // Константы для сообщений Toast
    private static final String TOAST_SAVED = "Сохранено";
    private static final String TOAST_LOADED = "Загружено";
    private static final String TOAST_DELETED = "Удалено";

    // Объект SharedPreferences для работы с сохраненными данными
    SharedPreferences sharedPreferences;
    // Поля для ввода данных
    private TextInputLayout userName, userEmail, userPassword;
    private MaterialSwitch switchPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        switchPrefs = findViewById(R.id.isEncryptedDataSwitch);
        // Инициализация SharedPreferences
        initPreferences();

        switchPrefs.setOnCheckedChangeListener((buttonView, isChecked) -> {
            initPreferences();
        });

        userName = findViewById(R.id.nameTextLayout);
        userEmail = findViewById(R.id.emailTextLayout);
        userPassword = findViewById(R.id.passwordTextLayout);

        // Кнопка для сохранения данных
        Button saveBtn = findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(v -> {
            // Проверка на пустые поля перед сохранением
            if (validateInputTexts(userName, userPassword, userEmail)) {
                saveData(
                        userName.getEditText().getText().toString(),
                        userPassword.getEditText().getText().toString(),
                        userEmail.getEditText().getText().toString()
                );
                // Очистка полей после сохранения
                clearFields(userName, userEmail, userPassword);
            }
        });

        // Кнопка для загрузки данных
        Button loadBtn = findViewById(R.id.btnLoad);
        loadBtn.setOnClickListener(v -> loadData(userName, userPassword, userEmail));

        // Кнопка для удаления данных
        Button clearBtn = findViewById(R.id.btnClear);
        clearBtn.setOnClickListener(v -> deleteData());
    }

    private void initPreferences() {
        try {
            if (switchPrefs.isChecked()) {
                String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                sharedPreferences = EncryptedSharedPreferences.create(
                        Profile.PREFS_NAME,
                        masterKeyAlias,
                        this,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            } else {
                sharedPreferences = getSharedPreferences(Profile.PREFS_NAME, Context.MODE_PRIVATE);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error initializing preferences", e);
        }
    }

    // Метод для валидации полей ввода
    private boolean validateInputTexts(TextInputLayout... texts) {
        boolean isValid = true;

        for (TextInputLayout text : texts) {
            // Проверка на пустоту поля
            if (text.getEditText() == null ||
                    TextUtils.isEmpty(text.getEditText().getText().toString())) {
                text.setError("Заполните поле");
                isValid = false;
            } else {
                text.setError(null); // Очистка ошибки, если поле заполнено
            }
        }
        return isValid;
    }

    // Метод для сохранения данных в SharedPreferences
    private void saveData(String username, String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Сохранение данных по ключам
        editor.putString(Profile.KEY_USERNAME, username);
        editor.putString(Profile.KEY_EMAIL, email);
        editor.putString(Profile.KEY_PASSWORD, password);
        editor.apply(); // Применение изменений
        showToast(TOAST_SAVED); // Уведомление пользователя
    }

    // Метод для загрузки данных из SharedPreferences
    private void loadData(TextInputLayout... texts) {
        // Загрузка данных по ключам и установка их в поля ввода
        texts[0].getEditText().setText(sharedPreferences.getString(Profile.KEY_USERNAME, Profile.DEF_USERNAME));
        texts[1].getEditText().setText(sharedPreferences.getString(Profile.KEY_EMAIL, Profile.DEF_EMAIL));
        texts[2].getEditText().setText(sharedPreferences.getString(Profile.KEY_PASSWORD, Profile.DEF_PASSWORD));
        showToast(TOAST_LOADED); // Уведомление пользователя
    }

    // Метод для удаления всех данных из SharedPreferences
    private void deleteData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Очистка всех данных
        editor.apply(); // Применение изменений
        showToast(TOAST_DELETED); // Уведомление пользователя
    }

    // Метод для очистки полей ввода
    private void clearFields(TextInputLayout... inputFields) {
        for (TextInputLayout text : inputFields) {
            if (text.getEditText() != null) {
                text.getEditText().setText(""); // Очистка текста в поле
            }
        }
    }

    // Метод для отображения Toast-уведомлений
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}