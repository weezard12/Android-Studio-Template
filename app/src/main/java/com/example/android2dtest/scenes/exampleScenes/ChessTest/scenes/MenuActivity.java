package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.GameLoop;
import com.example.android2dtest.gameLogic.MusicManager;
import com.example.android2dtest.gameLogic.myECS.GameScene;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton logoButton;
    Button hotSitButton;
    Button botButton;
    Button settingsButton;
    Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        logoButton = (ImageButton) findViewById(R.id.logo_image_button);
        hotSitButton = (Button) findViewById(R.id.btn_play_hot_seat);
        botButton = (Button) findViewById(R.id.btn_play_vs_bot);
        settingsButton = (Button) findViewById(R.id.btn_settings);
        quitButton = (Button) findViewById(R.id.btn_quit);

        logoButton.setOnClickListener(this);
        hotSitButton.setOnClickListener(this);
        botButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(logoButton.equals(v)){

        }
        else if(hotSitButton.equals(v)){
        // Hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

/*        GameScene scene = new ChessHotSit(getApplicationContext());
        scene.setFullScreen();


        setContentView(scene);
        GameLoop gameLoop = new GameLoop(scene);
        gameLoop.start();*/
            Intent intent = new Intent(this, ChessHotSitActivity.class);
            startActivity(intent);
        }
        else if(botButton.equals(v)){
/*            GameScene scene = new ChessVsBot(getApplicationContext());
            scene.setFullScreen();

            setContentView(scene);
            GameLoop gameLoop = new GameLoop(scene);
            gameLoop.start();*/
        }
        else if(settingsButton.equals(v)){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        else if(quitButton.equals(v)){
            finishAffinity(); // Closes all activities in the task
            System.exit(0);   // Optional, forces the app to exit
        }
    }
}