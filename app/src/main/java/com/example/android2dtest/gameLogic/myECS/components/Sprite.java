package com.example.android2dtest.gameLogic.myECS.components;

import android.graphics.Bitmap;
import android.graphics.PointF;

public class Sprite {
    Bitmap texture;
    PointF scale;
    public Sprite(Bitmap texture){
        this.texture = texture;
    }
    public Sprite(Bitmap texture, float scaleX, float scaleY){
        this.texture = texture;
        this.scale = new PointF(scaleX,scaleY);
    }
}
