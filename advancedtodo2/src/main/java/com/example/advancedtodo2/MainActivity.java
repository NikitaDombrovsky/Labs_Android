package com.example.advancedtodo2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Список для хранения задач
    private ArrayList<String> items;
    // Адаптер для связи списка задач с ListView
    private ArrayAdapter<String> itemsAdapter;
    // ListView для отображения задач
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        // Инициализируем список задач
        items = new ArrayList<String>();
        // Загружаем задачи из файла
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        // Настраиваем слушатель долгого нажатия на элементы ListView
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Удаляем элемент из списка по позиции
                        items.remove(pos);
                        // Обновляем адаптер, чтобы изменения отобразились в ListView
                        itemsAdapter.notifyDataSetChanged();
                        // Сохраняем обновленный список задач в файл
                        writeItems();
                        return true;
                    }
                });
    }

    // Метод для чтения задач из файла
    private void readItems() {
        // Получаем директорию для хранения файлов приложения
        File filesDir = getFilesDir();
        // Выбираем файл для хранения задач
        File todoFile = new File(filesDir, "todo.txt");
        try {
            // Читаем строки из файла и добавляем их в список задач
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            // Если файл не существует или произошла ошибка, инициализируем пустой список
            items = new ArrayList<String>();
        }
    }

    // Метод для записи задач в файл
    private void writeItems() {
        // Получаем директорию для хранения файлов приложения
        File filesDir = getFilesDir();
        // Выбираем файл для хранения задач
        File todoFile = new File(filesDir, "todo.txt");
        try {
            // Записываем список задач в файл
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            // В случае ошибки выводим стектрейс
            e.printStackTrace();
        }
    }

    // Метод для добавления новой задачи
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
    }
}