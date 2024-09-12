package com.example.android2dtest.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.entities.GameEntity;
import com.example.android2dtest.scenes.TestScenes.Flappy.FlappyBirdScene;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FlappyBirdScene scene = new FlappyBirdScene(getApplicationContext());

        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();

        setContentView(scene);

        //entities
        //scene.addEntity(new GameEntity("hello"));
    }
}