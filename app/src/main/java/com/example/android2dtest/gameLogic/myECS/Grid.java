package com.example.android2dtest.gameLogic.myECS;

import android.graphics.Point;

public abstract class Grid<T> {
    protected T[][] grid;

    public Grid(int rows, int columns, int rowDistance, int columnDistance){
    }

    public abstract void updateGrid(int rowDistance, int columnDistance);
    public T[][] getGrid(){return grid;}


}
