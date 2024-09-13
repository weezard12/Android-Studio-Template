package com.example.android2dtest.gameLogic.myPhysics.shapes;

import android.graphics.PointF;

public class Box extends Shape{
    public float width;
    public float height;

    @Override
    public boolean collidesWith(Shape other) {
        return false;
    }

    @Override
    public boolean collidesWithPoint(PointF point) {
        return false;
    }
}
