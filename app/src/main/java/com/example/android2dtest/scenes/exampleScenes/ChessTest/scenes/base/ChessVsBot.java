package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes.base;


import android.content.Context;
import android.util.AttributeSet;

public class ChessVsBot extends ChessSceneBase {

    public ChessVsBot(Context game) {
        super(game);
        gameBoard.moveTheBot = true;
        gameBoard.isFreeMove = false;
    }

    public ChessVsBot(Context context, AttributeSet attrs) {
        this(context);
    }

}
