package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.GameBoard;

public class ChessSceneBase extends GameScene {
    public static int tileSize = 128;
    public static int boardSize = 1024;

    protected GameBoard gameBoard;

    public ChessSceneBase(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();

    }
}
