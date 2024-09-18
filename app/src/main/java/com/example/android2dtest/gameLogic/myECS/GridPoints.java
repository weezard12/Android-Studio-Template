package com.example.android2dtest.gameLogic.myECS;

import android.graphics.Point;

public class GridPoints extends Grid<Point>{

    public GridPoints(int rows, int columns, int rowDistance, int columnDistance) {
        super(rows, columns, rowDistance, columnDistance);
        grid = new Point[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                grid[y][x] = new Point(x * rowDistance,y * columnDistance);
            }
        }
    }

    @Override
    public void updateGrid(int rowDistance, int columnDistance) {
        // TODO add update grid logic.
    }

}
