package com.example.android2dtest.gameLogic.myECS.components;

import static com.example.android2dtest.gameLogic.MyDebug.log;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleRendererComponent extends RenderableComponent {

    public int radius;

    public CircleRendererComponent(int radius){
        this.radius = radius;
    }
    public CircleRendererComponent(int radius, Paint color){
        this.radius = radius;
        this.paint = color;
    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render(float delta, Canvas canvas) {
        super.render(delta, canvas);
        canvas.drawCircle(entity.getTransform().getX(), entity.getTransform().getY(),radius,paint);
    }
}
