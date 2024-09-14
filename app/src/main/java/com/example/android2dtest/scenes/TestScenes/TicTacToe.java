package com.example.android2dtest.scenes.TestScenes;

import static com.example.android2dtest.gameLogic.MyDebug.log;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.ContentManager;
import com.example.android2dtest.gameLogic.extraComponents.touch.ClickableComponent;
import com.example.android2dtest.gameLogic.extraComponents.touch.DraggableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.Sprite;
import com.example.android2dtest.gameLogic.myECS.components.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.components.TextRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

public class TicTacToe extends GameScene {
    public boolean xTurn = true;
    Tile[][] tiles = new Tile[3][3];

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

        setBackgroundColor(Color.BLUE);

        GameEntity board = new GameEntity("board");
        addEntity(board);
        board.addComponent(new SpriteRenderer(new Sprite(contentManager.loadTextureFromDrawable(R.drawable.tic_tac_toe))));
        board.addComponent(new BoxCollider(200,200));
        board.addComponent(new DraggableComponent(board.getComponent(BoxCollider.class)));
        board.addComponent(new TextRenderer("Tic Tac Toe"));
        board.setPosition(getSurfaceCenter());

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                tiles[y][x] = new Tile("tile",new Point(x,y));
                addEntity(tiles[y][x]);
            }
        }
    }


    public class Tile extends GameEntity{
        Point id;
        String type;

        TextRenderer renderer;
        BoxCollider collider;
        ClickableComponent clickableComponent;
        public Tile(String name, Point id) {
            super(name);
            this.id = id;

            collider = new BoxCollider(300,300);
            addComponent(collider);

            //position
            setPosition(GameScene.getSurfaceCenter().x + ((id.x -1)*350),GameScene.getSurfaceCenter().y + ((id.y -1)*360));

            renderer = new TextRenderer("");
            renderer.paint.setTextSize(200);
            renderer.setOffset(0,60);
            addComponent(renderer);
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);
            clickableComponent = new ClickableComponent(collider);
            clickableComponent.setOnClickListener(new ClickableComponent.OnClickListener() {
                @Override
                public void onClick(float x, float y) {
                    if(!renderer.getDrawText().equals(""))
                        return;

                    TicTacToe gameScene = (TicTacToe) scene;
                    if(gameScene.xTurn)
                        renderer.setDrawText("X");
                    else
                        renderer.setDrawText("O");

                    gameScene.xTurn = !gameScene.xTurn;
                }
            });
            addComponent(clickableComponent);
        }
    }
}

