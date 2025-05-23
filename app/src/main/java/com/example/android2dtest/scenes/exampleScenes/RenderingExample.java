package com.example.android2dtest.scenes.exampleScenes;

import android.content.Context;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.ContentManager;
import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.components.renderable.SpriteAnimation;
import com.example.android2dtest.gameLogic.myECS.components.renderable.SpriteAnimationRenderer;
import com.example.android2dtest.gameLogic.myECS.components.touchable.DraggableComponent;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.components.renderable.RotatableSpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.components.renderable.SpriteRenderer;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.BoxCollider;

public class RenderingExample extends GameScene {

    private GameEntity textureEntity;
    private GameEntity animationEntity;

    /**
     * WARNING when inheriting the class do not use the constructor scene init logic!
     * override the start method so you cant get null reference when creating entities at the scene start.
     *
     * @param context
     */
    public RenderingExample(Context context) {
        super(context);
    }

    @Override
    public void start() {
        super.start();
        debugRenderPhysics = true;

        SpriteRenderer renderer = new RotatableSpriteRenderer(contentManager.loadTextureFromDrawable(R.drawable.flappy_bird));
        renderer.getSprite().setScale(10,10);


        textureEntity = new GameEntity("texture");
        addEntity(textureEntity);

        textureEntity.setPosition(getSurfaceCenter());

        textureEntity.addComponent(new BoxCollider(100,100));
        textureEntity.addComponent(new DraggableComponent(textureEntity.getComponent(BoxCollider.class)));
        textureEntity.addComponent(renderer);


        SpriteAnimationRenderer animationRenderer = new SpriteAnimationRenderer();
        animationRenderer.setScale(10);
        animationRenderer.addAnimation("attack",new SpriteAnimation(ContentManager.loadSpriteSheetFrames(getContext(),R.drawable.solider_attack1,1,6,6),6, SpriteAnimation.LoopMode.LOOP));

        animationEntity = new GameEntity("animation");
        addEntity(animationEntity);

        animationEntity.setPosition(getSurfaceCenter());
        animationEntity.addComponent(new BoxCollider(100,100));
        animationEntity.addComponent(new DraggableComponent(textureEntity.getComponent(BoxCollider.class)));
        animationEntity.addComponent(animationRenderer);
        animationRenderer.playAnimation("attack");
    }

    @Override
    public void update() {
        super.update();
        textureEntity.getTransform().rotation += 1 * deltaTime;
        textureEntity.getTransform().scale += 1 * deltaTime;
        //log("delta"+GameLoop.deltaTime);
    }
}
