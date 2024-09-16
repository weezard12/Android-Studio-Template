package com.example.android2dtest.scenes.exampleScenes;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;

import com.example.android2dtest.gameLogic.extraComponents.touch.ClickableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.GridPoints;
import com.example.android2dtest.gameLogic.myECS.components.TextRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame extends GameScene {

    public static int scale = 250;
    private Tile selectedTile;
    private int score = 0;
    private int turns = 0;
    private int pairsFound = 0;
    private TextRenderer scoreText;
    private TextRenderer turnText;
    private Handler handler = new Handler();

    public MemoryGame(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();
        debugRenderPhysics = true;

        int[][] mat = new int[4][5];
        fillMat(mat);

        GridPoints points = new GridPoints(4, 5, scale, scale);
        for (int y = 0; y < points.getGrid().length; y++) {
            for (int x = 0; x < points.getGrid()[0].length; x++) {
                Tile entity = new Tile(mat[y][x], new Point(x, y));
                entity.setPosition(points.getGrid()[y][x].x + (scale / 2), points.getGrid()[y][x].y + 400);
                addEntity(entity);
            }
        }

        // Score Text
        scoreText = new TextRenderer("Score: 0");
        scoreText.paint.setTextSize(100);
        GameEntity scoreEntity = new GameEntity("score");
        scoreEntity.addComponent(scoreText);
        scoreEntity.setPosition(getSurfaceCenter().x, 100);
        addEntity(scoreEntity);

        // Turn Text
        turnText = new TextRenderer("Turns: 0");
        turnText.paint.setTextSize(100);
        GameEntity turnEntity = new GameEntity("turn");
        turnEntity.addComponent(turnText);
        turnEntity.setPosition(getSurfaceCenter().x, 250);
        addEntity(turnEntity);
    }

    public static void fillMat(int[][] mat) {
        int size = mat.length * mat[0].length;
        if (size % 2 != 0) {
            throw new IllegalArgumentException("Matrix size must be even for a memory game.");
        }

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            numbers.add(i);
            numbers.add(i); // Add each number twice for pairs
        }

        Collections.shuffle(numbers); // Shuffle the numbers randomly

        int index = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = numbers.get(index++);
            }
        }
    }

    class Tile extends GameEntity {

        TextRenderer renderer;
        ClickableComponent clickableComponent;
        int number;
        boolean isRevealed = false;

        public Tile(int number, Point idx) {
            super("tile");
            this.number = number;
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);
            renderer = new TextRenderer("?");
            renderer.setOffset(0, scale / 8);
            renderer.paint.setTextSize(scale / 1.5f);
            addComponent(renderer);

            addComponent(new BoxCollider(scale, scale));
            clickableComponent = new ClickableComponent(getComponent(BoxCollider.class));
            clickableComponent.setOnClickListener((x, y) -> {
                if (isRevealed || this == selectedTile) return;

                renderer.setDrawText("" + number);
                isRevealed = true;

                if (selectedTile == null) {
                    selectedTile = this;
                } else {
                    turns++;
                    turnText.setDrawText("Turns: " + turns);
                    if (selectedTile.number == number) {
                        score += 10; // Increment score for a match
                        scoreText.setDrawText("Score: " + score);
                        pairsFound++;
                        selectedTile = null;
                        checkWin();
                    } else {
                        // Delay to show the incorrect pair
                        handler.postDelayed(() -> {
                            selectedTile.renderer.setDrawText("?");
                            selectedTile.isRevealed = false;
                            renderer.setDrawText("?");
                            isRevealed = false;
                            selectedTile = null;
                        }, 1000); // 1 second delay
                    }
                }
            });
            addComponent(clickableComponent);
        }
    }

    private void checkWin() {
        if (pairsFound == 10) { // Adjust based on your grid size
            // Handle win condition
            TextRenderer winText = new TextRenderer("You Win!");
            winText.paint.setTextSize(200);
            GameEntity winEntity = new GameEntity("win");
            winEntity.addComponent(winText);
            winEntity.setPosition(getSurfaceCenter().x, getSurfaceCenter().y+200);
            addEntity(winEntity);
        }
    }
}