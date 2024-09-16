package com.example.android2dtest.gameLogic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.android2dtest.gameLogic.myECS.GameScene;

public class GameLoop extends Thread {
    private volatile boolean isRunning = true;
    private static final float TARGET_FPS = 60;
    private static final float FRAME_INTERVAL_NS = 1000000000.0f / TARGET_FPS;

    private long lastFrameTime;
    private long timer;
    private int frames;
    private int fps;
    public static float deltaTime = 0;

    public GameScene scene;

    public GameLoop() {
        lastFrameTime = System.nanoTime();
        timer = System.currentTimeMillis();
    }

    public GameLoop(GameScene scene) {
        this();
        this.scene = scene;
    }

    @Override
    public void run() {
        while (isRunning) {
            long currentTime = System.nanoTime();
            deltaTime = (currentTime - lastFrameTime) / 1000000000.0f;

            if ((currentTime - lastFrameTime) >= FRAME_INTERVAL_NS) {
                updateAndRender();
                lastFrameTime = currentTime; // Assign current time here
                frames++;

                if (System.currentTimeMillis() - timer >= 1000) {
                    fps = frames;
                    frames = 0;
                    timer += 1000;
                }
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateAndRender() {
        if (scene != null) {

            Canvas canvas = null;
            try {
                canvas = scene.getHolder().lockCanvas();
                if (canvas != null) {
                    synchronized (scene.getHolder()) {
                        scene.update(deltaTime);
                        scene.draw(canvas);

                        debugRender(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    scene.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setScene(GameScene scene) {
        this.scene = scene;
    }

    public void stopLoop() {
        isRunning = false;
    }

    private void logFrameStats() {
        Log.i("debug", "FPS: " + frames);
    }

    private void debugRender(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(80); // Adjust text size as needed
        canvas.drawText("FPS: " + fps, 100, 100, paint); // Draw FPS at top-left
    }

}