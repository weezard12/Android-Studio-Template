package com.example.android2dtest.scenes.exampleScenes.checkers;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

import com.example.android2dtest.gameLogic.math.GridEntities;
import com.example.android2dtest.gameLogic.myECS.GameScene;

import java.util.ArrayList;

public class CheckersScene extends GameScene {

    private GridEntities board;

    private boolean whiteToMove = true;

    ArrayList<Move> possibleMoves = new ArrayList<>();

    public CheckersScene(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();

        //debugRenderPhysics = true;
        //debugRenderScene = true;

        board = new GridEntities(8, 8, 110, 110);

        for (int y = 0; y < board.getGrid().length; y++) {
            for (int x = 0; x < board.getGrid()[y].length; x++) {
                board.getGrid()[y][x] = new CheckersTile(x, y, board);
                addEntity(board.getGrid()[y][x]);
            }
        }

        board.setPosition(getSurfaceCenter().x, getSurfaceCenter().y);
        board.setGridClickable();

        setupBoardFromString(
                " b b b b" +
                "b b b b " +
                " b b b b" +
                "        " +
                "        " +
                "w w w w " +
                " w w w w" +
                "w w w w "
        );

        board.setOnClickListener(new GridEntities.OnClickListener() {
            @Override
            public void onClick(int row, int column) {
                Log.i("Board", "Clicked: " + row + " " + column);

                if(((CheckersTile)board.getGrid()[row][column]).getType() == TileType.EMPTY){
                    //setTileOnBoard(column, row+1, TileType.HIGHLIGHT);
                }
                else if(((CheckersTile)board.getGrid()[row][column]).getType() == TileType.HIGHLIGHT){
                    for (Move move : possibleMoves) {
                        if(move.movingTo.x == column && move.movingTo.y == row){

                            if(move.movingTo.y == 7){
                                if(move.position[row][column] == TileType.BLACK){
                                    move.position[row][column] = TileType.BLACK_KING;
                                }
                            }
                            else if(move.movingTo.y == 0){
                                if(move.position[row][column] == TileType.WHITE){
                                    move.position[row][column] = TileType.WHITE_KING;
                                }
                            }

                            setBoardFromMove(move);
                            whiteToMove = !whiteToMove;
                            break;
                        }
                    }
                    clearBoardFromHighlights();
                }
                else{
                    if(TileType.isWhite(((CheckersTile)board.getGrid()[row][column]).getType()) != whiteToMove)
                        return;
                    clearBoardFromHighlights();
                    getMovesForPiece(row, column,new Move(column, row, boardToArray()));
                }


            }
        });
    }

    private void setBoardFromMove(Move move) {
        for (int y = 0; y < move.position.length; y++) {
            for (int x = 0; x < move.position[y].length; x++) {
                setTileOnBoard(x,y,move.position[y][x]);
            }
        }
    }

