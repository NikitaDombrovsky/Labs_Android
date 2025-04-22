package com.example.network_get_post_method.POST;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// TODO STATIC?
public class Business {
    private String id;
    private String name;
    private String phone;
    private String imageUrl;

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
    // Десериализация JSON-объекта бизнеса в объект модели
    public static Business fromJson(JSONObject jsonObject) {
        Business b = new Business();
        // Десериализация JSON в поля объекта
        try {
            b.id = jsonObject.getString("id");
            b.name = jsonObject.getString("name");
            b.phone = jsonObject.getString("display_phone");
            b.imageUrl = jsonObject.getString("image_url");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Возвращаем новый объект
        return b;
    }
    // Десериализация массива JSON-результатов в объекты модели бизнеса
    public static ArrayList<Business> fromJson(JSONArray jsonArray) {
        JSONObject businessJson;
        ArrayList<Business> businesses = new ArrayList<Business>(jsonArray.length());
        // Обработка каждого результата в JSON-массиве, декодирование и преобразование в объект Business
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                businessJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Business business = Business.fromJson(businessJson);
            if (business != null) {
                businesses.add(business);
            }
        }

        return businesses;
    }
}