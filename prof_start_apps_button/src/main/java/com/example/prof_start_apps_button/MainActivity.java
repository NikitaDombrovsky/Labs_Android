package com.example.prof_start_apps_button;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //openGallery();

        int s = 0;
        Intents();
        Button btn = findViewById(R.id.btn);
        MaterialButton materialButton =
                findViewById(R.id.materialButton);
        TextView textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int b = s;
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn1 = (Button) v;
                btn1.setText("sfsfsds");
            }
        };
        btn.setOnClickListener(onClickListener);
        materialButton.setOnClickListener(onClickListener);



       // СhangeColor(btn, materialButton);


        // Обрабатывает клики по View (кнопкам, изображениям и др.)
/*        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setBackgroundColor(Color.RED);
                textView.setText("Button была нажата");
            }
        });*/
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                button.setBackgroundColor(Color.RED);
                String textButton = button.getText().toString();
                textView.setText( ""+ textButton +" была нажата");
            }
        };
        btn.setOnClickListener(listener);
        materialButton.setOnClickListener(listener);
/*        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setBackgroundColor(Color.RED);


            }
        });*/

        // OnLongClickListener
        // Обрабатывает долгое нажатие на View
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setText("Долго как-то");
                textView.setTextColor(Color.RED);
                return true; // возвращаем true, если событие обработано
            }
        });
        materialButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setText("Снова долго");
                textView.setTextColor(Color.BLACK);
                return false;
            }
        });



        // OnTouchListener
        // Обрабатывает касания экрана с детальной информацией о движении
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // Начало касания
                    case MotionEvent.ACTION_DOWN:
                        // Уменьшает кнопку при нажатии
                        v.animate().scaleX(0.65f).scaleY(0.65f).setDuration(100).start();
                        break;
                    // Движение пальца
                    case MotionEvent.ACTION_MOVE:
                        float x = event.getX();
                        Log.e("AXISX", ""+x);
                        if (x>0)
                            v.animate().scaleX(0.30f).scaleY(0.30f).setDuration(50).start();
                        else
                            v.animate().scaleX(0.80f).scaleY(0.80f).setDuration(100).start();
                        break;
                    // Окончание касания
                    case MotionEvent.ACTION_UP:
                        // Возвращает кнопку в исходное состояние
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                        break;
                }
                return false; // возвращаем false, чтобы другие слушатели тоже получили событие
            }
        };
        materialButton.setOnTouchListener(onTouchListener);
        btn.setOnTouchListener(onTouchListener);

/*        materialButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                        // Начало касания
                    case MotionEvent.ACTION_DOWN:
                        // Уменьшает кнопку при нажатии
                        v.animate().scaleX(0.65f).scaleY(0.65f).setDuration(100).start();
                        break;
                        // Движение пальца
                    case MotionEvent.ACTION_MOVE:
                        break;
                        // Окончание касания
                    case MotionEvent.ACTION_UP:
                        // Возвращает кнопку в исходное состояние
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                        break;
                }
                return false;
            }
        });
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Уменьшаем кнопку при нажатии
                        v.animate().scaleX(0.65f).scaleY(0.65f).setDuration(100).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Возвращаем кнопку в исходное состояние
                        v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                        break;
                }
                return false;
            }
        });*/



    }


    private void Intents(){

        // Отправить SMS
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto:+79123456789"));
        smsIntent.putExtra("sms_body", "Привет! Как дела?");
        startActivity(smsIntent);


        // Открыть календарь и добавить событие
        // Требуется зайти в аккаунт Google на устройстве
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT);
        calendarIntent.setData(CalendarContract.Events.CONTENT_URI);
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Встреча");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Офис");
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis());
        startActivity(calendarIntent);

/*        ActivityResultLauncher<Intent> startPhoto =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult o) {
                                if (o.getResultCode() == Activity.RESULT_OK){
                                    Intent intent = o.getData();
                                 //   Drawable drawable = intent

                                }
                               // imageView.setBackground(o);
                            }
                        });
        startPhoto.launch(pickPhotoIntent);*/


  /*      Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        searchIntent.putExtra(SearchManager.QUERY, "Android");
        startActivity(searchIntent);*/



/*// Поиск приложений по ключевому слову
        Intent searchPlayStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=telegram"));
        startActivity(searchPlayStoreIntent);*/
/*        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 400);
        }*/
    }

    private void СhangeColor(Button btn, MaterialButton materialButton) {
        // Изменение цвета фона
        btn.setBackgroundColor(Color.RED); // Из стандартных цветов
        btn.setBackgroundColor(Color.parseColor("#FF5722")); // Из HEX-кода
        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.black)); // Из ресурсов
        // Изменение текста кнопки
        btn.setText("Новый текст");
        btn.setTextColor(Color.WHITE); // Цвет текста
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // Размер текста
        // Изменение состояния кнопки программно
/*        btn.setEnabled(false); // Деактивация кнопки
        btn.setClickable(false); // Сделать некликабельной
        btn.setVisibility(View.GONE); // Скрыть кнопку*/

        // Установка drawable в качестве фона
        //btn.setBackgroundResource(R.drawable.ic_launcher_background);


        // Рекомендуется сначала сбросить базовые цвета
        materialButton.setBackgroundColor(Color.TRANSPARENT);
        materialButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

        // Любой R.drawable. который вам доступен
        materialButton.setIcon(
                ContextCompat.getDrawable(this, R.drawable.baseline_error_24)
        );
        materialButton.setIconTint(ColorStateList.valueOf(Color.WHITE));

        materialButton.setCornerRadius(16);
        //
        materialButton.setRippleColor(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.redRipple))
        );

        // materialButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));

        View.OnClickListener listener = v -> {
            // Что-то очень крутое
        };
        btn.setOnClickListener(v -> {
            // Тоже что-то очень крутое
        });
        btn.setOnClickListener(listener);
    }
}
