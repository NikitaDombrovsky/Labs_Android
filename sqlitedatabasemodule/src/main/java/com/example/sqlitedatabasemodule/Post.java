package com.example.sqlitedatabasemodule;

public class Post {
    // Имена таблиц
    public static final String TABLE_NAME_POSTS = "posts";
    // Столбцы таблицы постов
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_TITLE = "title";
    private static final String KEY_POST_TEXT = "text";

    /**
     * on below line we are creating.
     * an sqlite query and we are setting our column names along with their data types.
     */
    // SQL Create Table String:
    public static final String CREATE_TABLE_Post =
            "CREATE TABLE " + TABLE_NAME_POSTS + "("
                    + KEY_POST_ID + " INTEGER PRIMARY KEY," // Определяем первичный ключ
                    + KEY_POST_TITLE + " TEXT,"
                    + KEY_POST_TEXT + " TEXT"
                    + ")";

    private String title;
    private String text;

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
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


    /*
        public void addPost(Post post) {
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        values.put(KEY_POST_TITLE, post.getTitle());
        values.put(KEY_POST_TEXT, post.getText());

        dbHelper.insertData(TABLE_POSTS, values);

    }
    * */
}
