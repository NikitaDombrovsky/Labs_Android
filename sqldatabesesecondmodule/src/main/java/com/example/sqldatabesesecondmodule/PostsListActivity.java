package com.example.sqldatabesesecondmodule;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostsListActivity extends AppCompatActivity {

    // Создает переменные для списка постов, помощника базы данных,
    // адаптера и RecyclerView.
    private ArrayList<Post> postArrayList;
    private PostsDatabaseHelper postsDBHelper;
    private PostAdapter postRVAdapter;
    private RecyclerView postRV;
    private Button deleteAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        deleteAllBtn = findViewById(R.id.idBtnDeleteAllPost);

        // Инициализируем все переменные.
        postArrayList = new ArrayList<>();
        postsDBHelper = PostsDatabaseHelper.getInstance(this);

        // Получаем список всех записей из базы данных.
        postArrayList = (ArrayList<Post>) postsDBHelper.getAllPosts();

        postRV = findViewById(R.id.idRVPost);

        postRVAdapter = new PostAdapter(this, postArrayList, new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(PostsListActivity.this, "Изменение", Toast.LENGTH_SHORT).show();
                editPost(position); // Редактируем выбранный пост.
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(PostsListActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                postsDBHelper.deletePost(postArrayList.get(position)); // Удаляем пост из базы данных.

                // Удаляем элемент из списка.
                postArrayList.remove(position);

                // Уведомляем адаптер об удалении элемента.
                postRVAdapter.notifyItemRemoved(position);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PostsListActivity.this, LinearLayoutManager.VERTICAL, false);
        postRV.setLayoutManager(linearLayoutManager);

        postRV.setAdapter(postRVAdapter);

        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsDBHelper.deleteAllPosts(); // Удаляем все посты из базы данных.
            }
        });
    }

    /**
     * Метод для редактирования поста.
     *
     * @param pos Позиция элемента в списке.
     */
    private void editPost(final int pos) {
        // Создаем диалоговое окно для редактирования поста.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Редактировать");

        // Создаем контейнер для текстовых полей.
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Первое текстовое поле для заголовка.
        final EditText inputTitle = new EditText(this);
        inputTitle.setHint("Название");

        // Второе текстовое поле для текста поста.
        final EditText inputText = new EditText(this);
        inputText.setHint("Напиши о себе");

        // Устанавливаем текущие значения заголовка и текста поста.
        inputTitle.setText(postArrayList.get(pos).getTitle());
        inputText.setText(postArrayList.get(pos).getText());

        // Добавляем текстовые поля в контейнер.
        layout.addView(inputTitle);
        layout.addView(inputText);

        // Устанавливаем контейнер в качестве представления диалога.
        builder.setView(layout);

        // Обработка нажатия на кнопку "Сохранить".
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Получаем новые значения заголовка и текста.
                String title = inputTitle.getText().toString();
                String text = inputText.getText().toString();

                Post post = new Post(title, text);

                // Обновляем запись в базе данных.
                postsDBHelper.updatePostForTitle(post);

                // Обновляем элемент в списке.
                postArrayList.set(pos, post);

                // Уведомляем адаптер об изменении элемента.
                postRVAdapter.notifyItemChanged(pos);
            }
        });

        // Кнопка отмены.
        builder.setNegativeButton("Отмена", null);

        // Показываем диалоговое окно.
        builder.show();
    }
}