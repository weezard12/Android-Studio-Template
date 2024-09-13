package com.example.android2dtest.gameLogic.myPhysics;

import static com.example.android2dtest.gameLogic.MyDebug.log;
import static com.example.android2dtest.main.MyUtils.distance;

import android.graphics.Point;
import android.graphics.PointF;
import android.location.Location;

public class Circle extends Shape{
    public float radius;
    public Circle(float radius){
        this.radius = radius;
    }

    @Override
    public boolean collidesWith(Shape other) {
        if (other instanceof Circle){
            Circle otherCircle = (Circle) other;
            float dx = (this.center.x + this.offset.x) - (otherCircle.center.x + otherCircle.offset.x);
            float dy = (this.center.y + this.offset.y) - (otherCircle.center.y + otherCircle.offset.y);
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            return distance < this.radius + otherCircle.radius;

        }
        else {
            log("collision shape not implemented!");
        }
        return false;
    }

    @Override
    public boolean collidesWithPoint(PointF point) {
        return radius > distance(center.x + offset.x,center.y + offset.y,point.x, point.y);
    }
}
