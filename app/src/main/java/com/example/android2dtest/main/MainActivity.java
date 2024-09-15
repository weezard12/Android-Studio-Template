package com.example.android2dtest.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;
import com.example.android2dtest.scenes.exampleScenes.RenderingExample;
import com.example.android2dtest.scenes.exampleScenes.TicTacToe;
import com.example.android2dtest.scenes.exampleScenes.flappyBird.FlappyBirdScene;
import com.example.android2dtest.scenes.exampleScenes.physicsExample.PhysicsExampleScene;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PhysicsSystem.init();
        //GameScene scene = new FlappyBirdScene(getApplicationContext());
        //GameScene scene = new PhysicsExampleScene(getApplicationContext());
        //GameScene scene = new TicTacToe(getApplicationContext());
        GameScene scene = new RenderingExample(getApplicationContext());
        setContentView(scene);
        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();

        //entities
        //scene.addEntity(new GameEntity("hello"));
    }
}