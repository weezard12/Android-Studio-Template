package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android2dtest.R;
import com.example.android2dtest.main.MyUtils;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.BoardColors;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout themesLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        themesLayout = findViewById(R.id.themesLayout);


        themesLayout.addView(new ThemeSelectorView(this, new BoardColors(Color.WHITE,Color.BLACK,Color.BLUE,Color.CYAN),"Default"));
        themesLayout.addView(new ThemeSelectorView(this,
                new BoardColors(MyUtils.rgbToFloatRgb(235,236,208), MyUtils.rgbToFloatRgb(115,149,82), Color.valueOf(Color.BLUE), Color.valueOf(Color.CYAN)),
                "Chess.com"));

        themesLayout.addView(new ThemeSelectorView(this,
                new BoardColors(MyUtils.rgbToFloatRgb(237,214,176), MyUtils.rgbToFloatRgb(184,135,98), Color.valueOf(Color.BLUE), Color.valueOf(Color.CYAN)),
                "Brown"));

        themesLayout.addView(new ThemeSelectorView(this,
                new BoardColors(MyUtils.rgbToFloatRgb(240,241,240), MyUtils.rgbToFloatRgb(196,216,228), Color.valueOf(Color.BLUE), Color.valueOf(Color.CYAN)),
                "Sky"));

        themesLayout.addView(new ThemeSelectorView(this,
                new BoardColors(MyUtils.rgbToFloatRgb(139,138,136), MyUtils.rgbToFloatRgb(105,104,102), Color.valueOf(Color.BLUE), Color.valueOf(Color.CYAN)),
                "Clear"));

        themesLayout.addView(new ThemeSelectorView(this,
                new BoardColors(MyUtils.rgbToFloatRgb(216,217,216), MyUtils.rgbToFloatRgb(168,169,168), Color.valueOf(Color.BLUE), Color.valueOf(Color.CYAN)),
                "Light"));

        themesLayout.addView(new ThemeSelectorView(this,
                new BoardColors(MyUtils.rgbToFloatRgb(237,203,165), MyUtils.rgbToFloatRgb(216,164,109), Color.valueOf(Color.BLUE), Color.valueOf(Color.CYAN)),
                "Light Brown"));


    }
}