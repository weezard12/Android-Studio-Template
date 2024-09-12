package com.example.android2dtest.gameLogic.myECS;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends SurfaceView implements SurfaceHolder.Callback {

    private List<GameEntity> entities;

    public Point getViewCenter() {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        return new Point(centerX, centerY);
    }

    public GameScene(Context context) {
        super(context);
        entities = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Check if the event is a click
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Get the X and Y coordinates of the touch
            float x = event.getX();
            float y = event.getY();

            // Do something when the screen is clicked
            handleClick(x, y);

            // Return true to indicate the event was handled
            return true;
        }
        return false;
    }

    protected void handleClick(float x, float y) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for(GameEntity entity : entities)
            entity.render(GameLoop.DELTA_TIME, canvas);


/*        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(1,1,100,paint);
        canvas.drawText("TEST",100,100, paint);*/
    }

    public void update(float delta){
        for(GameEntity entity : entities){
            entity.update(delta);
        }
    }

    public void addEntity(GameEntity entity){
        entity.attachToScene(this);
        entities.add(entity);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
