package com.example.android2dtest.gameLogic;

import android.util.Log;

public final class MyDebug {

    public static void log(String massage){
        Log.i("debug", massage);
    }
    public static void log(String from,String massage){
        Log.i("debug",from + ": " + massage);
    }


}
