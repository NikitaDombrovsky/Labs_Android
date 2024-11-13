package com.example.labs_basic_painting_with_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingWithPathView extends View {
    // Настройка цвета по умолчанию
    private final int paintColor = Color.BLACK;
    // Определения объекта класса Paint
    private Paint drawPaint;
    // Список для хранения всех точек
    private List<Point> circlePoints;
    // TODO Инициализация Path
    private Path path = new Path();

    public DrawingWithPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        // Инициализация массива
        circlePoints = new ArrayList<Point>();
    }
    // Перегруженный метод onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Отрисовка новой линии
        canvas.drawPath(path, drawPaint);
    }
    // Перегруженный метод onTouchEvent
    public boolean onTouchEvent(MotionEvent event) {
        // Определяет координаты места нажатия
        float pointX = event.getX();
        float pointY = event.getY();
        // TODO Проверить какое именно событие вызвано
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // TODO Начинает рисовать новую точку в Path
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                // TODO Рисует линию между предыдущей точкои и новой
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        // уведомляет элемент о необходимости пересоздаться
        // (заного вызвать onDraw)
        postInvalidate();
        return true;
    }


    // Метод определющий настройки кисти и цвета для рисования
    private void setupPaint() {
        // Присваивание объекта
        drawPaint = new Paint();
        // Настройки кисти
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        // drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        // Изменили на fill
        //drawPaint.setStyle(Paint.Style.FILL);
        // TODO верните stroke
        drawPaint.setStyle(Paint.Style.STROKE);
    }
}
