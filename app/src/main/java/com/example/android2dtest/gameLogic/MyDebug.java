package com.example.android2dtest.gameLogic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.android2dtest.gameLogic.myPhysics.Circle;
import com.example.android2dtest.gameLogic.myPhysics.Collider;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;
import com.example.android2dtest.gameLogic.myPhysics.Shape;

public final class MyDebug {

    public static void log(String massage){
        Log.i("debug", massage);
    }


}
