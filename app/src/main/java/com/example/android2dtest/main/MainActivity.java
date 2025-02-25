package com.example.android2dtest.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.MusicManager;
import com.example.android2dtest.gameLogic.myPhysics.PhysicsSystem;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes.MenuActivity;

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
        //GameScene scene = new BattleShipWar(getApplicationContext());
        //GameScene scene = new SwordThrow(getApplicationContext());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Request window without title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

/*        // Hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GameScene scene = new ChessHotSit(getApplicationContext());
        scene.setFullScreen();


        setContentView(scene);
        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();*/



        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);

        MusicManager musicManager = MusicManager.getInstance(this);
        musicManager.loadMusic(this, R.raw.background_music);
        musicManager.playMusic();
    }
}