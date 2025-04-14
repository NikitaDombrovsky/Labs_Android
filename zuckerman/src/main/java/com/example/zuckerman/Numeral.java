package com.example.zuckerman;

public class Numeral {
    public static int getDigitsCount(int value){
        String buf = Integer.toString(value);
        return buf.length();
    }
    public static int[] getDigits(int value){
        int dc = getDigitsCount(value);
        int[] digits = new int[dc];

        String buf = Integer.toString(value);
        for (int i = 0; i < dc; ++i) {
            digits[i] = Character.getNumericValue(buf.charAt(i));
        }
        return digits;
    }
}
