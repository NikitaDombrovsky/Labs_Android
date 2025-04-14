package com.example.sqlitedatabasemodule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

interface DatabaseHelper {
    void insertData(String tableName, ContentValues values);

    ArrayList<Object> getAllData(String tableName);

}

public class DatabaseHelper_ extends SQLiteOpenHelper implements DatabaseHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    // TODO Названье
    private static final String DATABASE_NAME = "postsDatabase";
    // below int is our database version
    private static final int DATABASE_VERSION = 1;

/*    // Имена таблиц
    private static final String TABLE_POSTS = "posts";

    // Столбцы таблицы постов
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_TITLE = "title";
    private static final String KEY_POST_TEXT = "text";*/


    // TODO Singleton
    private static DatabaseHelper_ sInstance;

    public static synchronized DatabaseHelper_ getInstance(Context context) {
        // Используйте контекст приложения, чтобы избежать случайной утечки контекста Activity.
        if (sInstance == null) {
            sInstance = new DatabaseHelper_(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Конструктор должен быть приватным, чтобы предотвратить прямое создание экземпляра.
     * Вместо этого используйте статический метод "getInstance()".
     */
    private DatabaseHelper_(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*    // creating a constructor for our database handler.
        public PostsDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }*/
    // Вызывается при настройке соединения с базой данных.
    // Настройте параметры базы данных, такие как поддержка внешних ключей, ведение журнала и т.д.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
/*    // Вызывается при создании базы данных в ПЕРВЫЙ раз.
    // Если база данных уже существует на диске с тем же именем DATABASE_NAME, этот метод НЕ будет вызван.
    @Override
    public void onCreate(SQLiteDatabase db) {
        */

    /**
     * on below line we are creating.
     * an sqlite query and we are setting our column names along with their data types.
     *//*
        String query = "CREATE TABLE " + TABLE_POSTS + "("
                + KEY_POST_ID + " INTEGER PRIMARY KEY," // Определяем первичный ключ
                + KEY_POST_TITLE + " TEXT,"
                + KEY_POST_TEXT + " TEXT"
                + ")";
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }*/
    // Вызывается при создании базы данных в ПЕРВЫЙ раз.
    // Если база данных уже существует на диске с тем же именем DATABASE_NAME, этот метод НЕ будет вызван.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(Post.CREATE_TABLE_Post);

        // TODO Не знаю что это
        //db.execSQL("PRAGMA foreign_keys=ON");
    }

    /**
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    // Вызывается при необходимости обновления базы данных.
    // Этот метод будет вызван только в том случае, если база данных уже существует на диске с тем же именем DATABASE_NAME,
    // но версия DATABASE_VERSION отличается от версии базы данных, существующей на диске.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Самая простая реализация — удалить все старые таблицы и создать их заново
            db.execSQL("DROP TABLE IF EXISTS " + Post.TABLE_NAME_POSTS);
            onCreate(db);
        }
    }
    /**
     * TODO CRUD METHODS
     */
    /**
     * @param tableName
     * @param values
     */
    // TODO return bool
    public void insertData(
            String tableName,
            ContentValues values) {
        // Создаем и/или открываем базу данных для записи
        SQLiteDatabase db = getWritableDatabase();
        // Хорошей практикой является оборачивание вставки в транзакцию. Это улучшает производительность и обеспечивает
        // согласованность базы данных.
        db.beginTransaction();
        try {
            // after adding all values we are passing
            // content values to our table.
            db.insertOrThrow(tableName, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("InsertData", "Ошибка при попытке вставить данные в таблицу: " + tableName, e);
        } finally {
            db.endTransaction();
        }
    }


    /*    public ArrayList<?> SelectAll(Class<?> type, Context ctx, String whereClause) {

            DatabaseHelper db = new DatabaseHelper(ctx);
            db.OpenDB();

            ArrayList<?> returnList = new ArrayList<>();
            try {
                return db.SelectAll(this.getClass(), whereClause);
            } catch (Exception ex) {
                return null;
            }
        }*/
//return a generic field value from cursor
    private Object GetCursorFieldValue(Cursor cursor, int i) {
        switch (cursor.getType(i)) {
            /*  FIELD_TYPE_NULL
                FIELD_TYPE_INTEGER
                FIELD_TYPE_FLOAT
                FIELD_TYPE_STRING
                FIELD_TYPE_BLOB  */
            case 0:
                return null;
            case 1:
                return cursor.getInt(i);
            case 2:
                return cursor.getFloat(i);
            case 3:
                return cursor.getString(i);
            case 4:
                return cursor.getBlob(i);
            default:
                cursor.close();
                return null;
        }
    }
    //we set the value "val" on the field "field". we use this to value an object without having its name
    private Object SetUnknownFieldValue(Object object, String fieldName, Object fieldValue) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object fieldCasted = CastField(field.getType(), fieldValue);
        field.set(object, fieldCasted);
        return object;

    }
    //given a cursor and a class, it returns the object from the cursor
    private Object GetObjectFromCursor(Class<?> tipo, Cursor cursor) throws Exception {
        Field[] fields = tipo.getFields();
        Object o = tipo.newInstance();
        for (int i = 0; i < fields.length; i++) {
            Object fieldValue = GetCursorFieldValue(cursor, i);
            if (fieldValue != null) {
                o = SetUnknownFieldValue(o, cursor.getColumnName(i), fieldValue);
            }
        }
        return o;
    }

    public ArrayList<?> selectAllData(
            Class<?> type,
            String tableName,
            ContentValues values
    ) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + tableName, null);
        ArrayList arrayList = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                do {
                    //Object o = ge

                    Post newPost = new Post(
                            cursor.getString(cursor.getColumnIndexOrThrow("title")),
                            cursor.getString(cursor.getColumnIndexOrThrow("text"))
                           /* cursor.getString(cursor.getColumnIndex("title")),
                            cursor.getString(cursor.getColumnIndex("text"))*/
                    );
                    arrayList.add(newPost);
                } while (cursor.moveToNext());
            }
        }
    }

    public ArrayList<Object> getAllData(
            String tableName,
            Class<?> type

    ) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursor
                = db.rawQuery("SELECT * FROM " + tableName, null);

        // on below line we are creating a new array list.
        ArrayList<Post> arrayList
                = new ArrayList<>();


        try {
            if (cursor.moveToFirst()) {
                do {

                    Post newPost = new Post(
                            cursor.getString(cursor.getColumnIndexOrThrow("title")),
                            cursor.getString(cursor.getColumnIndexOrThrow("text"))
                           /* cursor.getString(cursor.getColumnIndex("title")),
                            cursor.getString(cursor.getColumnIndex("text"))*/
                    );
                    arrayList.add(newPost);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("getAllData", "Ошибка при попытке получить посты из базы данных");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return arrayList;
    }


    // TODO СТАРОЕ


    public void insertData_(String tableName, ContentValues values) {
        // Создаем и/или открываем базу данных для записи
        SQLiteDatabase db = getWritableDatabase();
        try {
            // after adding all values we are passing
            // content values to our table.
            db.insert(tableName, null, values);
        } catch (Exception e) {
            Log.d("InsertData", "Ошибка при попытке вставить данные в таблицу: " + tableName, e);
        } finally {
            // at last we are closing our
            // database after adding database.
            db.close();
        }
    }
}
