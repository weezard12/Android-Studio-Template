package com.example.android2dtest.scenes.exampleScenes.flappyBird;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.Sprite;
import com.example.android2dtest.gameLogic.myECS.components.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

public class Pipe extends GameEntity {
    private SpriteRenderer spriteRenderer;
    private BoxCollider collider;
    private boolean isTop;
    public Pipe(String name, int gap,boolean isTop) {
        super(name);
        this.isTop = isTop;
        setPosition(GameScene.getScreenEnd());
    }

    @Override
    public void attachToScene(GameScene scene) {
        super.attachToScene(scene);


        spriteRenderer = new SpriteRenderer(new Sprite(scene.contentManager.loadTextureFromDrawable(R.drawable.flappy_bird_pipe)));
        collider = new BoxCollider(200,200);

        addComponent(spriteRenderer);
        addComponent(collider);

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setPosition(getX()-delta*10,getY());

    }
}
