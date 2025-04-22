package com.example.heterogenouslistviewmodule;

public class SimpleColor {
    public enum ColorValues {
        RED, BLUE, GREEN;

        public static String equalsColor(ColorValues color1, ColorValues color2) {

            if (color1 == color2) {
                return "Одинаковые цвета";
            } else
                return "Разные цвета";
        }
    }

    public String label;
    public ColorValues color;

    public SimpleColor(String label, ColorValues color) {
        this.label = label;
        this.color = color;
    }
/*    public static String equalsColor(ColorValues color1, ColorValues color2){

        if (color1 == color2){
            return "Одинаковые цвета";
        }
        else
            return "Разные цвета";
    }*/
}