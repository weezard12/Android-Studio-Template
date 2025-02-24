package com.example.android2dtest.scenes.exampleScenes.ChessTest.scenes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android2dtest.R;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.BoardColors;

public class ThemeSelectorView extends LinearLayout {

    BoardColors colors;
    String themeName;

    public ThemeSelectorView(Context context, BoardColors colors, String themeName) {
        super(context);
        this.colors = colors;
        this.themeName = themeName;
        init(context);
    }


    private void init(Context context) {
        // Inflate custom layout
        LayoutInflater.from(context).inflate(R.layout.sample_theme_selector_view, this, true);

        // Create a GradientDrawable
        // Find the inner layout that wraps the buttons
        LinearLayout contentLayout = findViewById(R.id.contentLayout);

        // Create a GradientDrawable with a limited size
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, // Gradient direction
                new int[]{colors.black.toArgb(), colors.white.toArgb()} // Blue to Purple
        );
        gradientDrawable.setCornerRadius(16); // Optional: Rounded corners

        // Set the drawable as background only for the content layout
        contentLayout.setBackground(gradientDrawable);

        // Find views (colored squares)
        View color1 = findViewById(R.id.themeColor1);
        View color2 = findViewById(R.id.themeColor2);
        View color3 = findViewById(R.id.themeColor3);

        // Handle click events for each theme option
        color1.setOnClickListener(v -> selectTheme(context));
        color1.setBackgroundColor(colors.white.toArgb());

        color2.setOnClickListener(v -> selectTheme(context));
        color2.setBackgroundColor(colors.black.toArgb());

        color3.setOnClickListener(v -> selectTheme(context));
        color3.setBackgroundColor(colors.movesHighlightColor.toArgb());
    }

    private void selectTheme(Context context) {
        Toast.makeText(context, "Theme Selected: " + themeName, Toast.LENGTH_SHORT).show();
    }
}