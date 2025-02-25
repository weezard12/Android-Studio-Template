package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes.base;


import android.content.Context;

public class ChessVsBot extends ChessSceneBase {

    public ChessVsBot(Context game) {
        super(game);
        gameBoard.moveTheBot = true;
        gameBoard.isFreeMove = false;
    }

}
