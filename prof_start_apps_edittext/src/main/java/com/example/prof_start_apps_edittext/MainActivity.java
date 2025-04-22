package com.example.prof_start_apps_edittext;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextView outputTextView;
    EditText inputEditText;
    ColorStateList color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        TextFieldsValidate();

        inputEditText = findViewById(R.id.EdTV);
        outputTextView = findViewById(R.id.TV);

        inputEditText.setHint("Пароль сюда");
        inputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);


        color = outputTextView.getTextColors();


        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                outputTextView.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 5) {
                    outputTextView.setTextSize(10f);
                }
                if (s.length() >= 10) {
                    outputTextView.setTextColor(Color.RED);

                }
            }
        });
        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Действие при нажатии "DONE"
                    return true;
                }
                return false;
            }
        });


        TextInputLayout textInputLayout = findViewById(R.id.textField);
        boolean check = false;
        //  TextFields();
        // TODO Действия на нажатия
        // Действие при клике на иконку
/*        textInputLayout.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check){
                    textInputLayout.setTextColo
                }

            }
        });
        textInputLayout.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check){
                    textInputLayout.setTextColo
                }

            }
        });*/


    }

    TextInputLayout emailLayout, passwordLayout;
    TextInputEditText emailEditText, passwordEditText;
    boolean checkEmail = false;
    boolean checkPassword = false;

    public void init() {
        emailLayout = findViewById(R.id.emailTextLayout);
        emailEditText = findViewById(R.id.emailText);
        passwordLayout = findViewById(R.id.passwordTextLayout);
        passwordEditText = findViewById(R.id.passwordText);

    }


    public void TextFieldsValidate() {

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();
                if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailLayout.setError("Введите корректный email");
                    checkEmail = false;
                } else {
                    emailLayout.setError(null);
                    checkEmail = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > emailLayout.getCounterMaxLength())
                    emailLayout.setError("Электронная почта не может содержать больше "
                            + emailLayout.getCounterMaxLength() + "символов");
            }
        });


        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String password = passwordEditText.getText().toString();
                    if (password.length() < 6) {
                        passwordLayout.setError("Пароль должен содержать минимум 6 символов");
                        checkPassword = false;
                    } else if (password.length() >= 10) {
                        passwordLayout.setError("Пароль не должен содержать больше 10 символов");
                        checkPassword = false;
                    } else {
                        passwordLayout.setError(null);
                        checkPassword = true;
                    }
                    return true;
                }
                return false;
            }
        });
/*        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String password = passwordEditText.getText().toString();
                if (password.length() < 6) {
                    passwordLayout.setError("Пароль должен содержать минимум 6 символов");
                } else {
                    passwordLayout.setError(null);
                }
                return true;
            }
            return false;
        });*/
    }

    public void TextFields() {

        // Создание
        TextInputLayout textInputLayout = findViewById(R.id.textField);
        TextInputEditText editText = findViewById(R.id.textEditText);
        // Или так
        TextInputEditText editTextSame = findViewById(textInputLayout.getEditText().getId());

        // Получение текста
        String textLayout = textInputLayout.getEditText().getText().toString();
        String textText = editText.getText().toString();

        // Установка текста
        textInputLayout.getEditText().setText("Текстик");
        editText.setText("Текстик мой тектсик");

        // Изменение подсказки
        textInputLayout.setHint("Новая подсказка");
        editText.setHint("Уже не новая подсказка");

        // Изменение счетчика символов (только в Layout)
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(100);

        // Изменение стиля
        textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
        textInputLayout.setBoxBackgroundColor(Color.WHITE);
        textInputLayout.setBoxCornerRadii(
                10, 10,
                10, 10);

        // Установка ошибки
        textInputLayout.setError("Обязательное поле");
        textInputLayout.setErrorEnabled(true);
    }

    public void onClick(View view) {
        if (checkEmail && checkPassword) {
            outputTextView.setText("Валидация завершена");
        } else {
            outputTextView.setText("Где-то ошибка");
            if (!checkEmail) {
                emailLayout.setError("Обязательное поле");
            }

            if (!checkPassword) {
                passwordLayout.setError("Обязательное поле");
            }
        }

    }
/*    public void onClick(View view) {
        // String input = inputEditText.getText().toString();
        outputTextView.setText("");

    }*/
}