package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.content.Context;

public class ChessHotSit extends ChessSceneBase{
    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public ChessHotSit(Context context) {
        super(context);
        gameBoard.isUpdatingInput = true;
    }
}
