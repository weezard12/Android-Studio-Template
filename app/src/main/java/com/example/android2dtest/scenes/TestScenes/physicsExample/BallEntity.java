package com.example.android2dtest.scenes.TestScenes.physicsExample;

import com.example.android2dtest.gameLogic.myECS.components.IClickable;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

public class BallEntity extends GameEntity implements IClickable {
    public BallEntity(String name) {
        super(name);
    }

    @Override
    public void onClick() {

    }
}
