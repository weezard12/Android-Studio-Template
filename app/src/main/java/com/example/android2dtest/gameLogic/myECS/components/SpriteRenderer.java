package com.example.android2dtest.gameLogic.myECS.components;

import static com.example.android2dtest.gameLogic.MyDebug.log;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

public class SpriteRenderer extends RenderableComponent {

    private Sprite sprite;
    private Paint paint;

    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
        this.paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta, Canvas canvas) {
        super.render(delta, canvas);

        if (sprite == null || sprite.texture == null) {
            log("Sprite renderer error: sprite or texture are null");
            return;  // No sprite or texture to render
        }

        // Retrieve the entity's transform (position)
        float entityX = entity.getTransform().position.x;
        float entityY = entity.getTransform().position.y;

        // Get the bitmap to render
        Bitmap texture = sprite.texture;

        // Handle scaling: if sprite has scale set, apply it, otherwise default to 1.0
        float scaleX = (sprite.scale != null) ? sprite.scale.x : 1.0f;
        float scaleY = (sprite.scale != null) ? sprite.scale.y : 1.0f;

        // Calculate the source and destination rectangles
        RectF destinationRect = new RectF(
                entityX,
                entityY,
                entityX + (texture.getWidth() * scaleX),
                entityY + (texture.getHeight() * scaleY)
        );
        // Draw the bitmap on the canvas, applying the scaling and positioning
        canvas.drawBitmap(texture, null, destinationRect, paint);
    }
}
