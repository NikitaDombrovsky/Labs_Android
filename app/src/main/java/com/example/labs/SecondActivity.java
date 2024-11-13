package com.example.labs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        TextView textView = new TextView(this);
        textView.setTextSize(26);
        textView.setPadding(20, 20, 20, 20);



/*        if(arguments!=null){
            String name = arguments.get("name").toString();
            String group = arguments.getString("group");
            int score = arguments.getInt("score");
            textView.setText(name + " | " + group +
                    "| " + score);
        }*/
        Bundle arguments = getIntent().getExtras();
        Student student;
        if(arguments!=null){
            student = (Student) arguments.getSerializable(Student.class.getSimpleName());

            textView.setText(student.getName() + " |  " + student.getGroup() +
                    " | " + String.valueOf(student.getScore()));
        }

        setContentView(textView);

/*
        Bundle arguments = getIntent().getExtras();
        String message = arguments.get("message").toString();*/


    }
}
