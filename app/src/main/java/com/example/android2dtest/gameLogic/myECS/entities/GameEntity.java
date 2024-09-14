package com.example.android2dtest.gameLogic.myECS.entities;

import android.graphics.Canvas;
import android.graphics.Point;

import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.Transform;
import com.example.android2dtest.gameLogic.myECS.components.GameComponent;
import com.example.android2dtest.gameLogic.myECS.components.RenderableComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEntity {
    String name;
    public GameScene scene;
    Transform transform;

    //transform shortcuts
    public float getX(){return transform.getX();}
    public float getY(){return transform.getY();}

    List<GameComponent> components;
    List<RenderableComponent> renderableComponents;
    public GameEntity(String name){
        this.name = name;
        transform = new Transform();
        components = new ArrayList<>();
        renderableComponents = new ArrayList<>();
    }
    public void render(float delta, Canvas canvas){
        for (RenderableComponent component : renderableComponents){
            component.render(delta,canvas);
        }
    }
    public void update(float delta){
        for (GameComponent component : components){
            component.update(delta);
        }
    }

    public void addComponent(GameComponent component){
        component.attachToEntity(this);
        if(component instanceof RenderableComponent) {
            renderableComponents.add((RenderableComponent) component);
            return;
        }
        components.add(component);
    }
    public <T extends GameComponent> T getComponent(Class<T> componentClass) {
        for (GameComponent component : components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        for (RenderableComponent renderableComponent : renderableComponents) {
            if (componentClass.isInstance(renderableComponent)) {
                return componentClass.cast(renderableComponent);
            }
        }
        return null;
    }

    public Transform getTransform(){return transform;}
    public void setPosition(float x, float y){
        transform.position.x = x;
        transform.position.y = y;
    }
    public void setPosition(Point position){
        transform.position.x = position.x;
        transform.position.y = position.y;
    }

    public void attachToScene(GameScene scene){
        this.scene = scene;
    }

    public void removeComponent(GameComponent component){
        component.detachFromEntity(this);

        if(component instanceof RenderableComponent)
            renderableComponents.remove(component);
        else
            components.remove(component);

    }
    public <T extends GameComponent> void removeComponents(Class<T> componentClass) {
        // Create a unified list of all components
        List<? extends GameComponent> targetList =
                RenderableComponent.class.isAssignableFrom(componentClass) ? renderableComponents : components;

        Iterator<? extends GameComponent> iterator = targetList.iterator();

        while (iterator.hasNext()) {
            GameComponent c = iterator.next();
            if (componentClass.isInstance(c)) {
                iterator.remove();
                c.detachFromEntity(this); // Ensure component is detached
                return; // Remove this line if you want to remove all matching components
            }
        }
    }
}
