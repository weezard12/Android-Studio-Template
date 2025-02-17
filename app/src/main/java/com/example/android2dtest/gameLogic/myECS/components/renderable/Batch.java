package com.example.android2dtest.gameLogic.myECS.components.renderable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Box;

public final class Batch extends GameEntity {


    public Batch(String name) {
        super(name);

    }

    public static Paint getDefaultPaint(){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        return paint;
    }

    public static void drawBox(Canvas canvas, float x, float y, Box box){
        drawBox(canvas,x,y,box.width,box.height, getDefaultPaint());
    }
    public static void drawBox(Canvas canvas, float x, float y, Box box, Color color){
        Paint paint = new Paint();
        paint.setColor(color.toArgb());
        drawBox(canvas,x,y,box.width,box.height, paint);
    }
    public static void drawBox(Canvas canvas, float x, float y, Box box, Paint paint){
        drawBox(canvas,x,y,box.width,box.height, paint);
    }
    public static void drawBox(Canvas canvas, float x, float y, float width, float height, Paint paint){
        canvas.drawRect(x - (width / 2),y + (height/2),x+(width/2),y-(height/2), paint);
    }

}
