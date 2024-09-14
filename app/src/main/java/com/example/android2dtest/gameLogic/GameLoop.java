package com.example.android2dtest.gameLogic;

import android.graphics.Canvas;
import android.util.Log;

import com.example.android2dtest.gameLogic.myECS.GameScene;

public class GameLoop extends Thread {
    private volatile boolean isRunning = true;
    private static final float TARGET_FPS = 60;
    private static final float FRAME_INTERVAL_NS = 1000000000.0f / TARGET_FPS;

    private long lastFrameTime;
    private long timer;
    private int frames;
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
                lastFrameTime += FRAME_INTERVAL_NS;
                frames++;

                if (System.currentTimeMillis() - timer >= 1000) {
                    logFrameStats();
                    frames = 0;
                    timer += 1000;
                }
            } else {
                // Sleep/yield if running ahead of target FPS
                try {
                    Thread.sleep(1); // Adjust sleep duration as needed
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
}