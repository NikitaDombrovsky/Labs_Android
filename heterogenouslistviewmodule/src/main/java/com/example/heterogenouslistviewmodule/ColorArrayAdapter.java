package com.example.heterogenouslistviewmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorArrayAdapter extends ArrayAdapter<SimpleColor> {
    public ColorArrayAdapter(Context context, ArrayList<SimpleColor> colors) {
        super(context, 0, colors);
    }

    // Возвращает количество типов View,
    // которые будут созданы методом getView(int, View, ViewGroup)
    @Override
    public int getViewTypeCount() {
        // Возвращает количество типов View, которые будут созданы этим адаптером
        // Каждый тип представляет набор View, которые могут быть преобразованы

        // Общее количество типов — это количество значений enum
        return SimpleColor.ColorValues.values().length;
    }

    // Получает тип View, который будет создан методом getView(int, View, ViewGroup)
    // для указанного элемента.
    @Override
    public int getItemViewType(int position) {
        // Возвращает целое число, представляющее тип View.
        // Обратите внимание: целые числа должны быть в диапазоне от 0 до getViewTypeCount() - 1
        return getItem(position).color.ordinal();
    }

    // Получает View, которое отображает данные в указанной позиции в наборе данных.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View должно быть создано на основе типа,
        // возвращенного из `getItemViewType(int position)`
        // convertView гарантированно будет "правильным" типом для переиспользования
        // Получаем данные для этой позиции
        SimpleColor color = getItem(position);
        // Проверяем, используется ли существующий View для переиспользования, иначе создаем новый
        if (convertView == null) {
            // Получаем тип данных для этой позиции
            int type = getItemViewType(position);
            // Инфлейтим XML-макет на основе типа
            convertView = getInflatedLayoutForType(type);
        }
        // Ищем View для заполнения данными
        TextView tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        if (tvLabel != null) {
            // Заполняем данные в шаблон View с использованием объекта данных
            tvLabel.setText(color.label);
        }
        // Возвращаем завершенный View для отображения на экране
        return convertView;
    }
    // На основе типа отвечает за возврат правильного инфлейтированного XML-макета
    private View getInflatedLayoutForType(int type) {
        if (type == SimpleColor.ColorValues.BLUE.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_blue_color, null);
        } else if (type == SimpleColor.ColorValues.RED.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_red_color, null);
        } else if (type == SimpleColor.ColorValues.GREEN.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_green_color, null);
        } else {
            return null;
        }
    }
}