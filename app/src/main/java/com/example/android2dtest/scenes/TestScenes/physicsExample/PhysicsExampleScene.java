package com.example.android2dtest.scenes.TestScenes.physicsExample;

import android.content.Context;

import com.example.android2dtest.gameLogic.extraComponents.touch.DraggableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;
import com.example.android2dtest.gameLogic.myPhysics.CircleCollider;

public class PhysicsExampleScene extends GameScene {
    GameEntity ball = new GameEntity("ball");
    GameEntity box = new GameEntity("box");
    public PhysicsExampleScene(Context context) {
        super(context);
    }

    @Override
    public void start() {
        addEntity(ball);
        ball.setPosition(200,200);
        ball.addComponent(new CircleCollider(100));
        ball.addComponent(new DraggableComponent(ball.getComponent(CircleCollider.class)));

        addEntity(box);
        box.setPosition(400,400);
        box.addComponent(new BoxCollider(1000,1000));
        box.addComponent(new DraggableComponent(box.getComponent(BoxCollider.class)));
    }
}
