package com.example.android2dtest.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.myECS.GameScene;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;
import com.example.android2dtest.scenes.exampleScenes.BattleShipWar;
import com.example.android2dtest.scenes.exampleScenes.MemoryGame1Player;
import com.example.android2dtest.scenes.exampleScenes.MemoryGame2Players;
import com.example.android2dtest.scenes.exampleScenes.RenderingExample;
import com.example.android2dtest.scenes.exampleScenes.SwordThrow;
import com.example.android2dtest.scenes.exampleScenes.TicTacToe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PhysicsSystem.init();
        //GameScene scene = new FlappyBirdScene(getApplicationContext());
        //GameScene scene = new PhysicsExampleScene(getApplicationContext());
        //GameScene scene = new TicTacToe(getApplicationContext());
        //GameScene scene = new RenderingExample(getApplicationContext());
        //GameScene scene = new MemoryGame1Player(getApplicationContext());
        //GameScene scene = new MemoryGame2Players(getApplicationContext());
        GameScene scene = new BattleShipWar(getApplicationContext());
        //GameScene scene = new SwordThrow(getApplicationContext());

        setContentView(scene);
        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();

        //Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        //startActivity(intent);
    }
}