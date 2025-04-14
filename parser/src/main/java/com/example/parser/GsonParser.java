package com.example.parser;

import android.util.Log;

import com.example.parser.Models.Root;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class GsonParser {
    public JSONObject parseToObject(Root root1Object) {
        Gson gson = new Gson();

        try {
            String jsonObject = gson.toJson(root1Object);
            return new JSONObject(jsonObject);

        } catch (Exception e) {
            Log.e("GsonParse:parseToObject", e.getLocalizedMessage());
        }
        return null;

    }
    public Root parseFromObject(String string) {
        Gson gson = new Gson();
        try {

            // Reader - для чтения файла
            // Тип объекта который вы хотите прочитать
            return gson.fromJson(string, Root.class);
        } catch (Exception e) {
            Log.e("GsonParse:parseFromObject", e.getLocalizedMessage());
        }
        return null;

    }


    public JSONArray parseToArray(List<Root> roots) {
        Gson gson = new Gson();
        try {
            String jsonObject = gson.toJson(roots);

            return new JSONArray(jsonObject);
        } catch (Exception e) {
            Log.e("GsonParse:parseToArray", e.getLocalizedMessage());
        }
        return null;

    }
    public List<Root> parseFromArray(String string) {
        Gson gson = new Gson();
        try {

            Type type = new TypeToken<List<Root>>(){}.getType();

            return gson.fromJson(string, type);
        } catch (Exception e) {
            Log.e("GsonParse:parseFromArray", e.getLocalizedMessage());
        }
        return null;

    }
}
/*
 private List<Event> toList_GSON(String result) {
        try {
            Gson gson = new Gson();
            // Это придется просто запомнить
            Type eventListType = new TypeToken<ArrayList<Event>>() {
            }.getType();
            return gson.fromJson(result, eventListType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Event> toList_Mochi(String result) {
        try {
            // Вариант на Mochi
            Moshi moshi = new Moshi.Builder()
                    .build();
            // Тут хотя бы понятно
            Type type = Types.newParameterizedType(List.class, Event.class);
            // Mochi не очень хорошо работает с ArrayList но они с List заменяемы
            JsonAdapter<List<Event>> jsonAdapter = moshi.adapter(type);
            return jsonAdapter.fromJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Event> toList(String result) {
        try {
            List<Event> newEvents = new ArrayList<>();
            // Разбить результат на элементы по ключам
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Event event = new Event();
                event.setTitle(jsonObject.getString("title"));
                event.setDate(jsonObject.getString("date"));
                event.setDescription(jsonObject.getString("description"));
                event.setAuthor(jsonObject.getString("author"));
                newEvents.add(event);
            }
            return newEvents;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Event> toList_Jackson(String result) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Парсинг JSON в список объектов Event
            List<Event> newEvents = objectMapper.readValue(result, new TypeReference<List<Event>>() {
            });
            return newEvents;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
 */