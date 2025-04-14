package com.example.sqlitedatabasemodule;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Pair;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

interface IDatabaseHelper {
    ArrayList<?> SelectAll(Class<?> tipo, String whereClause);

    int SelectCount(Class<?> type, String whereClause);

    boolean Save(Object object);

    boolean SaveAll(List<?> objects);

    boolean OpenDB();

    boolean CreateTable(Object object);

    void Close();

    Integer SumColumn(Class<?> type, String whereClause, String columnName);

    boolean DeleteAll(Class<?> type);

    Object SelectById(Class<?> type, UUID id);

    boolean Delete(Class<?> type, String whereClause);

    boolean UpdateObject(Object objToUpdate);

}

public class DatabaseHelper implements IDatabaseHelper {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static String DATABASE_FULLPATH = "";
    private static SQLiteDatabase database;
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    //constructor
    public DatabaseHelper(Context context) {
        DATABASE_FULLPATH = context.getFilesDir().getPath() + "/" + DATABASE_NAME;
    }

    //returns all object of the given class
    public ArrayList<?> SelectAll(Class<?> type, String whereClause) {
        if (whereClause == null) {
            whereClause = "";
        }
        String query = "select * from " + type.getSimpleName() + " " + whereClause;
        Cursor cursor = database.rawQuery(query, null);
        ArrayList list = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Object o = GetObjectFromCursor(type, cursor);
                    list.add(o);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    //returns the count of records of given type
    public int SelectCount(Class<?> type, String whereClause) {
        try {
            if (whereClause == null) {
                whereClause = "";
            }
            String query = "select count(*) from " + type.getSimpleName() + " " + whereClause;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        } catch (Exception ex) {
            return -1;
        }
    }

    //save an object
    public boolean Save(Object object) {

        //we build the query for each object
        String insertQuery = "insert into " + object.getClass().getSimpleName() + "(";
        try {
            ArrayList<Pair<String, Object>> name_value = GetFieldNameValue(object);

            String tableNames = "";
            String tableValues = "";
            //for each record we add the values and the field names
            for (Pair<String, Object> pair : name_value) {
                tableNames += pair.first + ",";
                tableValues += "'" + pair.second.toString() + "'" + ",";
            }

            //remove the last comma
            tableNames = tableNames.substring(0, tableNames.length() - 1);
            tableValues = tableValues.substring(0, tableValues.length() - 1);

            //finished adjusting query
            insertQuery += tableNames + ")values(" + tableValues + ");";
            database.execSQL(insertQuery);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //save multiple objects into db
    public boolean SaveAll(List<?> objects) {
        int saved = 0;
        for (Object object : objects) {
            if (Save(object)) {
                saved++;
            } else {
                return false;
            }
        }
        return saved == objects.size();
    }

    //open db
    public boolean OpenDB() {
        try {
            database = SQLiteDatabase.openOrCreateDatabase(DATABASE_FULLPATH, null, null);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //create a table if not exists
    public boolean CreateTable(Object object) {
        try {
            String query = GetCreateQueryFromObject(object);
            database.execSQL(query);

            //we check for each field if it exists, if not it create the field on the database
            String className = object.getClass().getSimpleName();
            try {
                List<Pair<String, Object>> fields = GetFieldNameValue(object);
                for (Pair<String, Object> c : fields) {
                    if (!CheckColumnExistInTable(className, c.first)) {
                        String addColumnSql = "alter table ";
                        addColumnSql += className;
                        addColumnSql += " add ";
                        addColumnSql += c.first;
                        String columnType = GetSQLFieldType(c.first, c.second.getClass().getSimpleName());
                        addColumnSql += " " + columnType;
                        database.execSQL(addColumnSql);
                    }
                }
                return true;
            } catch (Exception ex) {
                return false;
            }
        }catch(Exception ex){
            return false;
        }
    }

    //closes db
    public void Close() {
        database.close();
    }

    //sum a column value for a given table
    public Integer SumColumn(Class<?> type, String whereClause, String columnName) {
        String sql = "select sum(" + columnName + ") as total from " + type.getSimpleName() + " " + whereClause;
        Cursor cursor = database.rawQuery(sql, null);
        int columnIndex = cursor.getColumnIndex("total");
        if (columnIndex == -1) {
            return 0;
        }
        if (cursor.moveToFirst()) {
            int value = cursor.getInt(columnIndex);
            cursor.close();
            return value;
        } else {
            cursor.close();
            return 0;
        }
    }

    //delete all record in a table
    public boolean DeleteAll(Class<?> type)  {
        String sql = "delete from " + type.getSimpleName();
        database.execSQL(sql);
        return true;
    }

    //select a record from id
    public Object SelectById(Class<?> type, UUID id) {

        String query = "select * from " + type.getSimpleName() + " where id='" + id.toString() + "'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            try {
                Object object = GetObjectFromCursor(type, cursor);
                cursor.close();
                return object;
            } catch(Exception ex){
                return null;
            }
        } else {
            return null;
        }
    }

    //delete all records with a given condition
    public boolean Delete(Class<?> type, String whereClause) {
        try {
            String sql = "delete from " + type.getSimpleName() + " " + whereClause;
            database.execSQL(sql);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //update an object from his id
    public boolean UpdateObject(Object objToUpdate) {
        try {
            Field field = objToUpdate.getClass().getField("id");
            int id = (int) field.get(objToUpdate);
            String whereClause = "where id = " + id;

            String sqlQuery = "update " + objToUpdate.getClass().getSimpleName() + " set ";
            ArrayList<Pair<String, Object>> name_value = GetFieldNameValue(objToUpdate);

            //for each field we add name and value
            for (Pair<String, Object> pair : name_value) {
                if (!pair.first.equals("id")) {
                    sqlQuery += pair.first + "=";
                    sqlQuery += "'" + pair.second.toString() + "'" + ",";
                }
            }

            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);

            sqlQuery += " ";
            sqlQuery += whereClause;

            database.execSQL(sqlQuery);
            return true;


        } catch (Exception ex) {
            return false;
        }
    }

    private boolean CheckColumnExistInTable(String tableName, String columnName) {
        Cursor mCursor = null;
        try {
            // Query 1 row
            mCursor = database.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);

            // getColumnIndex() gives us the index (0 to ...) of the column - otherwise we get a -1
            return mCursor.getColumnIndex(columnName) != -1;

        } catch (Exception Exp) {
            return false;
        } finally {
            if (mCursor != null) mCursor.close();
        }
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

    //returns from a given object a fieldName - fieldValue map
    private ArrayList<Pair<String, Object>> GetFieldNameValue(Object object) throws Exception {
        Field[] fields = object.getClass().getFields();
        ArrayList<Pair<String, Object>> pairs = new ArrayList<>();
        for (Field f : fields) {
            Object value = GetUnknownObjectFieldValue(f, object);
            String fieldName = f.getName();

            if (value == null || fieldName.isEmpty()) {
                continue;
            }
            pairs.add(new Pair(fieldName, value));
        }
        return pairs;
    }

    //returns from an object and a field name, the value
    private Object GetUnknownObjectFieldValue(Field field, Object object) throws Exception {
        field.setAccessible(true);
        Object o = field.get(object);

        //we save dates as longs
        Date date = new Date();
        UUID uuid = UUID.randomUUID();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (o != null && Objects.equals(o.getClass(), date.getClass())) {
                return new Date((long) o);
            }
            if (o != null && Objects.equals(o.getClass(), uuid.getClass())) {
                return o.toString();
            }
        } else {
            if (o != null && o.getClass().equals(date.getClass())) {
                return new Date((long) o);
            }
            if (o != null && o.getClass().equals(uuid.getClass())) {
                return o.toString();
            }
        }
        return o;
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

    //we take the field type and the object way to convert the object in the required field
    private Object CastField(Class fieldType, Object fieldValue) throws Exception {
        switch (fieldType.getSimpleName().toLowerCase()) {
            case "boolean":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(fieldValue.getClass().getSimpleName(), "String")) {
                        return Objects.equals(fieldValue, "true");
                    }
                } else {
                    if (fieldValue.getClass().getSimpleName().equals("String")) {
                        return fieldValue.equals("true");
                    }
                }
                break;
            case "double":
                if (fieldValue == null) {
                    return null;
                }
                return (double) Float.parseFloat(fieldValue.toString());
            case "date":
                return new Date((long) fieldValue);
            case "uuid":
                return UUID.fromString((String) fieldValue);
            default:
                return fieldValue;

        }
        return fieldValue;
    }

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

    //prende un oggetto e ne crea la query di creazione tabella
    private String GetCreateQueryFromObject(Object object) throws Exception {
        String fullQuery = "create table if not exists ";
        String objectName = GetTableName(object);
        fullQuery += objectName + " ";
        String properties = GetPropertiesFromObject(object);
        fullQuery += "(" + properties + ");";
        return fullQuery;
    }

    //returns object properties as: (fieldName fieldType, fieldName fieldType,..)
    private String GetPropertiesFromObject(Object object) throws Exception {
        Class<?> tClass = object.getClass();
        Field[] fieldsArray = tClass.getFields();
        ArrayList<Pair<String, String>> fieldMap = GetFields(fieldsArray);//1 field name, 2 field type
        String fields = "";
        for (Pair<String, String> field : fieldMap) {
            fields += field.first + " ";
            fields += GetSQLFieldType(field.first, field.second) + ", ";
        }
        return fields.substring(0, fields.length() - 2);
    }

    //returns from a given type, the sql type required
    private String GetSQLFieldType(String fieldName, String fieldType) throws Exception {

        if (fieldName.toLowerCase().equals("id")) {
            return "TEXT PRIMARY KEY UNIQUE";
        }
        switch (fieldType.toLowerCase()) {
            case "uuid":
                return "TEXT";
            case "string":
                return "TEXT";
            case "int":
                return "INT";
            case "double":
                return "DOUBLE";
            case "boolean":
                return "BOOLEAN";
            case "float":
                return "FLOAT";
            case "integer":
                return "INT";
            case "date":
                return "INT";
            default:
                return "BLOB";
        }
    }

    //returns from an object the table name
    private String GetTableName(Object object) {
        return object.getClass().getSimpleName();
    }

    //returns a map with an object properties as <fieldType-fieldName>
    private ArrayList<Pair<String, String>> GetFields(Field[] fields) {
        ArrayList<Pair<String, String>> pairs = new ArrayList<>();
        for (Field f : fields) {
            if (!f.getName().equals("shadow$_klass_") && !f.getName().equals("shadow$_monitor_") && !f.getName().equals("$change")) {
                pairs.add(new Pair<>(f.getName(), f.getType().getSimpleName()));
            }
        }
        return pairs;
    }
}