package com.example.android2dtest.scenes.exampleScenes;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

public class SwordThrow extends GameScene {
    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public SwordThrow(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();

    }

    public class CircleEntity extends GameEntity {

        public CircleEntity(String name) {
            super(name);
        }
    }
}
