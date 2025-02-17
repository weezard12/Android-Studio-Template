package com.example.android2dtest.scenes.exampleScenes.ChessTest.board;

import java.util.ArrayList;

import com.example.android2dtest.gameLogic.myPhysics.shapes.Box;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.QueenPiece;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.BasePiece;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes.ChessSceneBase;


public class Tile {
    GameBoard gameBoard;
    public TileHighlightType highlightType = TileHighlightType.NONE;

    //from 0 - 8 (tiles on the board)
    public int posX;
    public int posY;
    Box bounds;

    public int getTileBoundsYAsPos(){
        return (int) bounds.y / ChessSceneBase.tileSize;
    }

    public Tile(int posX, int posY, int boundsX, int boundsY, GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.posX = posX;
        this.posY = posY;
        bounds = new Rectangle((boundsX*ChessSceneBase.tileSize)+gameBoard.offsetToRight,boundsY* ChessSceneBase.tileSize,ChessSceneBase.tileSize,ChessSceneBase.tileSize);
    }

    //creates the tile by providing the bounds of it NOT from (0 - 7) instead by a float of other tile bounds
    public Tile(int posX, int posY, float boundsX, float boundsY, GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.posX = posX;
        this.posY = posY;
        bounds = new Rectangle(boundsX,boundsY,ChessSceneBase.tileSize,ChessSceneBase.tileSize);
    }

    @Override
    public String toString() {
        return String.format("x: %s, y: %s, piece on the tile: %s ",posX,posY, (gameBoard.board[posY][posX] == null) ? "empty" : gameBoard.board[posY][posX].type + " "+gameBoard.board[posY][posX].isEnemy);
    }

    public static void setTileHighlight(ArrayList<BasePiece[][]> moves, BasePiece piece,Tile[][] tiles){
        int bCount = 0;
        for (BasePiece[][] boards : moves) {
            for (BasePiece[] row : boards) {
                for (BasePiece p : row) {
                    if(p != null)
                        if(p.isJustMoved){
                            tiles[p.getPosY()][p.getPosX()].highlightType = TileHighlightType.CAN_MOVE_TO;
                            MyDebug.log("highlight",p.getPosX()+" "+p.getPosY()+" "+p.type+" "+bCount);
                        }
                }

            }
            bCount++;
        }
    }
}
