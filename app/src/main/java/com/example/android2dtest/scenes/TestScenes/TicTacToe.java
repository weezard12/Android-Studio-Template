package com.example.android2dtest.scenes.TestScenes;

import android.content.Context;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.ContentManager;
import com.example.android2dtest.gameLogic.extraComponents.touch.DraggableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.Sprite;
import com.example.android2dtest.gameLogic.myECS.components.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

public class TicTacToe extends GameScene {
    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public TicTacToe(Context context) {
        super(context);
    }

    @Override
    public void start() {
        GameEntity board = new GameEntity("board");
        addEntity(board);
        board.addComponent(new SpriteRenderer(new Sprite(contentManager.loadTextureFromDrawable(R.drawable.tic_tac_toe))));
        board.addComponent(new BoxCollider(200,200));
        board.addComponent(new DraggableComponent(board.getComponent(BoxCollider.class)));
        board.setPosition(250,250);
    }
}
