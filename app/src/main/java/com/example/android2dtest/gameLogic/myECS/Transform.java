package com.example.android2dtest.gameLogic.myECS;

import android.graphics.PointF;

public class Transform {
    public PointF position;
    public float getX(){return position.x;}
    public float getY(){return position.y;}
    public float rotation;
    public Transform(){
        position = new PointF();
        rotation = 0;
    }
}
