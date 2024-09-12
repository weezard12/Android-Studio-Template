package com.example.android2dtest.scenes.TestScenes.Flappy;

import com.example.android2dtest.gameLogic.myECS.components.GameComponent;

public class MoveDownComponent extends GameComponent {
    float velocity = 0;
    @Override
    public void update(float delta) {
        super.update(delta);
        entity.setPosition(entity.getTransform().x, entity.getTransform().y - velocity);
        velocity -= delta * 1.5;
    }
}
