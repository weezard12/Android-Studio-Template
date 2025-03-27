package com.example.android2dtest.scenes.exampleScenes.checkers;

import android.content.Context;
import android.util.Log;

import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.math.GridEntities;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;

public class CheckersScene extends GameScene {

    public CheckersScene(Context context) {
        super(context);


    }

    @Override
    public void start() {
        super.start();

        debugRenderPhysics = true;
        debugRenderScene = true;

        GridEntities entityGrid = new GridEntities(8, 8, 110, 110);
        entityGrid.setupBoardWithNewEntities();
        for (GameEntity[] row : entityGrid.getGrid()) {
            for (GameEntity e : row)
                addEntity(e);
        }
        entityGrid.setPosition(getSurfaceCenter().x, getSurfaceCenter().y);
        entityGrid.setGridClickable();

        entityGrid.setOnClickListener(new GridEntities.OnClickListener() {
            @Override
            public void onClick(int row, int column) {
                Log.i("Board","Clicked: " + row + " " + column);
            }
        });
    }
}
