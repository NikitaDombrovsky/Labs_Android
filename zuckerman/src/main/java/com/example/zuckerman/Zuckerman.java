package com.example.zuckerman;

public class Zuckerman {
    public static boolean isVerify(int value){
        int[] digits = Numeral.getDigits(value);
        int r = Array.multiplicationItems(digits);

        if (r == 0){
            return false;
        }
        return value % r == 0;
    }
}
