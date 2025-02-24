package com.example.android2dtest.main;

import android.graphics.Color;

public class MyUtils {
    public static float distance(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
    public static Color rgbToFloatRgb(int r, int g, int b){
        return Color.valueOf((float) r / 255, (float) g / 255, (float) b / 255);
    }
}
