package com.example.android2dtest.scenes.exampleScenes.ChessTest.board;

import android.graphics.Color;

public class BoardColors {
    public Color white;
    public Color black;
    public Color selectedTile;
    public Color movesHighlightColor;
    public BoardColors(Color white, Color black, Color selectedTile,Color movesHighlightColor){
        this.white = white;
        this.black = black;
        this.selectedTile = selectedTile;
        this.movesHighlightColor = movesHighlightColor;
    }
}
