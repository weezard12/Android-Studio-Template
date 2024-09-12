package com.example.android2dtest.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.scenes.TestScene;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestScene scene = new TestScene(getApplicationContext());

        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();

        setContentView(scene);
    }
}