package com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces;

import android.graphics.Point;
import android.graphics.Point;

import java.util.ArrayList;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.GameBoard;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.Tile;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.BasePiece;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.PieceType;


public class QueenPiece extends BasePiece {
    private final Point point1 = new Point(-1,-1);
    private final Point point2 = new Point(-1,-1);

    public QueenPiece(boolean isEnemy, BasePiece[][] board) {
        super(isEnemy, board);
        type = PieceType.QUEEN;
    }

    @Override
    public void getAllPossibleMoves(int pX, int pY, ArrayList<BasePiece[][]> r) {

        //Rook moves
        movePieceInRow(pX,pY,isEnemy,1,0, GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,-1,0,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,0,1,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,0,-1,GameBoard.cloneBoard(board),r);

        //Bishop moves
        movePieceInRow(pX,pY,isEnemy,1,1, GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,-1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,1,-1,GameBoard.cloneBoard(board),r);
        movePieceInRow(pX,pY,isEnemy,-1,1,GameBoard.cloneBoard(board),r);

        //Tile.setTileHighlight(r,this, GameBoard.tiles);
    }
    @Override
    public boolean doesCheck(int mX,int mY,int kX,int kY) {

        point1.x = -1;
        point1.y = -1;

        point2.x = kX;
        point2.y = kY;

        //ROOK
        //left
        if(moveInLineUntilHit(mX,mY,1,0,board,kX,kY,point1).equals(point2))
            return true;
        //right
        if(moveInLineUntilHit(mX,mY,-1,0,board,kX,kY,point1).equals(point2))
            return true;
        //up
        if(moveInLineUntilHit(mX,mY,0,1,board,kX,kY,point1).equals(point2))
            return true;
        //down
        if(moveInLineUntilHit(mX,mY,0,-1,board,kX,kY,point1).equals(point2))
            return true;

        //BISHOP
        //left
        if(moveInLineUntilHit(mX,mY,1,1,board,kX,kY,point1).equals(point2))
            return true;
        //right
        if(moveInLineUntilHit(mX,mY,1,-1,board,kX,kY,point1).equals(point2))
            return true;
        //up
        if(moveInLineUntilHit(mX,mY,-1,-1,board,kX,kY,point1).equals(point2))
            return true;
        //down
        if(moveInLineUntilHit(mX,mY,-1,1,board,kX,kY,point1).equals(point2))
            return true;
        return false;
    }
}
