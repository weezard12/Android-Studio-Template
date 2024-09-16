package com.example.android2dtest.gameLogic.myECS.components.renderable;

import android.graphics.Canvas;

public class BoxRenderer extends RenderableComponent {
    float width;
    float height;

    public BoxRenderer(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(float delta, Canvas canvas) {
        super.render(delta, canvas);
        canvas.drawRect(entity.getX(),entity.getY()+height,entity.getX()+width,entity.getY(),paint);
    }
}
