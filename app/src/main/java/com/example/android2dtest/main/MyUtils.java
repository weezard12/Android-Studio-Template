package com.example.android2dtest.main;

import android.graphics.Color;

public class MyUtils {

    public static Color rgbToFloatRgb(int r, int g, int b){
        return Color.valueOf((float) r / 255, (float) g / 255, (float) b / 255);
    }
}
