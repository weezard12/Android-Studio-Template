package com.example.android2dtest.scenes.exampleScenes.flappyBird;

import android.content.Context;
import android.os.Handler;

import com.example.android2dtest.gameLogic.myECS.GameScene;

import java.util.ArrayList;
import java.util.List;

public class FlappyBirdScene extends GameScene {

    Bird bird = new Bird("bird");
    List<Pipe> pipes = new ArrayList<>();
    private static final float PIPE_SPAWN_INTERVAL = 2.0f; // Seconds
    private float pipeSpawnTimer = 0f;
    private Handler handler = new Handler(); // Handler for delayed actions
    private Runnable pipeSpawnRunnable = new Runnable() {
        @Override
        public void run() {
            spawnPipes();
            handler.postDelayed(this, (long) (PIPE_SPAWN_INTERVAL * 1000));
        }
    };

    public FlappyBirdScene(Context context) {
        super(context);
        debugRenderPhysics = true;
    }

    @Override
    public void start() {
        super.start();
        addEntity(bird);
        handler.postDelayed(pipeSpawnRunnable, (long) (PIPE_SPAWN_INTERVAL * 1000));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pipeSpawnTimer += delta;
        checkForLoose();
    }

    private void spawnPipes() {
        // Calculate a random Y position for the gap
        float gapY = (float) (Math.random() * (getScreenEnd().y - 400)) + 200; // 200 is minimum gap from top/bottom

        // Create top and bottom pipes
        //Pipe topPipe = new Pipe("topPipe",0); // 600 is pipe height
        Pipe bottomPipe = new Pipe("bottomPipe",0,true); // 200 is gap size

        // Add pipes to the scene and the list
        //addEntity(topPipe);
        addEntity(bottomPipe);
        //pipes.add(topPipe);
        pipes.add(bottomPipe);
    }


    private boolean checkForLoose(){
        return bird.getY() < 0 || bird.getY() > getScreenEnd().y;
    }

    @Override
    protected void handleClick(float x, float y) {
        super.handleClick(x, y);
        bird.flap();
    }

}