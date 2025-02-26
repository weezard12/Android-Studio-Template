package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android2dtest.R;
import com.example.android2dtest.gameLogic.MyDebug;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.GameBoard;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes.base.ChessHotSit;

public class ChessHotSitActivity extends AppCompatActivity implements View.OnClickListener {

    Button backButton;
    Button rotateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chess_hot_sit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        rotateButton = findViewById(R.id.rotateBoardButton);
        rotateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.equals(backButton)){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        else if(v.equals(rotateButton)){
            GameBoard.currentActiveBoard.isBlackRotationBoard = !GameBoard.currentActiveBoard.isBlackRotationBoard;
            GameBoard.currentActiveBoard.createTiles(GameBoard.currentActiveBoard.isBlackRotationBoard);
        }

    }
}