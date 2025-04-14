package com.example.network_okhttp;

public class Junk {
    /*private List<Event> toList_GSON(String result) {
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
    }*/

    /*
    MediaType JSON = MediaType.get("application/json; charset=utf-8");
String jsonRequest = "Some request";
     */
    /*        final RequestBody formBody = new FormBody.Builder()
                .add("username", "test")
                .add("password", "test").build();

        final Request request = new Request.Builder()
                .url(BASE_URL + "/users")
                .post(formBody).build();*/

/*    public String test2GetRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://raw.github.com/square/okhttp/master/README.md")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.e("Code",""+ response.code() + "|" + response.message());
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/
/*
    public void test1(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://raw.github.com/square/okhttp/master/README.md")
                // .url("https://publicobject.com/helloworld.txt")
                .method("GET", body)
                .build();
        Response response = client.newCall(request).execute();
    }
*/

}
