package com.example.android2dtest.scenes.exampleScenes.ChessTest.board;



import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Box;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.ai.Shtokfish;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.ai.ShtokfishThread;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.QueenPiece;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.BasePiece;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.PieceType;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes.ChessSceneBase;


public class PromotionSelection {

    private static BasePiece pieceToPromote;
    private static GameBoard board;
    private static Box outlineBounds;

    private static final PromotionTile[] tiles = new PromotionTile[4];


    public static boolean isPromoting = false;

    public static void startPromotion(BasePiece pieceToPromote, GameBoard board, Tile moveToTile){
        pieceToPromote.updatePos();

        PromotionSelection.pieceToPromote = pieceToPromote;
        PromotionSelection.board = board;


/*
        if(!board.isBlackRotationBoard)
            outlineBounds = new Box((moveToTile.positionOnScreen.x ),(moveToTile.getTileBoundsYAsPos() - (!pieceToPromote.isEnemy ? 3.4f : 0)) * ChessSceneBase.tileSize,ChessSceneBase.tileSize,ChessSceneBase.tileSize*4.4f);
        else
            outlineBounds = new Box((moveToTile.positionOnScreen.x ),(moveToTile.getTileBoundsYAsPos() -3.4f + (!pieceToPromote.isEnemy ? 3.4f : 0)) * ChessSceneBase.tileSize,ChessSceneBase.tileSize,ChessSceneBase.tileSize*4.4f);
*/

        tiles[0] = new PromotionTile(moveToTile, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?0:7, board,PieceType.QUEEN,pieceToPromote.isEnemy);
        tiles[1] = new PromotionTile(moveToTile, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?1:6, board,PieceType.KNIGHT,pieceToPromote.isEnemy);
        tiles[2] = new PromotionTile(moveToTile, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?2:5, board,PieceType.ROOK,pieceToPromote.isEnemy);
        tiles[3] = new PromotionTile(moveToTile, (pieceToPromote.isEnemy == !board.isBlackRotationBoard)?3:4, board,PieceType.BISHOP,pieceToPromote.isEnemy);

        for (PromotionTile tile : tiles) {
            board.entity.scene.addEntity(tile);
        }

        board.board[pieceToPromote.posY][pieceToPromote.posX] = null;
        isPromoting = true;
    }



}
