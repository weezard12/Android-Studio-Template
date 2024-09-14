package com.example.android2dtest.gameLogic.myECS.components;

import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;


public abstract class GameComponent {
    public GameEntity entity;
    public void update(float delta) {

    }
    public void attachToEntity(GameEntity entity){
        this.entity = entity;
    }

    public void detachFromEntity(GameEntity entity) {
        this.entity = null;
    }

}
