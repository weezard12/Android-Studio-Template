package com.example.android2dtest.gameLogic.myPhysics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.android2dtest.gameLogic.myPhysics.shapes.Circle;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

// PhysicsSystem.java
public class PhysicsSystem {
    public static final List<Collider> colliders = new ArrayList<>();

    // This will be called at the beginning of the game to initialize any colliders
    public void init() {
        // Initialization logic, if needed
    }

    // This is the main update loop that checks for collisions
    public static void update(float delta) {
        for (int i = 0; i < colliders.size(); i++) {
            Collider colliderA = colliders.get(i);
            List<Collider> collidedWith = new ArrayList<>();

            for (int j = i + 1; j < colliders.size(); j++) {
                Collider colliderB = colliders.get(j);

                if (colliderA.checkCollision(colliderB)) {
                    collidedWith.add(colliderB);
                }
            }

            // If there are collisions, notify the collider
            if (!collidedWith.isEmpty()) {
                colliderA.onCollide(collidedWith);
            }
        }
    }
    public static void debugRenderPhysics(Canvas canvas){

        Paint debugPaint = new Paint();
        debugPaint.setColor(Color.RED);

        for (Collider collider : PhysicsSystem.colliders) {
            Shape shape = collider.getCollisionShape();
            if(shape instanceof Circle){
                Circle circle = (Circle)shape;
                canvas.drawCircle(circle.center.x,circle.center.y,circle.radius,debugPaint);
            }

        }
    }

    // Adds a collider to the system
    public static void addCollider(Collider collider) {
        colliders.add(collider);
    }

    // Removes a collider from the system
    public static void removeCollider(Collider collider) {
        colliders.remove(collider);
    }
}

