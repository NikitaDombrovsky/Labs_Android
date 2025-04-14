package com.example.sqldatabesesecondmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    // Переменные для списка постов и контекста.
    private ArrayList<Post> postArrayList;
    private Context context;
    private OnItemClickListener listener; // Слушатель для обработки кликов.

    // Интерфейс для обработки кликов по элементам списка.
    public interface OnItemClickListener {
        void onItemClick(int position); // Обработка обычного клика.

        void onItemLongClick(int position); // Обработка долгого клика.
    }

    /**
     * Конструктор адаптера.
     *
     * @param context       Контекст приложения.
     * @param postArrayList Список постов для отображения.
     * @param listener      Слушатель для обработки кликов.
     */
    public PostAdapter(Context context, ArrayList<Post> postArrayList, OnItemClickListener listener) {
        this.postArrayList = postArrayList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Создаем представление для элемента списка, используя макет post_item.
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        // Устанавливаем данные для элемента списка.
        Post post = postArrayList.get(position);
        holder.postTitleTV.setText(post.getTitle()); // Устанавливаем заголовок поста.
        holder.postTextTV.setText(post.getText());   // Устанавливаем текст поста.

        // Обработка обычного клика по элементу списка.
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));

        // Обработка долгого клика по элементу списка.
        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(position);
            return true; // Возвращаем true, чтобы указать, что событие обработано.
        });
    }

    @Override
    public int getItemCount() {
        // Возвращаем количество элементов в списке.
        return postArrayList.size();
    }

    /**
     * Класс ViewHolder для хранения ссылок на элементы интерфейса.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Переменные для текстовых полей.
        private TextView postTitleTV, postTextTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Инициализируем текстовые поля.
            postTitleTV = itemView.findViewById(R.id.idTVPostTitle);
            postTextTV = itemView.findViewById(R.id.idTVPostText);
        }
    }
}