package com.example.recyclerviewmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Создаем базовый адаптер, расширяющийся от RecyclerView.Adapter
// Обратите внимание, что указываем пользовательский ViewHolder,
// который дает доступ к представлениям
public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    // Сохраняем переменную для контактов
    private List<Contact> mContacts;
    private Context context;

    // Передаем массив контактов в конструктор
    public ContactsAdapter(List<Contact> contacts, Context context) {
        mContacts = contacts;
        this.context = context;
    }
    // Обычно включает в себя создание макета из XML и возврат холдера
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Создаем пользовательский макет
        View contactView = inflater.inflate(R.layout.item, parent, false);

        // Возвращаем новый экземпляр холдера
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Включает в себя заполнение данных в элемент через холдер
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Получаем модель данных на основе позиции
        Contact contact = mContacts.get(position);

        // Устанавливаем представления элемента на основе ваших представлений и модели данных
        TextView textView = holder.nameTextView;
        textView.setText(contact.getName());
        Button button = holder.messageButton;
        button.setText(contact.isOnline() ? "Message" : "Offline");
        button.setEnabled(contact.isOnline());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContacts.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    // Возвращает общее количество элементов в списке
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
/*    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }*/
    /***** Создание OnItemClickListener *****/

    // Определяем интерфейс слушателя
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Определяем переменную-член для слушателя
    private OnItemClickListener listener;

    // Определяем метод, который позволяет родительской активности или фрагменту определить слушателя
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Предоставляем прямую ссылку на каждый из представлений в элементе данных
    // Используется для кэширования представлений внутри элемента макета для быстрого доступа
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Ваш холдер должен содержать переменную-член
        // для любого представления, которое будет установлено при рендеринге строки
        public TextView nameTextView;
        public Button messageButton;

        // Мы также создаем конструктор, который принимает всю строку элемента
        // и выполняет поиск представлений для поиска каждого подпредставления
        public ViewHolder(View itemView) {
            // Сохраняем itemView в общедоступной окончательной переменной,
            // которую можно использовать
            // для доступа к контексту из любого экземпляра ViewHolder.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
            // Настраиваем обработчик кликов
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Триггер клика вверх к адаптеру при клике
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });

        }
    }

    /*
        // Предоставляем прямую ссылку на каждый из представлений в элементе данных
    // Используется для кэширования представлений внутри элемента макета для быстрого доступа
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Ваш холдер должен содержать переменную-член
        // для любого представления, которое будет установлено при рендеринге строки
        public TextView nameTextView;
        public Button messageButton;

        // Мы также создаем конструктор, который принимает всю строку элемента
        // и выполняет поиск представлений для поиска каждого подпредставления
        public ViewHolder(View itemView) {
            // Сохраняем itemView в общедоступной окончательной переменной,
            // которую можно использовать
            // для доступа к контексту из любого экземпляра ViewHolder.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);

            // Присоединяем обработчик кликов к всей строке
            itemView.setOnClickListener(this);
        }
        // Обрабатывает клик по строке
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // получаем позицию элемента
            if (position != RecyclerView.NO_POSITION) { // Проверяем, был ли элемент удален, но пользователь кликнул по нему до того, как UI его удалил
                Contact contact = mContacts.get(position);
                //User user = users.get(position);
                // Мы можем получить доступ к данным внутри представлений
                Toast.makeText(context, nameTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }
     */


}