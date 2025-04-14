package com.example.sqldatabesesecondmodule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PostsDatabaseHelper extends SQLiteOpenHelper {
    // Создание константных переменных для базы данных.
    // Переменная для имени базы данных.
    private static final String DATABASE_NAME = "postsDatabase";
    // Переменная для версии базы данных.
    private static final int DATABASE_VERSION = 1;

    // Реализация шаблона Singleton для управления экземпляром базы данных.
    private static PostsDatabaseHelper sInstance;

    /**
     * Метод для получения экземпляра базы данных.
     * Используется контекст приложения, чтобы избежать утечки контекста Activity.
     *
     * @param context Контекст приложения.
     * @return Экземпляр PostsDatabaseHelper.
     */
    public static synchronized PostsDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PostsDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Приватный конструктор для предотвращения прямого создания экземпляра.
     * Используйте статический метод getInstance() для получения экземпляра.
     *
     * @param context Контекст приложения.
     */
    private PostsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Вызывается при настройке соединения с базой данных.
     * Настраивает параметры базы данных, такие как поддержка внешних ключей.
     *
     * @param db Объект базы данных.
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        // Включает поддержку внешних ключей, хотя в задании это не используется.
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * Вызывается при первом создании базы данных.
     * Если база данных с таким именем уже существует, этот метод не будет вызван.
     *
     * @param db Объект базы данных.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Выполняем SQL-запрос для создания таблицы постов.
        db.execSQL(Post.CREATE_TABLE_Post);
    }

    /**
     * Вызывается при необходимости обновления базы данных.
     * Этот метод вызывается, если версия базы данных на диске отличается от текущей версии.
     *
     * @param db         Объект базы данных.
     * @param oldVersion Старая версия базы данных.
     * @param newVersion Новая версия базы данных.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Самая простая реализация — удалить все старые таблицы и создать их заново
            db.execSQL("DROP TABLE IF EXISTS " + Post.TABLE_NAME_POSTS);
            onCreate(db);
        }
    }

    /**
     * Метод для добавления записи в базу данных.
     *
     * @param post Объект Post для добавления.
     */
    public void addPost(Post post) {
        // Открываем базу данных для записи.
        SQLiteDatabase db = getWritableDatabase();

        // Хорошей практикой является оборачивание вставки в транзакцию. Это улучшает производительность и обеспечивает
        // согласованность базы данных.
        db.beginTransaction();
        try {
            // Обратите внимание, что вы не указали первичный ключ. SQLite автоматически увеличивает столбец первичного ключа.
            db.insertOrThrow(Post.TABLE_NAME_POSTS, null,  Post.toContentValues(post));
            db.setTransactionSuccessful(); // Успешно завершаем транзакцию.
        } catch (Exception e) {
            Log.d("AddPost", "Ошибка при попытке добавить пост в базу данных");
        } finally {
            db.endTransaction(); // Завершаем транзакцию.
        }
    }

    /**
     * Метод для добавления поста в базу данных без использования транзакции.
     *
     * @param post Объект Post для добавления.
     */
    public void addPost_NotTransaction(Post post) {
        // Открываем базу данных для записи.
        SQLiteDatabase db = getWritableDatabase();

        // Вставляем запись в таблицу.
        db.insert(Post.TABLE_NAME_POSTS, null, Post.toContentValues(post));

        // Закрываем базу данных после добавления данных.
        db.close();
    }

    /**
     * Метод для получения поста по его ID.
     *
     * @param postId ID поста.
     * @return Объект Post.
     */
    public Post getPostById(int postId) {
        SQLiteDatabase db = getReadableDatabase();
        Post post = null;

        // SQL-запрос для получения поста по ID.
        Cursor cursor = db.rawQuery(Post.SELECT_POST_BY_ID_QUERY, new String[]{String.valueOf(postId)});
        try {
            if (cursor.moveToFirst()) {
                // Создаем объект Post из данных курсора.
                post = new Post(
                        cursor.getString(cursor.getColumnIndexOrThrow(Post.KEY_POST_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Post.KEY_POST_TEXT))
                );
            }
        } catch (Exception e) {
            Log.d("getPostById", "Ошибка при попытке получить пост из базы данных", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close(); // Закрываем курсор.
            }
        }

        return post;
    }

    /**
     * Метод для получения всех записей из базы данных.
     *
     * @return Список объектов Post.
     */
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        // Открываем базу данных для чтения.
        // "getReadableDatabase()" и "getWriteableDatabase()" возвращают один
        // и тот же объект (за исключением случаев нехватки места на диске)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(Post.SELECT_POSTS_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    // Создаем объект Post из данных курсора и добавляем его в список.
                    Post newPost = new Post(
                            cursor.getString(cursor.getColumnIndexOrThrow(Post.KEY_POST_TITLE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(Post.KEY_POST_TEXT))
                    );
                    posts.add(newPost);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("getAllPosts", "Ошибка при попытке получить посты из базы данных");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close(); // Закрывает курсор.
            }
        }
        return posts;
    }

    /**
     * Метод для обновления записи по заголовку.
     *
     * @param post Объект Post для обновления.
     * @return Количество обновленных строк.
     */
    public int updatePostForTitle(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(Post.TABLE_NAME_POSTS, Post.toContentValues(post),
                Post.KEY_POST_TITLE + " = ?",
                new String[]{String.valueOf(post.getTitle())});
    }

    /**
     * Метод для удаления записи по заголовку.
     *
     * @param post Объект Post для удаления.
     */
    public void deletePost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            // Удаляем пост по заголовку.
            db.delete(Post.TABLE_NAME_POSTS,
                    Post.KEY_POST_TITLE + "=?", new String[]{post.getTitle()});
            db.setTransactionSuccessful(); // Успешно завершаем транзакцию.
        } catch (Exception e) {
            Log.d("deletePost", "Ошибка при попытке удалить пост");
        } finally {
            db.endTransaction(); // Завершаем транзакцию.
        }
    }

    /**
     * Метод для удаления всех записей из базы данных.
     */
    public void deleteAllPosts() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Удаляет все записи из таблицы постов.
            db.delete(Post.TABLE_NAME_POSTS, null, null);
            db.setTransactionSuccessful(); // Успешно завершаем транзакцию.
        } catch (Exception e) {
            Log.d("deleteAllPosts", "Ошибка при попытке удалить все посты");
        } finally {
            db.endTransaction(); // Завершаем транзакцию.
        }
    }
}