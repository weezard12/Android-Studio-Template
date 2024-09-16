package com.example.android2dtest.scenes.exampleScenes.flappyBird;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.renderable.Sprite;
import com.example.android2dtest.gameLogic.myECS.components.renderable.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.CircleCollider;

public class Bird extends GameEntity {

    private float speedY = 0;
    private CircleCollider collider;
    private SpriteRenderer renderer;


    public Bird(String name) {
        super(name);
    }

    @Override
    public void attachToScene(GameScene scene) {
        super.attachToScene(scene);
        //sets the position
        setPosition(100,100);

        collider = new CircleCollider(100);
        addComponent(collider);
        renderer = new SpriteRenderer(new Sprite(scene.contentManager.loadTextureFromDrawable(R.drawable.flappy_bird)));
        addComponent(renderer);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setPosition(getX(), getY() - speedY);
        speedY -= 0.8f * delta;
    }


    public void flap() {
        speedY = 40;
    }
}
