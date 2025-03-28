package com.example.android2dtest.scenes.exampleScenes.checkers;

import android.content.Context;
import android.util.Log;

import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.math.GridEntities;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;

public class CheckersScene extends GameScene {

    private GridEntities entityGrid;

    public CheckersScene(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();

        debugRenderPhysics = true;
        debugRenderScene = true;

        entityGrid = new GridEntities(8, 8, 110, 110);

        for (int y = 0; y < entityGrid.getGrid().length; y++) {
            for (int x = 0; x < entityGrid.getGrid()[y].length; x++) {
                entityGrid.getGrid()[y][x] = new CheckersTile(x, y, entityGrid);
                addEntity(entityGrid.getGrid()[y][x]);
            }
        }

        entityGrid.setPosition(getSurfaceCenter().x, getSurfaceCenter().y);
        entityGrid.setGridClickable();

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

        entityGrid.setOnClickListener(new GridEntities.OnClickListener() {
            @Override
            public void onClick(int row, int column) {
                Log.i("Board", "Clicked: " + row + " " + column);
            }
        });
    }

    private void setupBoardFromString(String boardString) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char tileTypeChar = boardString.charAt(x + (y * 8));
                CheckersTile tile = (CheckersTile) entityGrid.getGrid()[y][x];
                tile.setTileType(TileType.fromChar(tileTypeChar));
            }
        }
    }

}