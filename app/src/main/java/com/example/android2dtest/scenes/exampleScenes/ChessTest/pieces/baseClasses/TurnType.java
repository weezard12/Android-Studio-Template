package com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses;

public enum TurnType {
    BLACK(-1),
    WHITE(1);

    private final int value;


    TurnType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
