package com.example.sqlitedatabasemodule;

import android.content.ContentValues;

import java.util.ArrayList;

public class PostRepository {
    private static final String TABLE_POSTS = "posts";

    // Столбцы таблицы постов
    private static final String KEY_POST_TITLE = "title";
    private static final String KEY_POST_TEXT = "text";

    private DatabaseHelper dbHelper;

    public PostRepository(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

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
    public ArrayList<Post> getAllPost(){

    }

}
