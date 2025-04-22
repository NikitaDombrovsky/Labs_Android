package com.example.prof_start_apps;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TextActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textView = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView7 = findViewById(R.id.textView7);


        textView.setTextSize(100);

 /*       textView2.setTextColor(Color.DKGRAY);
        textView2.setBackgroundColor(Color.GREEN);*/
        //textView2.setTextColor(Color.parseColor("#FF000000"));
       // textView2.setBackgroundColor(Color.parseColor("#FF000000"));
        textView2.setTextColor(getResources().getColor(R.color.newColorSuperMegaCool));
        textView2.setBackgroundColor(ContextCompat.getColor(this,R.color.newColorSuperMega));

        textView3.setTypeface(Typeface.DEFAULT);

        textView4.setTypeface(null, Typeface.ITALIC);

        textView5.setLetterSpacing(0.2F);

        textView6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textView7.setMaxLines(2);
        form1();
        form2();

    }
    public void form1(){
        String b = textView.getText().toString();
        String a = "Hello 1";
        //String b = "hi";
        String c = new String("Hello 1");

        if (a == b){
            Log.e("!", "1");
        }
        if (b == c){
            Log.e("!", "1");
        }
        if (a.equals(b)){
            Log.e("!", "1");
        }
        if (b.equals(c)){
            Log.e("!", "1");
        }
    }
    public void form2(){
        String b = textView.getText().toString();
        String a = "ffffffff";
        int i = 22;
        //textView.setText("" +a + ""+ 22);
        textView.append("" +a + ""+ 22);
    }
}