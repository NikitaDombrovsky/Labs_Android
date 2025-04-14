package com.example.sqldatabesesecondmodule;

import android.content.ContentValues;

public class Post {
    // Имя таблицы
    public static final String TABLE_NAME_POSTS = "posts";
    // Столбцы таблицы записей
    public static final String KEY_POST_ID = "id";
    public static final String KEY_POST_TITLE = "title";
    public static final String KEY_POST_TEXT = "text";

    /**
     * Создает SQL-запрос для создания таблицы
     * и задает имена столбцов вместе с их типами данных.
     */
    public static final String CREATE_TABLE_Post =
            "CREATE TABLE " + TABLE_NAME_POSTS + "("
                    + KEY_POST_ID + " INTEGER PRIMARY KEY," // Первичный ключ
                    + KEY_POST_TITLE + " TEXT," // Столбец для заголовка
                    + KEY_POST_TEXT + " TEXT" // Столбец для текста
                    + ")";

    /**
     * SQL-запрос для выбора всех записей из таблицы
     */
    public static final String SELECT_POSTS_QUERY =
            "SELECT * FROM " +
                    TABLE_NAME_POSTS;
    /**
     * SQL-запрос для выбора одной записи из таблицы
     */
    public static final String SELECT_POST_BY_ID_QUERY =
            "SELECT * FROM " +
                    Post.TABLE_NAME_POSTS +
                    " WHERE " + Post.KEY_POST_ID + " = ?";
    private String title; // Заголовок записи
    private String text;  // Текст записи

    // Конструктор для создания объекта Post
    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Метод для преобразования объекта Post в объект ContentValues,
     * который используется для вставки данных в базу данных.
     *
     * @param post Объект Post, который нужно преобразовать.
     * @return ContentValues, содержащий данные.
     */
    public static ContentValues toContentValues(Post post) {
        ContentValues values = new ContentValues();
        values.put(KEY_POST_TITLE, post.getTitle());
        values.put(KEY_POST_TEXT, post.getText());
        return values;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}