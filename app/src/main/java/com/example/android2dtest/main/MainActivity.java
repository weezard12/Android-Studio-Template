package com.example.android2dtest.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;
import com.example.android2dtest.scenes.TestScenes.Flappy.FlappyBirdScene;
import com.example.android2dtest.scenes.TestScenes.TicTacToe;
import com.example.android2dtest.scenes.TestScenes.physicsExample.PhysicsExampleScene;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PhysicsSystem.init();
        //GameScene scene = new FlappyBirdScene(getApplicationContext());
        //GameScene scene = new PhysicsExampleScene(getApplicationContext());
        GameScene scene = new TicTacToe(getApplicationContext());

        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();
        setContentView(scene);

        //entities
        //scene.addEntity(new GameEntity("hello"));
    }
}