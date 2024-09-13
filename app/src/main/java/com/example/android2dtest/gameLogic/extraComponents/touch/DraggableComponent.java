package com.example.android2dtest.gameLogic.extraComponents.touch;

import com.example.android2dtest.gameLogic.myECS.components.GameComponent;
import com.example.android2dtest.gameLogic.myPhysics.Collider;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Shape;
import android.view.MotionEvent;

public class DraggableComponent extends TouchBase {
    private boolean isDragging = false;    // To check if we are currently dragging
    private float lastTouchX;              // To track the last X touch position
    private float lastTouchY;              // To track the last Y touch position

    public DraggableComponent(Collider collider) {
        super(collider);
    }
    public DraggableComponent(Shape collider) {
        super(collider);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // Handle dragging logic here (based on input)
        if (isDragging) {
            // Move the entity according to input delta
            // Assuming that entity has a position that can be updated
            entity.setPosition(entity.getX() + getTouchDeltaX(),entity.getY() + getTouchDeltaY());

            // Update the last touch coordinates
            lastTouchX = getCurrentTouchX();
            lastTouchY = getCurrentTouchY();
        }
    }

    // This method will be called whenever a touch event happens
    public void onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // If the touch is inside the shape's boundary, start dragging
                if (shape.collidesWithPoint(touchX, touchY)) {
                    isDragging = true;
                    lastTouchX = touchX;
                    lastTouchY = touchY;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                // If already dragging, update the touch position
                if (isDragging) {
                    float deltaX = touchX - lastTouchX;
                    float deltaY = touchY - lastTouchY;

                    // Update entity position
                    entity.setPosition(entity.getX()+deltaX,entity.getY()+deltaY);

                    // Update last touch coordinates
                    lastTouchX = touchX;
                    lastTouchY = touchY;
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Stop dragging when touch is released or cancelled
                isDragging = false;
                break;
        }
    }

    // Helper functions to get the current touch position (depends on platform)
    private float getCurrentTouchX() {
        // This method should return the current X coordinate of the touch
        // You can adapt it to your input system
        return lastTouchX;
    }

    private float getCurrentTouchY() {
        // This method should return the current Y coordinate of the touch
        // You can adapt it to your input system
        return lastTouchY;
    }

    private float getTouchDeltaX() {
        // Calculate how much the X position has changed
        return getCurrentTouchX() - lastTouchX;
    }

    private float getTouchDeltaY() {
        // Calculate how much the Y position has changed
        return getCurrentTouchY() - lastTouchY;
    }
}
