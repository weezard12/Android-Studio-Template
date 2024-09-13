package com.example.android2dtest.scenes.TestScenes.physicsExample;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.CircleCollider;

public class PhysicsExampleScene extends GameScene {
    GameEntity ball = new GameEntity("ball");
    public PhysicsExampleScene(Context context) {
        super(context);
        ball.setPosition(200,200);
        ball.addComponent(new CircleCollider(100));
        addEntity(ball);
    }
}
