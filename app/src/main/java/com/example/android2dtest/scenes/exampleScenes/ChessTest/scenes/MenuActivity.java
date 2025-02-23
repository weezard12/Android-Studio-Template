package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android2dtest.R;

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
    }


    @Override
    public void onClick(View v) {
        if(logoButton.equals(v)){

        }
        else if(hotSitButton.equals(v)){

        }
        else if(botButton.equals(v)){

        }
        else if(settingsButton.equals(v)){

        }
        else if(quitButton.equals(v)){

        }
    }
}