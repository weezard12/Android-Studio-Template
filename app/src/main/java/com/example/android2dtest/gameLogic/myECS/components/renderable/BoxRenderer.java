package com.example.android2dtest.gameLogic.myECS.components.renderable;

import android.graphics.Canvas;

public class BoxRenderer extends RenderableComponent {
    public float width;
    public float height;

    public BoxRenderer(float width, float height) {
        this.width = width;
        this.height = height;
    }
    public BoxRenderer(float widthAndHeight) {
        this.width = widthAndHeight;
        this.height = widthAndHeight;
    }

    @Override
    public void render(float delta, Canvas canvas) {
        super.render(delta, canvas);
        canvas.drawRect(entity.getX() - (width / 2) + offset.x,entity.getY() + (height/2) + offset.y,entity.getX() + (width/2) + offset.x,entity.getY()-(height/2) + offset.y, paint);
    }
}
