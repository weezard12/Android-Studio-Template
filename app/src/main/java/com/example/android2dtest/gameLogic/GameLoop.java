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
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long currentTime = System.nanoTime();
            scene.deltaTime = (currentTime - lastFrameTime) / 1_000_000_000.0f; // Convert ns to seconds

            if ((currentTime - lastFrameTime) >= FRAME_INTERVAL_NS) {
                updateAndRender();
                lastFrameTime = currentTime; // Assign current time here
                frames++;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                scene.fps = frames;
                frames = 0;
                timer = System.currentTimeMillis(); // Reset using current time to avoid drift
            }

            try {
                Thread.sleep(1); // Prevent CPU overload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateAndRender() {
        // Check if the scene is initialized
        if (scene != null) {

            Canvas canvas = null;
            try {
                // Try to acquire the canvas for drawing
                canvas = scene.getHolder().lockCanvas();

                if (canvas != null) {
                    // If canvas is acquired successfully
                    synchronized (scene.getHolder()) {
                        // Update the scene logic
                        scene.update();
                        // Draw the scene content on the canvas
                        scene.draw(canvas);
                    }
                }
            } finally {
                // Ensure the canvas is released even if an exception occurs
                if (canvas != null) {
                    // Unlock the canvas and post the new drawing
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