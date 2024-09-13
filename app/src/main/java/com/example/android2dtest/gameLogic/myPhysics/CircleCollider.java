package com.example.android2dtest.gameLogic.myPhysics;

import java.util.List;

public class CircleCollider extends Collider{

    public CircleCollider(float radius){
        collisionShape = new Circle(radius);
    }
    @Override
    public void onCollide(List<Collider> collidesWith) {

    }
}
