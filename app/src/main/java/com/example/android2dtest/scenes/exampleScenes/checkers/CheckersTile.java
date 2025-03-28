package com.example.android2dtest.scenes.exampleScenes.checkers;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import com.example.android2dtest.gameLogic.math.GridEntities;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.renderable.BoxRenderer;
import com.example.android2dtest.gameLogic.myECS.components.renderable.CircleRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

public class CheckersTile extends GameEntity {

    private final Point positionOnBoard;
    private final GridEntities board;

    private TileType type;
    public TileType getType() {
        return type;
    }

    private CircleRenderer pieceRenderer;
    private CircleRenderer kingRenderer;

    public CheckersTile(int x, int y, GridEntities gridEntities) {
        this.positionOnBoard = new Point(x,y);
        this.board = gridEntities;
        type = TileType.EMPTY;
    }

    @Override
    public void attachToScene(GameScene scene) {
        super.attachToScene(scene);

        setupComponents();
    }

    private void setupComponents() {
        // setup background color
        BoxRenderer tileColor = new BoxRenderer(board.getRowDistance());

        // Determine the color based on the checkerboard pattern
        if ((positionOnBoard.x + positionOnBoard.y) % 2 == 0) {
            tileColor.paint.setColor(Color.WHITE); // White tile
        } else {
            tileColor.paint.setColor(Color.GRAY); // Black tile (or any other color)
        }

        addComponent(tileColor);

        pieceRenderer = new CircleRenderer((int)(board.getRowDistance() / 2.4));
        pieceRenderer.paint.setColor(Color.RED);

        pieceRenderer.setEnabled(false);

        addComponent(pieceRenderer);

        kingRenderer = new CircleRenderer((int)(board.getRowDistance() / 4.4));
        kingRenderer.setEnabled(false);
        kingRenderer.paint.setStyle(Paint.Style.STROKE);
        kingRenderer.paint.setStrokeWidth(10);
        kingRenderer.paint.setColor(Color.rgb(255,215,0));
        addComponent(kingRenderer);
    }

    public void setTileType(TileType type){
        this.type = type;

        pieceRenderer.setEnabled(false);
        kingRenderer.setEnabled(false);

        switch (type){
            case EMPTY:
                break;

            case BLACK:
                pieceRenderer.paint.setColor(Color.BLACK);
                pieceRenderer.setEnabled(true);
                break;

            case WHITE:
                pieceRenderer.paint.setColor(Color.WHITE);
                pieceRenderer.setEnabled(true);
                break;

            case HIGHLIGHT:
                pieceRenderer.paint.setColor(Color.CYAN);
                pieceRenderer.setEnabled(true);
                break;

            case BLACK_KING:
                pieceRenderer.paint.setColor(Color.BLACK);
                pieceRenderer.setEnabled(true);
                kingRenderer.setEnabled(true);
                break;
            case WHITE_KING:
                pieceRenderer.paint.setColor(Color.WHITE);
                pieceRenderer.setEnabled(true);
                kingRenderer.setEnabled(true);
                break;
        }

    }
}
