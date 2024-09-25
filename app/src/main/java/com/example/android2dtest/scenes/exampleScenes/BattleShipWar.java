package com.example.android2dtest.scenes.exampleScenes;

import static com.example.android2dtest.gameLogic.MyDebug.log;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.myECS.components.touchable.ClickableComponent;
import com.example.android2dtest.gameLogic.myECS.components.touchable.DraggableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.GridPoints;
import com.example.android2dtest.gameLogic.myECS.components.renderable.BoxRenderer;
import com.example.android2dtest.gameLogic.myECS.components.renderable.RotatableSpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;
import com.example.android2dtest.gameLogic.myPhysics.shapes.Box;

import java.util.ArrayList;
import java.util.List;

public class BattleShipWar extends GameScene {

    public static int scale = 100;
    public static int boardStart = 500;
    public static GridPoints points;
    public static Tile[][] tiles;

    List<Submarine> submarines = new ArrayList<>();

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

        submarines.add(new Submarine(1,3,R.drawable.submarine1x3));
        submarines.add(new Submarine(1,4,R.drawable.submarine1x4));
        submarines.add(new Submarine(1,4,R.drawable.submarine1x4_2));
        submarines.add(new Submarine(1,5,R.drawable.submarine1x5));
        submarines.add(new Submarine(2,5,R.drawable.submarine2x5));
        submarines.add(new Submarine(1,6,R.drawable.submarine1x6));
        for (Submarine submarine : submarines) {
            addEntity(submarine);
            submarine.getTransform().scale = 0.5f;
        }
        //Submarine submarine1x3 = new Submarine(1,3,R.drawable.submarine1x3);
        //addEntity(submarine1x3);
        //submarine1x3.setPosition(points.getGrid()[9][9].x + scale*1.6f, 600);

        //Submarine submarine1x4 = new Submarine(1,4,R.drawable.submarine1x4);
        //addEntity(submarine1x4);
        //submarine1x4.setPosition(points.getGrid()[9][9].x + scale*1.6f, 600);

        //Submarine submarine1x5 = new Submarine(1,5,R.drawable.submarine1x5);
        //addEntity(submarine1x5);
        //submarine1x5.setPosition(points.getGrid()[9][9].x + scale*1.6f, 600);

/*        Submarine submarine2x5 = new Submarine(2,5,R.drawable.submarine2x5);
        addEntity(submarine2x5);
        submarine2x5.setPosition(points.getGrid()[9][9].x + scale * 1.6f, 600);*/
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    public class Submarine extends GameEntity implements DraggableComponent.OnStopDraggingListener, ClickableComponent.OnClickListener {

        RotatableSpriteRenderer renderer;
        Bitmap texture;
        BoxCollider collider;
        DraggableComponent draggableComponent;
        ClickableComponent clickableComponent;

        // properties
        Point dimensions;
        boolean isRotated;

        private Point currentPoint;

        public Submarine(int x, int y, int drawable){
            this(new Point(x, y),contentManager.loadTextureFromDrawable(drawable));
        }

        public Submarine(Point dimensions, Bitmap texture){
            super("submarine");

            this.dimensions = dimensions;
            this.texture = texture;
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);

            renderer = new RotatableSpriteRenderer(texture);
            addComponent(renderer);

            collider = new BoxCollider(scale * dimensions.x,scale * dimensions.y);
            addComponent(collider);

            PointF newOffset = new PointF(0,0);
            if(dimensions.y % 2 == 0)
                newOffset.y = scale * 0.5f;

            if(dimensions.x % 2==0)
                newOffset.x = scale * 0.5f;

            renderer.setOffset(newOffset);
            collider.getCollisionShape().offset = newOffset;

            draggableComponent = new DraggableComponent(getComponent(BoxCollider.class));
            addComponent(draggableComponent);
            draggableComponent.setOnStopDraggingListener(this);
            draggableComponent.setOnStartDraggingListener(this);

            clickableComponent = new ClickableComponent(collider,300);
            clickableComponent.setOnClickListener(new ClickableComponent.OnClickListener() {
                @Override
                public void onClick(float x, float y) {
                    updateCollider();
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

        private void updateCollider(){
            setSubmarineTiles(false);
            isRotated = !isRotated;
            ((Box)collider.getCollisionShape()).width = (isRotated ? dimensions.y : dimensions.x) * scale;
            ((Box)collider.getCollisionShape()).height = (isRotated ? dimensions.x : dimensions.y) * scale;

            PointF offset = new PointF(
                    ((isRotated ? dimensions.y : dimensions.x) % 2 == 0 ? 0.5f : 0) * scale,
                    ((isRotated ? dimensions.x : dimensions.y) % 2 == 0 ? 0.5f : 0) * scale
            );
            renderer.setOffset(offset);
            collider.getCollisionShape().offset = offset;

            setSubmarineTiles(true);
        }

        @Override
        public void onStopDragging() {
            Point closestPoint = points.getClosestPointAsIdx(new Point((int)getY()-boardStart,(int)getX()));
            setPosition(tiles[closestPoint.y][closestPoint.x].getTransform().position);

            //tiles[closestPoint.y][closestPoint.x].setHasSubmarine(true);

            currentPoint = closestPoint;
            setSubmarineTiles(true);

            log(""+closestPoint);
        }

        @Override
        public void onClick(float x, float y) {
            if(currentPoint != null)
                setSubmarineTiles(false);
        }

        private void setSubmarineTiles(boolean addSubmarine) {
            int xStart = -(isRotated ? dimensions.y : dimensions.x) / 2;
            int xEnd = (isRotated ? dimensions.y : dimensions.x) / 2;
            int yStart = -(isRotated ? dimensions.x : dimensions.y) / 2;
            int yEnd = ((isRotated ? dimensions.x : dimensions.y) / 2);

            log("xStart: "+xStart+" xEnd: "+xEnd+" yStart: "+yStart+" yEnd: "+yEnd+"");
            if(dimensions.y % 2 == 0){
                if(!isRotated)
                    yStart += 1;
                else
                    xStart += 1;
            }
            if(dimensions.x % 2 == 0){
                if(!isRotated)
                    xStart += 1;
                else
                    yStart += 1;
            }

            for (int y = yStart; y <= yEnd; y++) {
                for (int x = xStart; x <= xEnd; x++) {
                    tiles[currentPoint.y + y][currentPoint.x + x].setHasSubmarine(addSubmarine);
                }
            }
        }
    }
    public class Tile extends GameEntity {

        Point idx;
        private boolean hasSubmarine;

        BoxCollider collider;
        BoxRenderer submarineTileRenderer;

        public Tile(Point idx) {
            super("tile");

            this.idx = idx;
        }

        @Override
        public void attachToScene(GameScene scene) {
            super.attachToScene(scene);

            collider = new BoxCollider(scale,scale);
            addComponent(collider);

            submarineTileRenderer = new BoxRenderer(scale, scale);
            submarineTileRenderer.setEnabled(false);
            addComponent(submarineTileRenderer);
        }

        public void setHasSubmarine(boolean hasSubmarine){
            this.hasSubmarine = hasSubmarine;
            if(hasSubmarine){
                submarineTileRenderer.setEnabled();
            }
            else submarineTileRenderer.setEnabled(false);
        }
    }
}
