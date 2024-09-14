package com.example.android2dtest.scenes.exampleScenes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.extraComponents.touch.ClickableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.Sprite;
import com.example.android2dtest.gameLogic.myECS.components.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.components.TextRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

public class TicTacToe extends GameScene {
    public boolean xTurn = true;
    Tile[][] tiles = new Tile[3][3];
    private TextRenderer turnText;

    public TicTacToe(Context context) {
        super(context);
    }

    @Override
    public void start() {
        setBackgroundColor(Color.BLUE);
        setBackgroundImage(contentManager.loadTextureFromDrawable(R.drawable.board_background));

        GameEntity board = new GameEntity("board");
        addEntity(board);
        board.setPosition(getSurfaceCenter());
        board.addComponent(new SpriteRenderer(new Sprite(contentManager.loadTextureFromDrawable(R.drawable.tic_tac_toe))));
        board.addComponent(new TextRenderer("Tic Tac Toe"));
        board.getComponent(TextRenderer.class).paint.setTextSize(300);
        board.getComponent(TextRenderer.class).setOffset(0,-750);

        turnText = new TextRenderer("X's Turn");
        turnText.paint.setTextSize(200);
        GameEntity turnEntity = new GameEntity("turn");
        turnEntity.addComponent(turnText);
        turnEntity.setPosition(getSurfaceCenter().x, getSurfaceCenter().y + 780);
        addEntity(turnEntity);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                tiles[y][x] = new Tile("tile", new Point(x, y));
                addEntity(tiles[y][x]);
            }
        }
    }

    private void checkWin() {
        String winner = getWinner();
        if (winner != null) {
            turnText.setDrawText(winner + " Wins!");
            disableTiles();
        } else if (isBoardFull()) {
            turnText.setDrawText("It's a Draw!");
        } else {
            turnText.setDrawText(xTurn ? "X's Turn" : "O's Turn");
        }
    }

    private String getWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (tiles[i][0].type != null && tiles[i][0].type.equals(tiles[i][1].type) && tiles[i][0].type.equals(tiles[i][2].type)) {
                return tiles[i][0].type;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (tiles[0][i].type != null && tiles[0][i].type.equals(tiles[1][i].type) && tiles[0][i].type.equals(tiles[2][i].type)) {
                return tiles[0][i].type;
            }
        }

        // Check diagonals
        if (tiles[0][0].type != null && tiles[0][0].type.equals(tiles[1][1].type) && tiles[0][0].type.equals(tiles[2][2].type)) {
            return tiles[0][0].type;
        }
        if (tiles[0][2].type != null && tiles[0][2].type.equals(tiles[1][1].type) && tiles[0][2].type.equals(tiles[2][0].type)) {
            return tiles[0][2].type;
        }

        return null;
    }

    private boolean isBoardFull() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (tiles[y][x].type == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableTiles() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                tiles[y][x].removeComponents(ClickableComponent.class);
            }
        }
    }

    public class Tile extends GameEntity {
        Point id;
        String type;

        TextRenderer renderer;
        BoxCollider collider;
        ClickableComponent clickableComponent;

        public Tile(String name, Point id) {
            super(name);
            this.id = id;

            collider = new BoxCollider(300, 300);
            addComponent(collider);

            setPosition(GameScene.getSurfaceCenter().x + ((id.x - 1) * 350), GameScene.getSurfaceCenter().y + ((id.y - 1) * 360));

            renderer = new TextRenderer("");
            renderer.paint.setTextSize(200);
            renderer.setOffset(0, 60);
            addComponent(renderer);
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);
            clickableComponent = new ClickableComponent(collider);
            clickableComponent.setOnClickListener((x, y) -> {
                if (!renderer.getDrawText().equals("")) {
                    return;
                }

                TicTacToe gameScene = (TicTacToe) scene;
                if (gameScene.xTurn) {
                    renderer.setDrawText("X");
                    type = "X";
                } else {
                    renderer.setDrawText("O");
                    type = "O";
                }

                gameScene.xTurn = !gameScene.xTurn;
                gameScene.checkWin();
            });
            addComponent(clickableComponent);
        }
    }
}