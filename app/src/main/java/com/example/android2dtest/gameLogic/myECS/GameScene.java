package com.example.android2dtest.gameLogic.myECS;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.android2dtest.gameLogic.ContentManager;
import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.extraComponents.touch.TouchBase;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;
import com.example.android2dtest.gameLogic.extraComponents.touch.DraggableComponent;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends SurfaceView implements SurfaceHolder.Callback {
    private List<GameEntity> entities;

    private final List<TouchBase> touchables;

    public ContentManager contentManager;

    public void addTouchable(TouchBase touchable){
        touchables.add(touchable);
    }
    public void removeTouchable(TouchBase touchable){
        touchables.remove(touchable);
    }

    public Point getViewCenter() {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        return new Point(centerX, centerY);
    }
    /**
    * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     */
    public GameScene(Context context) {
        super(context);
        entities = new ArrayList<>();
        touchables = new ArrayList<>();
        contentManager = new ContentManager(context);
    }

    //this method will be called from the game loop.
    //override it for scene start logic
    public void start(){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //updates entities that needs a touch event
        for (TouchBase touchable : touchables)
            touchable.onTouchEvent(event);

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

        //draw physics debug
        PhysicsSystem.debugRenderPhysics(canvas);

/*        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(1,1,100,paint);
        canvas.drawText("TEST",100,100, paint);*/
    }

    public void update(float delta){
        for(GameEntity entity : entities){
            entity.update(delta);
        }

        //updates physics
        PhysicsSystem.update(delta);
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
