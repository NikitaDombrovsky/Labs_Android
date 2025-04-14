package com.example.parser;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parser.Models.Cart;
import com.example.parser.Models.Coordinates;
import com.example.parser.Models.Location;
import com.example.parser.Models.Name;
import com.example.parser.Models.Root;
import com.example.parser.Models.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String testjson1 = "{\n" +
            "  \"message\": \"Здравствуйте Том, ваш заказ готов!\",\n" +
            "  \"phoneNumber\": \"+79123456789\",\n" +
            "  \"status\": \"active\",\n" +
            "  \"name\": {\n" +
            "    \"first\": \"Том\",\n" +
            "    \"middle\": \"Джерриевич\",\n" +
            "    \"last\": \"Смит\"\n" +
            "  },\n" +
            "  \"cart\": [\n" +
            "    {\n" +
            "      \"book_title\": \"Мастер и Маргарита\",\n" +
            "      \"book_genre\": \"Роман\",\n" +
            "      \"book_author\": \"Михаил Булгаков\",\n" +
            "      \"book_price\": 450.50,\n" +
            "      \"book_rating\": 4.8,\n" +
            "      \"page_count\": 384\n" +
            "    },\n" +
            "    {\n" +
            "      \"book_title\": \"1984\",\n" +
            "      \"book_genre\": \"Антиутопия\",\n" +
            "      \"book_author\": \"Джордж Оруэлл\",\n" +
            "      \"book_price\": 380.25,\n" +
            "      \"book_rating\": 4.6,\n" +
            "      \"page_count\": 328\n" +
            "    }\n" +
            "  ],\n" +
            "  \"emails\": [\n" +
            "    \"tom.smith@example.com\",\n" +
            "    \"t.smith@work.com\"\n" +
            "  ],\n" +
            "  \"location\": {\n" +
            "    \"street\": \"ул. Пушкина, д. 10\",\n" +
            "    \"city\": \"Москва\",\n" +
            "    \"country\": \"Россия\",\n" +
            "    \"coordinates\": {\n" +
            "      \"latitude\": 55.7558,\n" +
            "      \"longitude\": 37.6173\n" +
            "    },\n" +
            "    \"delivery_available\": true,\n" +
            "    \"delivery_radius\": 15.5,\n" +
            "    \"delivery_cost\": 199.99\n" +
            "  },\n" +
            "  \"date\": \"2023-05-15\",\n" +
            "  \"is_premium\": true,\n" +
            "  \"total_orders\": 12,\n" +
            "  \"discount\": 0.15,\n" +
            "  \"loyalty_points\": 2450\n" +
            "}";
    private String testJson2 = "[\n" +
            "  {\n" +
            "    \"message\": \"Здравствуйте Том, ваш заказ готов!\",\n" +
            "    \"phoneNumber\": \"+79123456789\",\n" +
            "    \"status\": \"active\",\n" +
            "    \"name\": {\n" +
            "      \"first\": \"Том\",\n" +
            "      \"middle\": \"Джерриевич\",\n" +
            "      \"last\": \"Смит\"\n" +
            "    },\n" +
            "    \"cart\": [\n" +
            "      {\n" +
            "        \"book_title\": \"Мастер и Маргарита\",\n" +
            "        \"book_genre\": \"Роман\",\n" +
            "        \"book_author\": \"Михаил Булгаков\",\n" +
            "        \"book_price\": 450.50,\n" +
            "        \"book_rating\": 4.8,\n" +
            "        \"page_count\": 384\n" +
            "      },\n" +
            "      {\n" +
            "        \"book_title\": \"1984\",\n" +
            "        \"book_genre\": \"Антиутопия\",\n" +
            "        \"book_author\": \"Джордж Оруэлл\",\n" +
            "        \"book_price\": 380.25,\n" +
            "        \"book_rating\": 4.6,\n" +
            "        \"page_count\": 328\n" +
            "      }\n" +
            "    ],\n" +
            "    \"emails\": [\n" +
            "      \"tom.smith@example.com\",\n" +
            "      \"t.smith@work.com\"\n" +
            "    ],\n" +
            "    \"location\": {\n" +
            "      \"street\": \"ул. Пушкина, д. 10\",\n" +
            "      \"city\": \"Москва\",\n" +
            "      \"country\": \"Россия\",\n" +
            "      \"coordinates\": {\n" +
            "        \"latitude\": 55.7558,\n" +
            "        \"longitude\": 37.6173\n" +
            "      },\n" +
            "      \"delivery_available\": true,\n" +
            "      \"delivery_radius\": 15.5,\n" +
            "      \"delivery_cost\": 199.99\n" +
            "    },\n" +
            "    \"date\": \"2023-05-15\",\n" +
            "    \"is_premium\": true,\n" +
            "    \"total_orders\": 12,\n" +
            "    \"discount\": 0.15,\n" +
            "    \"loyalty_points\": 2450\n" +
            "  },\n" +
            "  {\n" +
            "    \"message\": \"Здравствуйте Анна, ваш заказ готов!\",\n" +
            "    \"phoneNumber\": \"+79234567890\",\n" +
            "    \"status\": \"disabled\",\n" +
            "    \"name\": {\n" +
            "      \"first\": \"Анна\",\n" +
            "      \"middle\": \"Петровна\",\n" +
            "      \"last\": \"Иванова\"\n" +
            "    },\n" +
            "    \"cart\": [\n" +
            "      {\n" +
            "        \"book_title\": \"Преступление и наказание\",\n" +
            "        \"book_genre\": \"Роман\",\n" +
            "        \"book_author\": \"Фёдор Достоевский\",\n" +
            "        \"book_price\": 320.75,\n" +
            "        \"book_rating\": 4.7,\n" +
            "        \"page_count\": 592\n" +
            "      },\n" +
            "      {\n" +
            "        \"book_title\": \"Гарри Поттер и философский камень\",\n" +
            "        \"book_genre\": \"Фэнтези\",\n" +
            "        \"book_author\": \"Джоан Роулинг\",\n" +
            "        \"book_price\": 550.00,\n" +
            "        \"book_rating\": 4.9,\n" +
            "        \"page_count\": 432\n" +
            "      }\n" +
            "    ],\n" +
            "    \"emails\": [\n" +
            "      \"anna.ivanova@example.com\",\n" +
            "      \"a.ivanova@work.com\"\n" +
            "    ],\n" +
            "    \"location\": {\n" +
            "      \"street\": \"ул. Ленина, д. 5\",\n" +
            "      \"city\": \"Санкт-Петербург\",\n" +
            "      \"country\": \"Россия\",\n" +
            "      \"coordinates\": {\n" +
            "        \"latitude\": 59.9343,\n" +
            "        \"longitude\": 30.3351\n" +
            "      },\n" +
            "      \"delivery_available\": true,\n" +
            "      \"delivery_radius\": 20.0,\n" +
            "      \"delivery_cost\": 249.50\n" +
            "    },\n" +
            "    \"date\": \"2023-06-22\",\n" +
            "    \"is_premium\": false,\n" +
            "    \"total_orders\": 5,\n" +
            "    \"discount\": 0.05,\n" +
            "    \"loyalty_points\": 750\n" +
            "  },\n" +
            "  {\n" +
            "    \"message\": \"Здравствуйте Иван, ваш заказ готов!\",\n" +
            "    \"phoneNumber\": \"+79345678901\",\n" +
            "    \"status\": \"active\",\n" +
            "    \"name\": {\n" +
            "      \"first\": \"Иван\",\n" +
            "      \"middle\": \"Сергеевич\",\n" +
            "      \"last\": \"Петров\"\n" +
            "    },\n" +
            "    \"cart\": [\n" +
            "      {\n" +
            "        \"book_title\": \"Война и мир\",\n" +
            "        \"book_genre\": \"Роман-эпопея\",\n" +
            "        \"book_author\": \"Лев Толстой\",\n" +
            "        \"book_price\": 680.90,\n" +
            "        \"book_rating\": 4.9,\n" +
            "        \"page_count\": 1274\n" +
            "      },\n" +
            "      {\n" +
            "        \"book_title\": \"Анна Каренина\",\n" +
            "        \"book_genre\": \"Роман\",\n" +
            "        \"book_author\": \"Лев Толстой\",\n" +
            "        \"book_price\": 490.20,\n" +
            "        \"book_rating\": 4.7,\n" +
            "        \"page_count\": 864\n" +
            "      }\n" +
            "    ],\n" +
            "    \"emails\": [\n" +
            "      \"ivan.petrov@example.com\",\n" +
            "      \"i.petrov@work.com\"\n" +
            "    ],\n" +
            "    \"location\": {\n" +
            "      \"street\": \"пр. Мира, д. 15\",\n" +
            "      \"city\": \"Новосибирск\",\n" +
            "      \"country\": \"Россия\",\n" +
            "      \"coordinates\": {\n" +
            "        \"latitude\": 55.0084,\n" +
            "        \"longitude\": 82.9357\n" +
            "      },\n" +
            "      \"delivery_available\": false,\n" +
            "      \"delivery_radius\": 10.0,\n" +
            "      \"delivery_cost\": 149.99\n" +
            "    },\n" +
            "    \"date\": \"2023-07-10\",\n" +
            "    \"is_premium\": true,\n" +
            "    \"total_orders\": 23,\n" +
            "    \"discount\": 0.20,\n" +
            "    \"loyalty_points\": 5870\n" +
            "  }\n" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GSONMethods();

    }
    // TODO GSON
    private void GSONMethods(){
        GsonParser parser = new GsonParser();
        // Чтение JSONObject
        Root rootGSON = parser.parseFromObject(testjson1);
        // Создание JSONObject
        JSONObject jsonObjectGSON = parser.parseToObject(createTestRoot());
        // Чтение JSONArray
        List<Root> rootGSONArray = parser.parseFromArray(testJson2);
        // Создание JSONArray
        JSONArray jsonArrayGSON = parser.parseToArray(createTestRoots());

        Log.e("!", rootGSON.toString());
    }
    private List<Root> createTestRoots(){
        ArrayList<Root> roots = new ArrayList<>();
        roots.add(createTestRoot());
        roots.add(createTestRoot());
        roots.add(createTestRoot());
        return roots;
    }
    private Root createTestRoot(){
        ArrayList<String> email = new ArrayList<String>();
        email.add("test_email1");
        email.add("test_email2");

        ArrayList<Cart> carts = new ArrayList<>();
        carts.add(new Cart(
                "test_btitle1",
                "test_bgenre1",
                "test_bauthor1",
                2.0,
                10.0,
                1
        ));
        carts.add(new Cart(
                "test_btitle2",
                "test_bgenre2",
                "test_bauthor2",
                2.0,
                10.0,
                1200
        ));

        return new Root(
                "test_message",
                "test_phone",
                Status.ACTIVE,
                new Name(
                        "test_fname",
                        "test_mname",
                        "test_lname"
                ),
                carts,
                email,
                new Location("test_street1",
                        "test_city1",
                        "test_contry1",
                        new Coordinates(
                                1.0,
                                0.1
                        ),
                        true,
                        0.0,
                        1.1
                        ),
                new Date("Mon May 15 00:00:00 GMT 2023"),
                true,
                100,
                100.0,
                0
        );
    }
}