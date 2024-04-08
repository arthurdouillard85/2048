package com.example.a2048;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.a2048.databinding.ActivityJeuxBinding;

public class Jeux extends AppCompatActivity {
    private ActivityJeuxBinding binding;
    private Game2048 game;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJeuxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        game = new Game2048(4);
        updateUI();

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();

                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    if (distanceX > 0) {
                        game.moveDown();
                    } else {
                        game.moveUp();
                    }
                } else {
                    if (distanceY > 0) {
                        game.moveRight();
                    } else {
                        game.moveLeft();
                    }
                }
                updateUI();
                return true;
            }
        });

        binding.gridLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    private void updateUI() {
        Tile[][] grid = game.getGrid();
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                TextView textView = (TextView) binding.gridLayout.getChildAt(i * game.getSize() + j);
                Tile tile = grid[i][j];
                if (tile.getValue() == 0) {
                    textView.setText("");
                    textView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                } else {
                    textView.setText(String.valueOf(tile.getValue()));
                    textView.setBackgroundColor(ContextCompat.getColor(this, R.color.black));

                }
            }
        }
        binding.textViewScore.setText(getString(R.string.score) + " " + game.getScore());
        if (game.isGameOver()) {
            binding.gridLayout.setOnTouchListener(null);
        }
    }
}
