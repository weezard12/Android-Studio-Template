package com.example.android2dtest.gameLogic;

import android.util.Log;

public final class MyDebug {

    private static final boolean DEBUG_ENABLED = true;
    public static void log(String massage){
        if(DEBUG_ENABLED)
            Log.i("debug", massage);
    }
    public static void log(String from, String massage){
        if(DEBUG_ENABLED)
            Log.i("debug",from + ": " + massage);
    }


}
