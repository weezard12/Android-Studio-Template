package com.example.android2dtest.scenes.exampleScenes;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;

public class RenderingExample extends GameScene {
    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public RenderingExample(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();

    }
}
