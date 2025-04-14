package com.example.sqldatabesesecondmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Создаем переменные для EditText, Button и DBHandler
    private EditText postTitleEdt, postTextEdt, postGetTextEdt;
    private Button addPostBtn, getPostBtn, getAllPostBtn;
    private PostsDatabaseHelper postsDatabaseHelper;

    private void init() {
        // Инициализируем все переменные.
        postTitleEdt = findViewById(R.id.idEdtPostTitle);
        postTextEdt = findViewById(R.id.idEdtPostText);
        postGetTextEdt = findViewById(R.id.idEdtPostGetTitle);

        addPostBtn = findViewById(R.id.idBtnAddPost);
        getPostBtn = findViewById(R.id.idBtnGetPost);
        getAllPostBtn = findViewById(R.id.idBtnGetAllPost);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // Создает новый экземпляр класса DBHandler и передает ему контекст.
        // В любой Activity просто передайте контекст и используйте метод Singleton
        postsDatabaseHelper = PostsDatabaseHelper.getInstance(this);

        // Добавляем слушатель нажатия на кнопку добавления поста.
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String postTitle = postTitleEdt.getText().toString();
                String postText = postTextEdt.getText().toString();

                if (postTitle.isEmpty() && postText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Введите данные", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Вызываем метод для добавления нового поста в SQLite и передаем все наши значения в него.
                postsDatabaseHelper.addPost(new Post(
                        postTitle, postText
                ));

                toast("addPostBtn", "Запись успешно добавлена");

                postTitleEdt.setText("");
                postTextEdt.setText("");

            }
        });

        getPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getTitle = postGetTextEdt.getText().toString();

                if (getTitle.isEmpty()) {
                    toast("getPostBtn", "Введите данные");
                    return;
                }

                // Получаем пост по ID
                Post post = postsDatabaseHelper.getPostById(Integer.parseInt(getTitle));

                toast("getPostBtn", "" + post.getTitle() + "|" + post.getText());
                postTitleEdt.setText(post.getTitle());
                postTextEdt.setText(post.getText());

            }
        });

        getAllPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PostsListActivity.class);
                startActivity(i);
            }
        });

    }

    /**
     * Метод для отображения Toast и логирования.
     *
     * @param funName Название функции, откуда вызывается метод.
     * @param text Текст для отображения в Toast и логирования.
     */
    public void toast(String funName, String text) {
        Toast.makeText(MainActivity.this, "" + text, Toast.LENGTH_SHORT).show();
        Log.e("" + funName, text.toString());
    }

    /**
     * Метод для отображения Toast и логирования с разными текстами.
     *
     * @param funName Название функции, откуда вызывается метод.
     * @param toasttext Текст для отображения в Toast.
     * @param logtext Текст для логирования.
     */
    public void toast(String funName, String toasttext, String logtext) {
        Toast.makeText(MainActivity.this, "" + toasttext, Toast.LENGTH_SHORT).show();
        Log.e("" + funName, logtext.toString());
    }
}