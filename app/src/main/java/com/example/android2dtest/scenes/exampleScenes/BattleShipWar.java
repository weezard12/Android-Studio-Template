package com.example.android2dtest.scenes.exampleScenes;

import static com.example.android2dtest.gameLogic.MyDebug.log;

import android.content.Context;
import android.graphics.Point;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.extraComponents.touch.ClickableComponent;
import com.example.android2dtest.gameLogic.extraComponents.touch.DraggableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.GridPoints;
import com.example.android2dtest.gameLogic.myECS.components.renderable.RotatableSpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.components.renderable.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

public class BattleShipWar extends GameScene {

    public static int scale = 100;
    public static int boardStart = 500;
    public static GridPoints points;
    public static Tile[][] tiles;

    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public BattleShipWar(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();

        debugRenderPhysics = true;

        scale = getScreenEnd().x / 12;

        points = new GridPoints(10, 10, scale, scale);
        tiles = new Tile[10][10];
        for (int y = 0; y < points.getGrid().length; y++) {
            for (int x = 0; x < points.getGrid()[0].length; x++) {
                Tile entity = new Tile(new Point(x, y));
                entity.setPosition(points.getGrid()[y][x].x + (scale / 2), points.getGrid()[y][x].y + 500);
                addEntity(entity);

                //adds to the array
                tiles[y][x] = entity;
            }
        }

        Submarine submarine1 = new Submarine("submarine");
        addEntity(submarine1);
        submarine1.setPosition(points.getGrid()[9][9].x + scale*1.6f, 600);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    public class Submarine extends GameEntity{

        RotatableSpriteRenderer renderer;
        BoxCollider collider;
        DraggableComponent draggableComponent;
        ClickableComponent clickableComponent;

        // properties
        Point dimensions;
        boolean isRotated;

        public Submarine(String name) {
            super(name);
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);

            renderer = new RotatableSpriteRenderer(contentManager.loadTextureFromDrawable(R.drawable.submarine1));
            addComponent(renderer);

            collider = new BoxCollider(scale,scale * 4);
            addComponent(collider);

            draggableComponent = new DraggableComponent(getComponent(BoxCollider.class));
            addComponent(draggableComponent);
            draggableComponent.setOnStopDraggingListener( new DraggableComponent.OnStopDraggingListener() {
                @Override
                public void onStopDragging() {
                    Point closestPoint = points.getClosestPointAsIdx(new Point((int)getY()-boardStart,(int)getX()));
                    log(""+closestPoint);
                    setPosition(tiles[closestPoint.y][closestPoint.x].getTransform().position);
                }
            });

            clickableComponent = new ClickableComponent(collider);
            clickableComponent.setOnClickListener(new ClickableComponent.OnClickListener() {
                @Override
                public void onClick(float x, float y) {
                    if(draggableComponent.)
                    isRotated = !isRotated;
                    if(isRotated){
                        getTransform().rotation = 90;
                    }
                    else {
                        getTransform().rotation = 0;
                    }
                }
            });
            addComponent(clickableComponent);
        }
    }
    public class Tile extends GameEntity {

        Point idx;
        BoxCollider collider;

        public Tile(Point idx) {
            super("tile");

            this.idx = idx;
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);

            collider = new BoxCollider(scale,scale);
            addComponent(collider);
        }
    }
}
