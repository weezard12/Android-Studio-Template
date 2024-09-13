package com.example.android2dtest.gameLogic.myPhysics;

import com.example.android2dtest.gameLogic.myECS.components.GameComponent;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;

import java.util.List;

public abstract class Collider extends GameComponent {
    public Shape getCollisionShape() {
        return collisionShape;
    }

    protected Shape collisionShape;
    public abstract void onCollide(List<Collider> collidesWith);


    public boolean checkCollision(Collider colliderB) {
        return this.collisionShape.collidesWith(colliderB.collisionShape);
    }

    @Override
    public void attachToEntity(GameEntity entity) {
        super.attachToEntity(entity);
        PhysicsSystem.addCollider(this);
        collisionShape.center = entity.getTransform().position;
    }
}
