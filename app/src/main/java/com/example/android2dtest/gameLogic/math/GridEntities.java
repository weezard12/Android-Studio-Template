package com.example.android2dtest.gameLogic.math;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import com.example.android2dtest.gameLogic.myECS.components.GameComponent;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myECS.components.touchable.ClickableComponent;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

import java.util.ArrayList;
import java.util.List;

public class GridEntities extends Grid<GameEntity> {

    private final int rowDistance;
    private final int columnDistance;
    private final List<GameEntity> entityList;
    private PointF position; // Center of the grid
    private OnClickListener onClickListener;

    public GridEntities(int rows, int columns, int rowDistance, int columnDistance) {
        super(rows, columns, rowDistance, columnDistance);
        this.rowDistance = rowDistance;
        this.columnDistance = columnDistance;
        this.grid = new GameEntity[rows][columns];
        this.entityList = new ArrayList<>();
        this.position = new PointF(0, 0); // Default position at (0,0)
    }

    /**
     * Sets the center position of the grid.
     * Entities will align based on this position.
     */
    public void setPosition(float x, float y) {
        this.position.set(x, y);
        realignEntities();
    }

    public PointF getPosition() {
        return new PointF(position.x, position.y);
    }

    @Override
    public void updateGrid(int newRowDistance, int newColumnDistance) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != null) {
                    float newX = position.x + col * newColumnDistance;
                    float newY = position.y + row * newRowDistance;
                    grid[row][col].setPosition(newX, newY);
                }
            }
        }
    }

    public void addEntity(int row, int column, GameEntity entity) {
        if (isValidPosition(row, column)) {
            grid[row][column] = entity;
            entity.setPosition(position.x + column * columnDistance, position.y + row * rowDistance);
            entityList.add(entity);
        }
    }

    public void setupBoardWithNewEntities() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new GameEntity();
            }
        }
    }

    public GameEntity getEntity(int row, int column) {
        return isValidPosition(row, column) ? grid[row][column] : null;
    }

    public void removeEntity(int row, int column) {
        if (isValidPosition(row, column)) {
            entityList.remove(grid[row][column]);
            grid[row][column] = null;
        }
    }

    public void clearBoard() {
        entityList.clear();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = null;
            }
        }
    }

    /**
     * Aligns all entities based on the grid's center position.
     */
    public void realignEntities() {
        // Calculate total width and height of the grid
        float gridWidth = grid[0].length * columnDistance;
        float gridHeight = grid.length * rowDistance;

        // Compute the top-left starting point so the grid is centered around 'position'
        float startX = position.x - (gridWidth / 2);
        float startY = position.y - (gridHeight / 2);

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != null) {
                    float newX = startX + col * columnDistance;
                    float newY = startY + row * rowDistance;
                    grid[row][col].setPosition(newX, newY);
                }
            }
        }
    }


    public Point getClosestEntityIndex(Point point) {
        Point closestPointIdx = new Point(0, 0);
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    double distance = calculateDistance(point, new Point(
                            (int) (position.x + j * columnDistance),
                            (int) (position.y + i * rowDistance)
                    ));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestPointIdx = new Point(i, j);
                    }
                }
            }
        }
        return closestPointIdx;
    }

    public void setGridClickable() {
        setGridClickable(columnDistance, rowDistance);
    }
    public void setGridClickable(int boxWidth, int boxHeight) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                GameEntity entity = grid[row][col];
                if (entity != null) {
                    BoxCollider collider = new BoxCollider(boxWidth, boxHeight);
                    ClickableComponent clickable = new ClickableComponent(collider);

                    entity.addComponent(collider);
                    entity.addComponent(clickable);

                    final int finalRow = row;
                    final int finalCol = col;
                    clickable.setOnClickListener(new ClickableComponent.OnClickListener() {
                        @Override
                        public void onClick(float x, float y) {
                            onClickListener.onClick(finalRow, finalCol);
                        }
                    });
                }
            }
        }
    }



    public void addComponentToAll(GameComponent component) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                GameEntity entity = grid[row][col];
                if (entity != null) {
                    // entity.addComponent(component.clone());
                }
            }
        }
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }


    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int row, int column);
    }
}
