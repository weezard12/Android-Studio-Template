package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.content.Context;

import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.ai.Shtokfish;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.GameBoard;

public class ChessSceneBase extends GameScene {
    public static int tileSize = 128;
    public static int boardSize = 1024;

    protected GameBoard gameBoard;

    public ChessSceneBase(Context context) {
        super(context);
        gameBoard = new GameBoard(MyGdxGame.batch);
        Shtokfish.init(gameBoard);

        GameBoard.setBoardByString(gameBoard.board,
            "Br,Bk,Bb,Bq,BK,Bb,Bk,Br,"+
                "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp,"+
                "e,e,e,e,e,e,e,e,"+
                "e,e,e,e,e,e,e,e,"+
                "e,e,e,e,e,e,e,e,"+
                "e,e,e,e,e,e,e,e,"+
                "p,p,p,p,p,p,p,p,"+
                "r,k,b,q,K,b,k,r,"
        );
    }

    @Override
    public void start() {
        super.start();

    }
}
