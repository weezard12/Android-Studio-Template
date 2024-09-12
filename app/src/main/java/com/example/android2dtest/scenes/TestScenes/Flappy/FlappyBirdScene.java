package com.example.android2dtest.scenes.TestScenes.Flappy;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;

public class FlappyBirdScene extends GameScene {

    Bird bird = new Bird("bird");

    public FlappyBirdScene(Context context) {
        super(context);
        addEntity(bird);
    }

    @Override
    protected void handleClick(float x, float y) {
        super.handleClick(x, y);
        bird.getComponent(MoveDownComponent.class).velocity = 5;
    }


}
