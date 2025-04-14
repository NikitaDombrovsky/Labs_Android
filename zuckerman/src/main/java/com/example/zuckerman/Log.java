package com.example.zuckerman;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {
    private TextView textView;
    public Log(TextView textView){
        this.textView = textView;
    }
    public void out(String msg){
        this.textView.append(String.format("[%s] %s\n", getDateTime(), msg));
       // this.textView.append(String.format("%s | %s\n", getDateTime(), msg));
        //<b>Bolded text</b>, <i>italic text</i>, even <u>underlined</u>!

    }
    public void out(int msg){
        this.textView.append(String.format("[%s] %s\n", getDateTime(), msg));
        //this.textView.append(String.format("%s | %s\n", getDateTime(), msg));
    }
    public void clearView(){
        this.textView.setText("");
    }
    private String getDateTime(){
        DateFormat df = new SimpleDateFormat("MM.dd, hh:mm:ss.SSS");
        return df.format(Calendar.getInstance().getTime());
    }
}
