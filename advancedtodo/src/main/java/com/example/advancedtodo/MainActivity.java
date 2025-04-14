package com.example.advancedtodo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TodoAdapter todoAdapter; // Адаптер для RecyclerView
    private RecyclerView recyclerView; // RecyclerView для отображения списка задач
    ArrayList<TODO> todoList; // Список задач

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoList = new ArrayList<>();

        recyclerView = findViewById(R.id.rvItems); // Инициализация RecyclerView

        readItems(); // Загрузка задач из файла

        // Настройка адаптера
        todoAdapter = new TodoAdapter(this, todoList, new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                createOrEditTask(position); // Редактирование задачи при клике
            }

            @Override
            public void onItemLongClick(int position) {
                todoList.remove(position); // Удаление задачи при долгом клике
                todoAdapter.notifyItemRemoved(position); // Уведомление адаптера об удалении
                writeItems(); // Сохранение изменений в файл
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(todoAdapter); // Установка адаптера для RecyclerView

        // Настройка SearchView для поиска задач
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                todoAdapter.getFilter().filter(query); // Фильтрация при отправке запроса
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                todoAdapter.getFilter().filter(newText); // Фильтрация при изменении текста
                return false;
            }
        });

    }

    // Обработка нажатия на кнопку добавления задачи
    public void onAddItem(View v) {
        createOrEditTask(-1); // Создание новой задачи
    }

   // Метод для создания или редактирования задачи
    private void createOrEditTask(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(pos == -1 ? "Создать задачу" : "Редактировать задачу");

        // Создаем контейнер для текстовых полей
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Первое текстовое поле (заголовок)
        final EditText inputTitle = new EditText(this);
        inputTitle.setHint("Заголовок");

        // Второе текстовое поле (описание)
        final EditText inputText = new EditText(this);
        inputText.setHint("Описание");

        // Если редактируем задачу, заполняем поля текущими значениями
        if (pos != -1) {
            inputTitle.setText(todoList.get(pos).getTitle());
            inputText.setText(todoList.get(pos).getText());
        }

        layout.addView(inputTitle);
        layout.addView(inputText);

        // Устанавливаем контейнер в качестве представления диалога
        builder.setView(layout);

        // Обработка нажатия на кнопку "Сохранить"
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = inputTitle.getText().toString();
                String text = inputText.getText().toString();

                if (pos == -1) {
                    // Создаем новую задачу
                    todoList.add(new TODO(title, text));
                    todoAdapter.notifyItemInserted(todoList.size() - 1); // Уведомляем адаптер о добавлении
                } else {
                    // Редактируем существующую задачу
                    todoList.set(pos, new TODO(title, text));
                    todoAdapter.notifyItemChanged(pos); // Уведомляем адаптер об изменении
                }
                writeItems(); // Сохранение изменений в файл
            }
        });

        builder.setNegativeButton("Отмена", null); // Кнопка отмены
        builder.show(); // Показ диалога
    }

    // Метод для сохранения задач в файл
    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todoList.txt");
        try {
            List<String> lines = new ArrayList<>();
            for (TODO item : todoList) {
                lines.add(TODO.toString(item)); // Формируем строку для записи
            }
            FileUtils.writeLines(todoFile, lines); // Запись в файл
        } catch (IOException e) {
            Log.e("writeItems", e.getLocalizedMessage()); // Логирование ошибки
        }
    }

    // Метод для загрузки задач из файла
    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todoList.txt");
        try {
            List<String> lines = FileUtils.readLines(todoFile, String.valueOf(Charset.defaultCharset()));
            todoList.clear();
            for (String line : lines) {
                todoList.add(TODO.fromString(line)); // Преобразование строки в объект TODO
            }
        } catch (IOException e) {
            todoList = new ArrayList<>(); // Если файл не найден, создаем пустой список
        }
    }
}