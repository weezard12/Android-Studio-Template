package com.example.android2dtest.scenes.TestScenes.Flappy;

import com.example.android2dtest.gameLogic.myECS.components.CircleRendererComponent;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

public class Bird extends GameEntity {
    public Bird(String name) {
        super(name);

        //sets the position
        setPosition(100,100);

        addComponent(new CircleRendererComponent(100));
        addComponent(new MoveDownComponent());
    }

    @Override
    public void update(float delta) {
        super.update(delta);

    }
}
