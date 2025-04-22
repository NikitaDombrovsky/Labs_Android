package com.example.jsonparser;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSON0();
    }

    private class User {
        private int id;
        private String name;
        private String email;
        private String gender;

        public User(int id, String name, String email, String gender) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    private class Users {
        private int id;
        private String name;
        private String email;
        private String gender;
        private Contact contact;

        public Users(int id, String name, String email, String gender, Contact contact) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.gender = gender;
            this.contact = contact;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }
    }

    private class Contact {
        private String mobile;
        private String home;
        private String office;

        public Contact(String mobile, String home, String office) {
            this.mobile = mobile;
            this.home = home;
            this.office = office;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }
    }

    private void JSON4() {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        String JSON_STRING = "{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"id\": \"1087\",\n" +
                "      \"name\": \"Иван Иванов\",\n" +
                "      \"email\": \"ivan.ivanov@example.com\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"contact\": {\n" +
                "        \"mobile\": \"+7 900 000 00 00\",\n" +
                "        \"home\": \"00 000000\",\n" +
                "        \"office\": \"00 000000\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"1088\",\n" +
                "      \"name\": \"Мария Петрова\",\n" +
                "      \"email\": \"maria.petrova@example.com\",\n" +
                "      \"gender\": \"female\",\n" +
                "      \"contact\": {\n" +
                "        \"mobile\": \"+7 900 111 11 11\",\n" +
                "        \"home\": \"11 111111\",\n" +
                "        \"office\": \"11 111111\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"1089\",\n" +
                "      \"name\": \"Алексей Смирнов\",\n" +
                "      \"email\": \"aleksey.smirnov@example.com\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"contact\": {\n" +
                "        \"mobile\": \"+7 900 222 22 22\",\n" +
                "        \"home\": \"22 222222\",\n" +
                "        \"office\": \"22 222222\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        try {
            // создать JSONObject из JSON-файла
            JSONObject obj = new JSONObject(JSON_STRING);

            // Создать JSONArray по ключю users
            JSONArray users = obj.getJSONArray("users");
            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);

                JSONObject contact = user.getJSONObject("contact");

                usersArrayList.add(new Users(
                        user.getInt("id"),
                        user.getString("name"),
                        user.getString("email"),
                        user.getString("gender"),
                        new Contact(
                                contact.getString("mobile"),
                                contact.getString("home"),
                                contact.getString("office")
                        )
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("!", "1");
    }

    private void JSON3() {
        String JSON_STRING = "[\n" +
                "  {\n" +
                "    \"id\": \"1087\",\n" +
                "    \"name\": \"Иван Иванов\",\n" +
                "    \"email\": \"ivan.ivanov@example.com\",\n" +
                "    \"gender\": \"male\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"1088\",\n" +
                "    \"name\": \"Мария Петрова\",\n" +
                "    \"email\": \"maria.petrova@example.com\",\n" +
                "    \"gender\": \"female\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"1089\",\n" +
                "    \"name\": \"Алексей Смирнов\",\n" +
                "    \"email\": \"aleksey.smirnov@example.com\",\n" +
                "    \"gender\": \"male\"\n" +
                "  }\n" +
                "]";
        String name = "", email = "", gender = "";
        int id = 0;

        ArrayList<User> users = new ArrayList<>();

        try {
            // создать JSONArray из JSON-файла
            JSONArray arr = new JSONArray(JSON_STRING);

            // Цикл с кол-вом итераций равным размеру массива
            for (int i = 0; i < arr.length(); i++) {
                // Создание JSONObject по текущему индексу
                JSONObject user = arr.getJSONObject(i);

                id = user.getInt("id");
                name = user.getString("name");
                email = user.getString("email");
                gender = user.getString("gender");
                Log.e("" + id, "Name:" + name);

                users.add(new User(
                        id,
                        name,
                        email,
                        gender
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void JSON2() {
        String JSON_STRING = "{\n" +
                "  \"locale\": \"ru\",\n" +
                "        \"user\":{\n" +
                "            \"id\": \"1087\",\n" +
                "            \"name\": \"Иван Иванов\",\n" +
                "            \"email\": \"ivan.ivanov@example.com\",\n" +
                "            \"gender\": \"male\"\n" +
                "        }\n" +
                "}\n";
        String name, email, locale;
        TextView textName, textEmail, textLocale;
        textName = (TextView) findViewById(R.id.name);
        textEmail = (TextView) findViewById(R.id.email);
        textLocale = (TextView) findViewById(R.id.locale);

        try {
            // создать JSONObject из JSON-файла
            JSONObject obj = new JSONObject(JSON_STRING);
            // получить по ключу локализацию
            locale = obj.getString("locale");

            // Создать JSONIbject из первого по ключю user
            JSONObject user = obj.getJSONObject("user");
            // получить по ключам имя и эл.почту
            name = user.getString("name");
            email = user.getString("email");

            textName.setText("Name: " + name);
            textEmail.setText("Email: " + email);
            textLocale.setText("Locale: " + locale);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void JSON1() {
        String JSON_STRING = "{\n" +
                "  \"id\": \"1087\",\n" +
                "  \"name\": \"Иван Иванов\",\n" +
                "  \"email\": \"ivan.ivanov@example.com\",\n" +
                "  \"gender\": \"male\"\n" +
                "}";
        String name, email;
        TextView textName, textEmail;
        textName = (TextView) findViewById(R.id.name);
        textEmail = (TextView) findViewById(R.id.email);

        try {
            // создать JSONObject из JSON-файла
            JSONObject obj = new JSONObject(JSON_STRING);
            // получить по ключам имя и эл.почту
            name = obj.getString("name");
            email = obj.getString("email");

            textName.setText("Name: " + name);
            textEmail.setText("Email: " + email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void JSON0() {
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            String json = "[\n" +
                    "  {\n" +
                    "\t\"id\": \"1087\",\n" +
                    "\t\"name\": \"Иван Иванов\",\n" +
                    "\t\"email\": \"ivan.ivanov@example.com\",\n" +
                    "\t\"gender\": \"male\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "\t\"id\": \"1088\",\n" +
                    "\t\"name\": \"Мария Петрова\",\n" +
                    "\t\"email\": \"maria.petrova@example.com\",\n" +
                    "\t\"gender\": \"female\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "\t\"id\": \"1089\",\n" +
                    "\t\"name\": \"Алексей Смирнов\",\n" +
                    "\t\"email\": \"aleksey.smirnov@example.com\",\n" +
                    "\t\"gender\": \"male\"\n" +
                    "  }\n" +
                    "]\n";
            JSONArray jsonArr1 = new JSONArray(json);
            for (int i = 0; i<jsonArr1.length(); i++){
                JSONObject jsonObj1 = jsonArr1.getJSONObject(i);
                //String email = jsonObj1.getString("email");
                userArrayList.add(new User(
                        jsonObj1.getInt("id"),
                        jsonObj1.getString("name"),
                        jsonObj1.getString("email"),
                        jsonObj1.getString("gender")
                ));
              //  Log.e("!", email);
            }


            Log.e("!", "");
        }
        catch (Exception e){

        }
    }
}