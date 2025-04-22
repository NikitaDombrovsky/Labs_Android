package com.example.parser;

import android.util.Log;

import com.example.parser.Models.Jackson.RootJS;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class JacksonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // TODO +
    // Сериализация Root в JSON
    //@Override
    public JSONObject toObject(RootJS rootJS1Object) {
        try {
            String jsonObject = objectMapper.writeValueAsString(rootJS1Object);
            return new JSONObject(jsonObject);

        } catch (Exception e) {
            Log.e("GsonParse:parseToObject", e.getLocalizedMessage());
        }
        return null;


    }
    public Test test() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            String personJsonStr = "{\"firstname\":\"John\",\"lastname\":\"Doe\"}";
            Test person = mapper.readValue(personJsonStr, Test.class);
            return person;// read from json string

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Десериализация JSON в объект Root
    //@Override
    public RootJS toModel(String string) {
        try {
            return objectMapper.readValue(string, RootJS.class);
        } catch (Exception e) {
            Log.e("JacksonParser:toModel", e.getLocalizedMessage());
        }
        return null;
    }

    // TODO +
    // Сериализация List<Root> в JSONArray
    //@Override
    public JSONArray toArray(List<RootJS> rootJS) {
        try {
            return new JSONArray(objectMapper.writeValueAsString(rootJS));
        } catch (Exception e) {
            Log.e("JacksonParser:toModel", e.getLocalizedMessage());
        }
        return null;
    }

    // Десериализация JSON массива в List<Root>
    //@Override
    public List<RootJS> toListModel(String string) {
        try {
            return objectMapper.readValue(string, new TypeReference<List<RootJS>>() {
            });
        } catch (Exception e) {
            Log.e("JacksonParser:toModel", e.getLocalizedMessage());
        }
        return null;
    }


}

class Test {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;



}