package com.example.android2dtest.scenes.exampleScenes;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.GridPoints;

public class MemoryGame extends GameScene {
    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public MemoryGame(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();
        GridPoints points = new GridPoints(5,5,100,100);
        for (int y = 0; y < points.getGrid().length; y++) {
            for (int x = 0; x < points.getGrid()[0].length; x++) {

            }
        }
    }
}
