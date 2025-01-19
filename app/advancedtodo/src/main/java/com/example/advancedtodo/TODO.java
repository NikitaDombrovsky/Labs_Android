package com.example.advancedtodo;

public class TODO {
    private String Title;
    private String Text;

    public TODO(String test1, String test2) {
        this.Title = test1;
        this.Text = test2;
    }

    public String getTitle() {
        return Title;
    }
    // Остальные геттеры / сеттеры
    public void setTitle(String title) {
        this.Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }
    public static String toString(TODO todo)  {
        return todo.getTitle() + "," + todo.getText();
    }
    public static TODO fromString(String str) {
        String[] parts = str.split(",");
        return new TODO(parts[0], parts[1]);
    }


}