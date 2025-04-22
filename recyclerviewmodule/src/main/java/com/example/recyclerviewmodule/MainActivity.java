package com.example.recyclerviewmodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.divider.MaterialDividerItemDecoration;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Найти RecyclerView в макете активности
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Инициализировать контакты
        contacts = Contact.createContactsList(120);
        // Создать адаптер, передав в него образец данных пользователей
        ContactsAdapter adapter = new ContactsAdapter(contacts, this);
        // Прикрепить адаптер к RecyclerView для заполнения элементов
        rvContacts.setAdapter(adapter);
        // Установить менеджер макета для позиционирования элементов
        // rvContacts.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContacts.setLayoutManager(layoutManager);

        // contacts.size() - 1 - это позиция последнего элемента
        adapter.notifyItemInserted(contacts.size() - 1);
        // обновление на основе адаптера
        rvContacts.scrollToPosition(adapter.getItemCount() - 1);

        adapter.notifyItemInserted(0);
        rvContacts.scrollToPosition(0);   // позиция индекса 0

        rvContacts.setHasFixedSize(true);

        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String name = contacts.get(position).getName();
                Toast.makeText(MainActivity.this, name + " was clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvContacts);


/*        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvContacts.addItemDecoration(itemDecoration);*/
        rvContacts.setItemAnimator(new SlideInUpAnimator());
/*        val divider = MaterialDividerItemDecoration(context!!, LinearLayoutManager.VERTICAL *//*или LinearLayoutManager.HORIZONTAL*//*)
        recyclerView.addItemDecoration(divider);*/

        MaterialDividerItemDecoration divider = new MaterialDividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        divider.setDividerInsetStart(16);
        divider.setDividerInsetEnd(16);
        divider.setLastItemDecorated(false);
        divider.setDividerThickness(12);
        divider.setDividerColorResource(this, R.color.blue);
        rvContacts.addItemDecoration(divider);
        rvContacts.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        });
   /*     rvContacts.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recycler, MotionEvent event) {
                Log.e("onInterceptTouchEvent", "onInterceptTouchEvent");
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recycler, MotionEvent event) {
                Log.e("onTouchEvent", "onTouchEvent");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Log.e("onRequestDisallowInterceptTouchEvent", "onRequestDisallowInterceptTouchEvent");
            }
        });*/

/*        // Настройка менеджера макета для элементов с ориентацией
        // Также поддерживает `LinearLayoutManager.HORIZONTAL`
        LinearLayoutManager layoutContactsManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);
        // При необходимости настройте позицию, на которую вы хотите по умолчанию прокрутить
        layoutContactsManager.scrollToPosition(0);
        // Прикрепите менеджер макета к RecyclerView
        rvContacts.setLayoutManager(layoutContactsManager);*/

/*        // Первый параметр - количество столбцов,
        // второй параметр - ориентация, например, Вертикальная или Горизонтальная
        // GridLayoutManager работает аналогично
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // Прикрепите менеджер макета к RecyclerView
        rvContacts.setLayoutManager(gridLayoutManager);*/


        //rvContacts.setItemAnimator(new DefaultItemAnimator());
        //rvContacts.setItemAnimator(new CustomItemAnimator());
    }
}