    private void setupBoardFromString(String boardString) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char tileTypeChar = boardString.charAt(x + (y * 8));
                CheckersTile tile = (CheckersTile) board.getGrid()[y][x];
                tile.setTileType(TileType.fromChar(tileTypeChar));
            }
        }
    }

    private void setTileOnBoard(int x, int y, TileType type){
        ((CheckersTile)board.getGrid()[y][x]).setTileType(type);
    }

    private TileType getTileOnBoard(int x, int y){
        return ((CheckersTile)board.getGrid()[y][x]).getType();
    }
    private TileType getTileOnBoard(int x, int y, Move move){
        return move.position[y][x];
    }

    //region Game Logic
    private void getMovesForPiece(int row, int column, Move currentMove) {
        CheckersTile selectedTile = (CheckersTile) board.getGrid()[row][column];
        TileType pieceType = selectedTile.getType();

        if (pieceType == TileType.EMPTY) return;

        boolean isWhite = (pieceType == TileType.WHITE || pieceType == TileType.WHITE_KING);
        boolean isKing = (pieceType == TileType.BLACK_KING || pieceType == TileType.WHITE_KING);

        ArrayList<Move> captures = new ArrayList<>();

        // Normal piece movement
        if (!isKing) {
            int direction = isWhite ? -1 : 1; // White moves up (-1), Black moves down (+1)
            tryMove(column, row, new Point(1, direction), currentMove);
            tryMove(column, row, new Point(-1, direction), currentMove);
            tryCapture(column, row, new Point(1, direction), currentMove, captures);
            tryCapture(column, row, new Point(-1, direction), currentMove, captures);
        }
        // King movement: Can move in all four diagonal directions
        else {
            tryMove(column, row, new Point(1, 1), currentMove);
            tryMove(column, row, new Point(-1, 1), currentMove);
            tryMove(column, row, new Point(1, -1), currentMove);
            tryMove(column, row, new Point(-1, -1), currentMove);

            tryCapture(column, row, new Point(1, 1), currentMove, captures);
            tryCapture(column, row, new Point(-1, 1), currentMove, captures);
            tryCapture(column, row, new Point(1, -1), currentMove, captures);
            tryCapture(column, row, new Point(-1, -1), currentMove, captures);
        }

        if (!captures.isEmpty()) {
            possibleMoves.addAll(captures);
        }
    }



    private void tryMove(int column, int row, Point direction, Move currentMove) {
        int newColumn = column + direction.x;
        int newRow = row + direction.y;

        TileType pieceType = getTileOnBoard(column, row, currentMove);
        boolean isKing = (pieceType == TileType.BLACK_KING || pieceType == TileType.WHITE_KING);

        while (isWithinBounds(newRow, newColumn) && getTileOnBoard(newColumn, newRow, currentMove) == TileType.EMPTY) {
            Move move = new Move(newColumn, newRow, boardToArray());
            move.position[newRow][newColumn] = pieceType;
            move.position[row][column] = TileType.EMPTY;
            possibleMoves.add(move);
            setTileOnBoard(newColumn, newRow, TileType.HIGHLIGHT);

            if (!isKing) break; // Normal pieces only move one step, kings continue

            newColumn += direction.x;
            newRow += direction.y;
        }
    }


    private void tryCapture(int column, int row, Point direction, Move currentMove, ArrayList<Move> captures) {
        int midColumn = column + direction.x;
        int midRow = row + direction.y;
        int newColumn = column + 2 * direction.x;
        int newRow = row + 2 * direction.y;

        TileType pieceType = getTileOnBoard(column, row, currentMove);

        if (isWithinBounds(midRow, midColumn) &&
                isWithinBounds(newRow, newColumn) &&
                getTileOnBoard(midColumn, midRow, currentMove) != TileType.EMPTY &&
                getTileOnBoard(midColumn, midRow, currentMove) != pieceType &&
                getTileOnBoard(newColumn, newRow, currentMove) == TileType.EMPTY) {

            Move newMove = new Move(newColumn, newRow, deepCopyBoard(currentMove.position));

            // Move the piece and remove the captured piece
            newMove.position[newRow][newColumn] = pieceType;
            newMove.position[row][column] = TileType.EMPTY;
            newMove.position[midRow][midColumn] = TileType.EMPTY;

            captures.add(newMove);
            possibleMoves.add(newMove);
            setTileOnBoard(newColumn, newRow, TileType.HIGHLIGHT);

            // **NEW: Check for additional captures for both normal pieces & kings**
            tryCapture(newColumn, newRow, new Point(1, 1), newMove, captures);
            tryCapture(newColumn, newRow, new Point(-1, 1), newMove, captures);
            tryCapture(newColumn, newRow, new Point(1, -1), newMove, captures);
            tryCapture(newColumn, newRow, new Point(-1, -1), newMove, captures);
        }
    }

    private TileType[][] deepCopyBoard(TileType[][] original) {
        TileType[][] copy = new TileType[8][8];
        for (int y = 0; y < 8; y++) {
            System.arraycopy(original[y], 0, copy[y], 0, 8);
        }
        return copy;
    }



    // Helper method to check board bounds
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    // Convert board state to TileType[][] for Move storage
    private TileType[][] boardToArray() {
        TileType[][] snapshot = new TileType[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                snapshot[y][x] = ((CheckersTile) board.getGrid()[y][x]).getType();
            }
        }
        return snapshot;
    }

    private void clearBoardFromHighlights(){
        possibleMoves.clear();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if(((CheckersTile) board.getGrid()[y][x]).getType() == TileType.HIGHLIGHT)
                    ((CheckersTile) board.getGrid()[y][x]).setTileType(TileType.EMPTY);
            }
        }
    }

    //endregion
}