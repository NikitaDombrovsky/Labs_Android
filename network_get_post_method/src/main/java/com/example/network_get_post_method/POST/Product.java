package com.example.network_get_post_method.POST;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product {
    private int id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

/*    public Product(int id, String title, String price, String description, String category, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }*/

    public static Product fromJSON(JSONObject jsonObject){
        Product product = new Product();
        try {
            product.id = jsonObject.getInt("id");
            product.title = jsonObject.getString("title");
            product.price = jsonObject.getDouble("price");
            product.description = jsonObject.getString("description");
            product.category = jsonObject.getString("category");
            product.image = jsonObject.getString("image");

        } catch (JSONException e){
            Log.e("ERROR:Product.fromJSON", e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
        return product;
    }

    public static JSONObject toJSON(int id, String title, Double price,
                                    String description, String category, String image) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("title", title);
            jsonObject.put("price", price);
            jsonObject.put("description", description);
            jsonObject.put("category", category);
            jsonObject.put("image", image);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public static ArrayList<Product> fromJson(JSONArray jsonArray){
        JSONObject jsonObject;
        ArrayList<Product> productArrayList = new ArrayList<>(jsonArray.length());

        for (int i=0; i< jsonArray.length(); i++){
            try{
                jsonObject = jsonArray.getJSONObject(i);
            } catch (Exception e){
                e.printStackTrace();
                continue;
            }
            Product product = Product.fromJSON(jsonObject);
            if (product != null){
                productArrayList.add(product);
            }
        }
        return productArrayList;
    }


/*    public String toFormUrlEncoded() {
        try {
            StringBuilder formData = new StringBuilder();

            // Добавляем поля, исключая id
            addParam(formData, "title", title);
            //addParam(formData, "price", price);
            addParam(formData, "description", description);
            addParam(formData, "category", category);
            addParam(formData, "image", image);

            // Удаляем последний символ '&' если он есть
            if (formData.length() > 0) {
                formData.deleteCharAt(formData.length() - 1);
            }

            return formData.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode form data", e);
        }
    }*/
/*    private void addParam(StringBuilder builder, String name, String value)
            throws UnsupportedEncodingException {
        if (value != null) {
            builder.append(URLEncoder.encode(name, "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(value, "UTF-8"))
                    .append("&");
        }
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
