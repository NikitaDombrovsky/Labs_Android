package com.example.network_okhttp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"PrivateConstructorForUtilityClass", "SystemOut"})
public class RawCollectionsExample {
    static class Event {
        private String name;
        private String source;

        private Event(String name, String source) {
            this.name = name;
            this.source = source;
        }

        @Override
        public String toString() {
            return String.format("(name=%s, source=%s)", name, source);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void run() {
        Gson gson = new Gson();
        Collection collection = new ArrayList();
        collection.add("hello");
        collection.add(5);
        collection.add(new Event("GREETINGS", "guest"));
        String json = gson.toJson(collection);
        System.out.println("Using Gson.toJson() on a raw collection: " + json);
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();
        String message = gson.fromJson(array.get(0), String.class);
        int number = gson.fromJson(array.get(1), int.class);
        Event event = gson.fromJson(array.get(2), Event.class);
        System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", message, number, event);
    }
}