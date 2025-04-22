package com.example.app2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {
    // Кэш поиска представлений
    private static class ViewHolder {
        TextView name;
        TextView home;
        Button btn;
    }

    private ArrayList<User> users_;
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, R.layout.item_layout, users);
        this.users_ = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Получаем данные для этой позиции
        User user = getItem(position);
        // Проверяем, используется ли существующее представление для переиспользования,
        // иначе раздуваем представление
        ViewHolder viewHolder; // кэш поиска представлений, хранящийся в теге
        if (convertView == null) {
            // Если нет представления для переиспользования,
            // раздуваем новое представление для строки
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.home = (TextView) convertView.findViewById(R.id.tvHome);
            viewHolder.btn = convertView.findViewById(R.id.btButton);

            // Кэшируем объект viewHolder внутри свежего представления
            convertView.setTag(viewHolder);
        } else {
            // Представление переиспользуется, извлекаем объект viewHolder из тега
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Заполняем данные из объекта данных через объект viewHolder
        // в шаблон представления.

        // Присоединяем обработчик событий
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int position = (Integer) view.getTag();
                // Получаем доступ к позиции строки, чтобы получить правильный элемент данных
                User user = getItem(position);
                // Выводим в логи информацию по позиции
                Log.e("Button " + position , user.name);
                users_.remove(position);
                notifyDataSetChanged();
            }
        });

        viewHolder.name.setText(user.name);
        viewHolder.home.setText(user.hometown);
        // Возвращаем завершенное представление для отображения на экране
        return convertView;
    }
}/*
public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

*//*    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Получаем данные для этой позиции
        User user = getItem(position);
        // Проверяем, используется ли существующее представление для переиспользования,
        // иначе создаем представление
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout,
                    parent, false);
        }
        // Ищем представления для заполнения данными
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Заполняем данные в шаблон представления, используя объект данных
        tvName.setText(user.name);
        tvHome.setText(user.hometown);
        // Возвращаем завершенное представление для отображения на экране
        return convertView;
    }*//*

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Получаем данные для этой позиции
        User user = getItem(position);
        // Проверяем, используется ли существующее представление для переиспользования,
        // иначе создаем представление
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout,
                    parent, false);
        }
        // Ищем представления для заполнения данными
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // TODO
        Button btButton = (Button) convertView.findViewById(R.id.btButton);
        // Кэшируем позицию строки внутри кнопки с помощью `setTag`
        btButton.setTag(position);
        // Присоединяем обработчик событий
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Получаем доступ к позиции строки, чтобы получить правильный элемент данных
                User user = getItem(position);
                // Выводим в логи информацию по позиции
                Log.e("Button " + position , user.name);
                // Делайте, что хотите...
            }
        });
        // TODO
        // Заполняем данные в шаблон представления, используя объект данных
        tvName.setText(user.name);
        tvHome.setText(user.hometown);
        // Возвращаем завершенное представление
        return convertView;
    }
    public class UsersAdapter extends ArrayAdapter<User> {
        // Кэш поиска представлений
        private static class ViewHolder {
            TextView name;
            TextView home;
        }

        public UsersAdapter(Context context, ArrayList<User> users) {
            super(context, R.layout.item_user, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Получаем данные для этой позиции
            User user = getItem(position);
            // Проверяем, используется ли существующее представление для переиспользования, иначе раздуваем представление
            ViewHolder viewHolder; // кэш поиска представлений, хранящийся в теге
            if (convertView == null) {
                // Если нет представления для переиспользования, раздуваем новое представление для строки
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_user, parent, false);
                viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
                viewHolder.home = (TextView) convertView.findViewById(R.id.tvHome);
                // Кэшируем объект viewHolder внутри свежего представления
                convertView.setTag(viewHolder);
            } else {
                // Представление переиспользуется, извлекаем объект viewHolder из тега
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // Заполняем данные из объекта данных через объект viewHolder
            // в шаблон представления.
            viewHolder.name.setText(user.name);
            viewHolder.home.setText(user.hometown);
            // Возвращаем завершенное представление для отображения на экране
            return convertView;
        }
    }
}*/