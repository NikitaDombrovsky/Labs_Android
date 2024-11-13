package com.example.labs_basic_painting_with_views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimplePaintingDrawingView extends View {
    // Настройка цвета по умолчанию
    private final int paintColor = Color.BLACK;
    // Определения объекта класса Paint
    private Paint drawPaint;
    // TODO Список для хранения всех точек
    private List<Point> circlePoints;

    public SimplePaintingDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        // TODO Инициализация массива
        circlePoints = new ArrayList<Point>();
    }

    // Перегруженный метод onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Цикл который перересовывает на всех координатах точки
        for (Point p : circlePoints) {
            canvas.drawCircle(p.x, p.y, 5, drawPaint);
        }
    }

    // Перегруженный метод onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Определяет координаты места нажатия
        float touchX = event.getX();
        float touchY = event.getY();
        circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
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
        // TODO Изменили на fill
        drawPaint.setStyle(Paint.Style.FILL);
    }
}