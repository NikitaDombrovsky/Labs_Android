package com.example.heterogenrecyclermodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Элементы для отображения в RecyclerView
    private List<User> items;
    private Context context;
    private final int USER = 0, IMAGE = 1;

    // Конструктор
    public UsersRecyclerViewAdapter(Context context, List<User> items) {
        this.context = context;
        this.items = items;
    }

    // Возвращает размер набора данных (вызывается layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    // Возвращает тип представления элемента по позиции
    // для переиспользования представлений
    @Override
    public int getItemViewType(int position) {
        if (items.get(position).hometown != null){
            return USER;
        }
        else {
            return IMAGE;
        }
/*        if (items.get(position) instanceof User) {
            return USER;
        } else if (items.get(position) instanceof Integer) {
            return IMAGE;
        }*/
        //return -1;
    }



    // Этот метод создает различные объекты RecyclerView.ViewHolder
    // на основе типа представления элемента.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case USER:
                View v1 = inflater.inflate(R.layout.item_user, viewGroup, false);
                return new UserViewHolder(v1);
            case IMAGE:
                View v2 = inflater.inflate(R.layout.item_image, viewGroup, false);
                return new ImageViewHolder(v2);
        }
        throw new IllegalArgumentException("Invalid view type");
    }


    // Этот метод внутренне вызывает onBindViewHolder(ViewHolder, int) для обновления
    // содержимого RecyclerView.ViewHolder элементом на заданной позиции
    // и также настраивает некоторые приватные поля для использования RecyclerView.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case USER:
                UserViewHolder vh1 = (UserViewHolder) viewHolder;
                configureUserViewHolder(vh1, position);
                break;
            case IMAGE:
                ImageViewHolder vh2 = (ImageViewHolder) viewHolder;
                configureImageViewHolder(vh2, position);
                break;

        }
    }


    private void configureUserViewHolder(UserViewHolder vh1, int position) {
        User user = (User) items.get(position);
        if (user != null) {
            vh1.getUserNameLabel().setText("Name: " + user.name);
            vh1.getUserHometownLabel().setText("Hometown: " + user.hometown);
        }
    }

    private void configureImageViewHolder(ImageViewHolder vh2, int position) {
        Object i = items.get(position);
        vh2.getImageView().setImageResource((Integer) i);
    }
}
