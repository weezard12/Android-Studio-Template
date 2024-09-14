package com.example.android2dtest.scenes.exampleScenes.flappyBird;

import com.example.android2dtest.gameLogic.myECS.components.GameComponent;

public class MoveDownComponent extends GameComponent {
    float velocity = 0;
    @Override
    public void update(float delta) {
        super.update(delta);
        entity.setPosition(entity.getTransform().getX(), entity.getTransform().getY() - velocity);
        velocity -= delta * 1.5;
    }
}
