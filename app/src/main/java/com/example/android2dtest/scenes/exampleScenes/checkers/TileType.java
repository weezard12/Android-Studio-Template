package com.example.android2dtest.scenes.exampleScenes.checkers;

public enum TileType {
    EMPTY(' '),
    BLACK('b'),
    WHITE('w'),
    BLACK_KING('B'),
    WHITE_KING('W'),
    HIGHLIGHT('h');

    public final char typeAsLetter;

    TileType(char c) {
        this.typeAsLetter = c;
    }

    public static TileType fromChar(char c) {
        for (TileType type : TileType.values()) {
            if (type.typeAsLetter == c) {
                return type;
            }
        }
        return null; // Return null if no matching TileType is found
    }
}