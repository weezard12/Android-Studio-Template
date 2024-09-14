package com.example.android2dtest.gameLogic.extraComponents.touch;

import static com.example.android2dtest.gameLogic.MyDebug.log;

import com.example.android2dtest.gameLogic.myECS.components.GameComponent;
import com.example.android2dtest.gameLogic.myPhysics.Collider;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Shape;
import android.view.MotionEvent;

public class ClickableComponent extends TouchBase {
    private OnClickListener onClickListener; // Callback for click events

    public ClickableComponent(Collider collider) {
        super(collider);
    }
    public ClickableComponent(Shape collider) {
        super(collider);
    }

    // This method will be called whenever a touch event happens
    public void onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {// Detect if the click (touch) was inside the shape
            if (shape.collidesWithPoint(touchX, touchY)) {
                if (onClickListener != null) {
                    // Notify that the shape was clicked
                    onClickListener.onClick(event.getX(), event.getY());
                }
            }
        }
    }

    // Set the click listener (callback)
    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    // Callback interface for click events
    public interface OnClickListener {
        void onClick(float x, float y);
    }
}
