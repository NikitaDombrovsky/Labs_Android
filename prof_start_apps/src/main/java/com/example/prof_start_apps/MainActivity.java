package com.example.prof_start_apps;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView3, textView2, textView4, textView51, textView52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.changeTxt1);
        textView3 = findViewById(R.id.changeTxt3);
        textView2 = findViewById(R.id.changeTxt2);
        textView4 = findViewById(R.id.changeTxt4);
        textView51 = findViewById(R.id.changeTxt51);
        textView52 = findViewById(R.id.changeTxt52);

        form4();
        form5();


        // textView.setTextIsSelectable(true);

        //  textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

        // TODO Сказать про листание
/*        textView.setText("1--------------- \n" +
                "2--------------- \n" +
                "3--------------- \n" +
                "4--------------- \n" );
        textView.setMaxLines(1);
*/

/*
        InputFilter.LengthFilter maxLengthFilter = new InputFilter.LengthFilter(100);
        InputFilter[] origin = textView2.getFilters();
        InputFilter[] newFilters;
        if (origin != null && origin.length > 0) {
            newFilters = new InputFilter[origin.length + 1];
            System.arraycopy(origin, 0, newFilters, 0, origin.length);
            newFilters[origin.length] = maxLengthFilter;
        } else {
            newFilters = new InputFilter[]{maxLengthFilter};
        }
        textView2.setFilters(newFilters);*/


        // Первое значение - шрифт
        // textView.setTypeface(Typeface.MONOSPACE);
        // Значение в float
        // textView.setLetterSpacing(2.2F);
    }

    private void form() {
        // Создается новый объект в куче (heap),
        // даже если такая строка есть в пуле
        String str1 = new String("Hello Android");
        // Если такая строка уже есть в куче,
        // возвращается ссылка на существующий объект
        String str2 = "Hello Android";

        // Сравнение строк
        String a = "hello";
        String b = "hello";
        String c = new String("hello");

        if (a == b) {
            Log.i("a == b", "true");
        } else {
            Log.e("a == b", "false");
        }
        if (a == c) {
            Log.i("a == c", "true");
        } else {
            Log.e("a == c", "false");
        }
        if (a.equals(b)) {
            Log.i("a.equals(b)", "true");
        } else {
            Log.e("a.equals(b)", "false");
        }
        if (a.equals(c)) {
            Log.i("a.equals(c)", "true");
        } else {
            Log.e("a.equals(c)", "false");
        }

        // Конкатенация строк
        String result;

        result = "test" + 1 + "|" + 2 + "test";
        Log.e("Конкатенация-1", result);
        String firstName = "Nikita";
        String secondName = "Dombrovskiy";
        result = firstName + secondName;
        Log.e("Конкатенация-2", result);
        result = firstName + " | " + secondName;
        Log.e("Конкатенация-3", result);


        //Форматирование строк
        String form2 = getString(R.string.app_name);
        Log.e("Form", form2);
        String result2 = String.format("Value: %d", 42);
        Log.e("Form", result2);


    }

    private void form5() {
        SpannableString spannable = new SpannableString("Повседневная практика показывает, " +
                "что перспективное планирование позволяет выполнить важные задания по разработке " +
                "существующих финансовых и административных условий.");

        // Жирный текст (0-5 символы)
        spannable.setSpan(
                new StyleSpan(Typeface.BOLD),
                0, 5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Курсив (7-14 символы)
        spannable.setSpan(
                new StyleSpan(Typeface.ITALIC),
                7, 14,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Красный цвет (27-37 символы)
        spannable.setSpan(
                new ForegroundColorSpan(Color.RED),
                27, 37,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        // Подчеркивание (39-50 символы)
        spannable.setSpan(
                new UnderlineSpan(),
                39, 50,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Зачеркивание (52-64 символы)
        spannable.setSpan(
                new StrikethroughSpan(),
                52, 64,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        textView4.setText(spannable);
    }

    private void form4() {
        String html = "<b>Bold</b> and <i>italic</i>";
        Spanned text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        textView51.setText(text);
        Log.e("HTML", String.valueOf(text));
        String htmlWithLink = "Посетите <a href='https://www.oat.ru/'>наш сайт</a> для подробностей.";
        Spanned linkText = Html.fromHtml(htmlWithLink, Html.FROM_HTML_MODE_LEGACY);

        textView52.setText(linkText);
        textView52.setMovementMethod(LinkMovementMethod.getInstance()); // обязательно для ссылок
    }

    private void form3() {
        String str = "Hello Android";
        // Длина строки
        int length = str.length(); // 13
        // Получение символа
        char ch = str.charAt(1); // 'e'
        // Подстрока
        String sub1 = str.substring(6); // "Android"
        String sub2 = str.substring(0, 5); // "Hello"
        // Поиск
        int index = str.indexOf("And"); // 6
        boolean contains = str.contains("Hello"); // true
        // Замена
        String replaced = str.replace("Hello", "Hi"); // "Hi Android"
        // Разделение
        String[] parts = str.split(" "); // ["Hello", "Android"]
        // Регистр
        String upper = str.toUpperCase(); // "HELLO ANDROID"
        String lower = str.toLowerCase(); // "hello android"
        // Удаление пробелов
        String withSpaces = "  text  ";
        String trimmed = withSpaces.trim(); // "text"
    }

    //android:ellipsize	Use this attribute when you want text to be ellipsized if it is longer than the Textview width.
    private void form2() {
        // Получение текста
        String text = textView.getText().toString();
        // Заменяет текст
        textView.setText("Hello");
        // Динамически прибавляет текст
        for (int i = 0; i < 5; i++) {
            textView.append(text);
        }

    }

    private void textChangeColor() {
        // Использование предопределенных цветов
//        textView.setTextColor(Color.CYAN);
//        textView.setBackgroundColor(Color.BLACK);
//        // Использование HEX-кода цвета
//        textView.setTextColor(Color.parseColor("#00FF00")); // Красный
//        textView.setBackgroundColor(Color.parseColor("#FF0000")); // Зеленый
        // Взятие из ресурсов (устаревший)
        textView.setTextColor(getResources().getColor(R.color.newColorSuperMegaCool));
        textView.setBackgroundColor(getResources().getColor(R.color.newColorSuperMega));
        // Взятие из ресурсов (рекомендуемый)
        textView3.setTextColor(ContextCompat.getColor(this, R.color.newColorSuperMega));
        textView3.setBackgroundColor(ContextCompat.getColor(this, R.color.newColorSuperMegaCool));
    }

    private void textChange12() {
        textView.setText("Привет 100sp");
        // Равно 100sp
        textView.setTextSize(100);


        // Первое значение - шрифт
        // Второе значение - тип начертания текста
        // textView.setTypeface(null, Typeface.BOLD_ITALIC);
    }

    private void Spannable2() {
/*        TextView textView = findViewById(R.id.myTextView);
        String htmlText = "Посетите наш <a href='https://example.com'>сайт</a> или напишите на <a href='mailto:info@example.com'>email</a>.";
        textView.setText(Html.fromHtml(htmlText));
        textView.setMovementMethod(LinkMovementMethod.getInstance());

*//*        2. Использование SpannableString
        java
                Copy*//*

        TextView textView = findViewById(R.id.myTextView);
        SpannableString spannable = new SpannableString("Нажмите здесь для перехода");
        spannable.setSpan(new URLSpan("https://example.com"), 10, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

*//*        3. Кастомная обработка кликов
                java
        Copy*//*

        TextView textView = findViewById(R.id.myTextView);
        textView.setText("Нажмите здесь для действия");
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnClickListener(v -> {
            // Выполнить действие при клике
            Toast.makeText(this, "Ссылка нажата", Toast.LENGTH_SHORT).show();
            // Или открыть URL:
            // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://example.com"));
            // startActivity(browserIntent);
        });*/
    }

    private void Spannable() {
/*        SpannableString spannable = new SpannableString("Красный и Зеленый");
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);*/
    }
}