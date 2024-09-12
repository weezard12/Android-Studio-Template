package com.example.android2dtest.gameLogic.myECS;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceView;

public class GameScene extends SurfaceView implements Drawable.Callback {

    public GameScene(Context context) {
        super(context);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //log("aaa");
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(1,1,100,paint);
        canvas.drawText("TEST",100,100, paint);
    }
}
