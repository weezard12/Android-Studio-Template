package com.example.android2dtest.gameLogic.myECS;

import android.graphics.PointF;

import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;
import com.example.android2dtest.gameLogic.myPhysics.Collider;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Box;

import java.util.ArrayList;

public class Panel {
    ArrayList<GameEntity> entities;
    PointF startPos;

    public Panel(PointF startPos){
        this.startPos = startPos;
        this.entities = new ArrayList<>();
    }
    public void add(GameEntity entity){
        entities.add(entity);
        arrangeEntitiesVertically();
    }
    public void remove(GameEntity entity){
        entities.remove(entity);
        arrangeEntitiesVertically();
    }
    private void arrangeEntitiesVertically(){
        float currentY = startPos.y;
        for (GameEntity entity : entities) {
            if (entity != null) {
                entity.setPosition(startPos.x,currentY);
                Collider collider = entity.getComponent(BoxCollider.class);
                if (collider != null) {
                    currentY += ((Box) collider.getCollisionShape()).height;
                }
            }
        }
    }
}