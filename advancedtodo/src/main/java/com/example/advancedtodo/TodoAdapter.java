package com.example.advancedtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements Filterable {
    private List<TODO> todoList; // Список всех задач
    private List<TODO> filteredtodoList; // Список задач после фильтрации
    private OnItemClickListener listener; // Слушатель для обработки кликов
    private Context context;

    // Интерфейс для обработки кликов по элементам списка
    public interface OnItemClickListener {
        void onItemClick(int position); // Обработка обычного клика

        void onItemLongClick(int position); // Обработка долгого клика
    }

    // Реализация фильтра для поиска
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if (charSequenceString.isEmpty()) {
                    filteredtodoList = todoList; // Если строка поиска пуста, показываем все задачи
                } else {
                    List<TODO> filteredList = new ArrayList<>();
                    for (TODO todos : todoList) {
                        // Фильтруем задачи по заголовку
                        if (todos.getTitle().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(todos);
                        }
                    }
                    filteredtodoList = filteredList;
                }
                FilterResults results = new FilterResults();
                results.values = filteredtodoList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredtodoList = (List<TODO>) results.values;
                notifyDataSetChanged(); // Обновляем RecyclerView после фильтрации
            }
        };
    }

    // Конструктор
    public TodoAdapter(Context context, List<TODO> todoItems, OnItemClickListener listener) {
        this.context = context;
        this.todoList = todoItems;
        this.listener = listener;
        this.filteredtodoList = todoItems;
    }

    // Создание ViewHolder для RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_text, parent, false); // Загрузка макета для элемента списка
        return new ViewHolder(view);
    }

    // Привязка данных к ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TODO item = filteredtodoList.get(position);
        holder.textViewTitle.setText(item.getTitle()); // Установка заголовка задачи
        holder.textViewText.setText(item.getText()); // Установка текста задачи

        // Обработка клика по элементу
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));

        // Обработка долгого клика по элементу
        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(position);
            return true;
        });
    }

    // Возвращает количество элементов в списке
    @Override
    public int getItemCount() {
        return filteredtodoList.size();
    }

    // ViewHolder для отображения элементов списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle; // Поле для заголовка задачи
        public TextView textViewText; // Поле для текста задачи

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle); // Инициализация поля заголовка
            textViewText = itemView.findViewById(R.id.textViewText); // Инициализация поля текста
        }
    }
}