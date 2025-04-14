package com.example.zuckerman;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log log = new Log((TextView) findViewById(R.id.log_id));
        log.out("Запуск приложения");
        EditText rangeStart = (EditText) findViewById(R.id.rande_start_id);
        EditText rangeFinish = (EditText) findViewById(R.id.rande_finish_id);
        Button searchButton =  findViewById(R.id.search_button_id);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = 0;
                int finish = 0;
                try{
                    start = Integer.parseInt(rangeStart.getText().toString());
                    finish = Integer.parseInt(rangeFinish.getText().toString());

                    if (finish - start <=0){
                        throw new Exception("");
                    }
                } catch (Exception e){
                    log.out("Ошибка в диапазоне поиска");
                    return;
                }
                log.out(
                        String.format(
                                "Диапазон поиска от %d до %d", start, finish)
                        );
                log.out("Поиск запущен");
                for (int i = start; i <= finish ; ++i) {
                    if (Zuckerman.isVerify(i)){
                        log.out(i);
                    }
                }
                log.out("Поиск завершен");

            }
        });
        Button clearButton = findViewById(R.id.clear_button_id);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.clearView();
            }
        });

    }
}


