package com.example.android2dtest.gameLogic;

import android.graphics.Canvas;
import android.util.Log;

import com.example.android2dtest.gameLogic.myECS.GameScene;

public class GameLoop extends Thread {
    public static boolean GAME_RUNNING = true;

    public static float DELTA_TIME = 0;
    public static final float UPS_MAX = 30; // Updates per second
    public static final float RPS_MAX = 30; // Frames per second

    private static final float NS_PER_UPDATE = 1000000000.0f / UPS_MAX;
    private static final float NS_PER_RENDER = 1000000000.0f / RPS_MAX;

    private long lastUpdateTime;
    private long lastRenderTime;
    private long timer;
    private int updates;
    private int frames;

    public GameScene scene;

    public GameLoop(){

    }
    public GameLoop(GameScene scene){
        this.scene = scene;
    }

    @Override
    public synchronized void start() {
        super.start();
        lastUpdateTime = System.nanoTime();
        lastRenderTime = System.nanoTime();
        timer = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (GAME_RUNNING) {
            long currentTime = System.nanoTime();

            // Calculate delta time in seconds for update logic
            DELTA_TIME = (currentTime - lastUpdateTime) / 1000000000.0f;

            // Update logic
            if ((currentTime - lastUpdateTime) >= NS_PER_UPDATE) {
                update();
                lastUpdateTime += NS_PER_UPDATE;
                updates++;
            }

            // Render logic
            if ((currentTime - lastRenderTime) >= NS_PER_RENDER) {
                render();
                lastRenderTime += NS_PER_RENDER;
                frames++;
            }

            // FPS and UPS display per second
            if (System.currentTimeMillis() - timer >= 1000) {
                debugLogStats();
                updates = 0;
                frames = 0;
                timer += 1000;
            }
        }
    }

    private void update() {
        // Game update logic goes here
        // DELTA_TIME can be used to adjust game physics or animations
    }
    private void render() {
        // Game rendering logic goes here
        if(scene == null)
            return;
        // Get the SurfaceHolder to manage canvas locking
        Canvas canvas = null;
        try {
            canvas = scene.getHolder().lockCanvas(); // Lock the canvas for drawing
            if (canvas != null) {
                synchronized (scene.getHolder()) {
                    scene.draw(canvas); // Call the draw method from GameBase
                }
            }
        } finally {
            if (canvas != null) {
                scene.getHolder().unlockCanvasAndPost(canvas); // Post and unlock the canvas
            }
        }
    }


    //Debug
    private void debugLogStats(){
        Log.i("debug","UPS: " + updates + ", FPS: " + frames);
    }
}

