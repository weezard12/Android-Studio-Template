package com.example.android2dtest.scenes.exampleScenes.ChessTest.board;

import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.*;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.BasePiece;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.PieceType;

public class PromotionTile extends Tile{

    private final PieceType type;
    private final boolean isEnemy;

    public PromotionTile(int posX, int posY, GameBoard gameBoard, PieceType type,boolean isEnemy) {
        super(posX, posY, posX, posY, gameBoard);
        this.type = type;
        this.isEnemy = isEnemy;
    }
    public PromotionTile(int posX, int posY, float boundsX, float boundsY, GameBoard gameBoard,PieceType type,boolean isEnemy){
        super(posX, posY, boundsX, boundsY, gameBoard);
        this.type = type;
        this.isEnemy = isEnemy;
    }

    public void setNewPieceAt(int x, int y){
        switch (type){
            case KING:
            gameBoard.board[y][x] = new KingPiece(isEnemy,gameBoard.board);
            break;
            case KNIGHT:
                gameBoard.board[y][x] = new KnightPiece(isEnemy,gameBoard.board);
            break;
            case ROOK:
                gameBoard.board[y][x] = new RookPiece(isEnemy,gameBoard.board);
            break;
            case BISHOP:
                gameBoard.board[y][x] = new BishopPiece(isEnemy,gameBoard.board);
            break;
            case QUEEN:
                gameBoard.board[y][x] = new QueenPiece(isEnemy,gameBoard.board);
            break;
            case PAWN:
                gameBoard.board[y][x] = new PawnPiece(isEnemy,gameBoard.board);
            break;

        }

    }
}
