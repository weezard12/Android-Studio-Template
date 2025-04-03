package com.example.android2dtest.scenes.exampleScenes.checkers;

import android.graphics.Point;

public class Move {
    Point movingTo;
    TileType[][] position;

    public Move(Point movingTo, TileType[][] position) {
        this.movingTo = movingTo;
        this.position = position;
    }
    public Move(int x, int y, TileType[][] position) {
        this.movingTo = new Point(x, y);
        this.position = position;
    }

}
