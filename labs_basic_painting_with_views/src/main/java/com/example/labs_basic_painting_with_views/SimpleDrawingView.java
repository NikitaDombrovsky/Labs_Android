package com.example.labs_basic_painting_with_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/*public class SimpleDrawingView extends View {
    // констуктор который принимает контекст и коллекцию атрибутов
    public SimpleDrawingView(Context context, AttributeSet attrs) {
        // Метод - конструктор, который "наращивает" View
        super(context, attrs);
    }
}*/

public class SimpleDrawingView extends View {
    // Настройка цвета по умолчанию
    private final int paintColor = Color.BLACK;
    // Определения объекта класса Paint
    private Paint drawPaint;
    // Констуктор который принимает контекст и коллекцию атрибутов
    public SimpleDrawingView(Context context, AttributeSet attrs) {
        // Метод - конструктор, который "наращивает" View
        super(context, attrs);
        // Вызов методов
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    // Метод определющий настройки кисти и цвета для рисования
    private void setupPaint() {
        // Присваивание объекта
        drawPaint = new Paint();
        // Настройки кисти
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    // Перегруженный метод onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        // Настройка формы фигуры
        canvas.drawCircle(150, 150, 50, drawPaint);
        // Настройка цвета
        drawPaint.setColor(Color.GREEN);
        canvas.drawCircle(300, 300, 70, drawPaint);
        drawPaint.setColor(Color.BLUE);
        canvas.drawCircle(100, 450, 50, drawPaint);
    }
}
