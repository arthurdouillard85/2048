package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.a2048.databinding.ActivityJeuxBinding;

public class Jeux extends AppCompatActivity {
    private ActivityJeuxBinding binding;
    private Game2048 game;
    private GestureDetector gestureDetector;
    private String pseudo;
    private Intent service;

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
                        game.moveRight();
                    } else {
                        game.moveLeft();
                    }
                } else {
                    if (distanceY > 0) {
                        game.moveDown();
                    } else {
                        game.moveUp();
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
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent != null) {
            pseudo = intent.getStringExtra("pseudo");
            service = intent.getParcelableExtra("service");
            binding.LoginVar.setText(pseudo);
        }
        binding.imgAccueil.setOnClickListener(v -> {
            if (service != null){
                stopService(service);
            }
            Intent intent2 = new Intent(Jeux.this,Accueil.class);
            startActivity(intent2);
        });
        binding.imgRejouer.setOnClickListener(v -> {
            Intent intent2 = new Intent(Jeux.this,Jeux.class);
            intent2.putExtra("pseudo",pseudo);
            if (service != null) {
                intent2.putExtra("service", service);
            }
            startActivity(intent2);
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
                int valeur = tile.getValue();
                    textView.setText(String.valueOf(tile.getValue()));
                    textView.setTextColor(ContextCompat.getColor(this, R.color.color_light_text));
                    switch (valeur){
                        case 0:
                            textView.setText("");
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_0));
                            break;
                        case 2:
                            textView.setTextColor(ContextCompat.getColor(this, R.color.color_text));
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_2));
                            break;
                        case 4:
                            textView.setTextColor(ContextCompat.getColor(this, R.color.color_text));
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_4));
                            break;
                        case 8:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_8));
                            break;
                        case 16:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_16));
                            break;
                        case 32:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_32));
                            break;
                        case 64:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_64));
                            break;
                        case 128:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_128));
                            break;
                        case 256:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_256));
                            break;
                        case 512:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_512));
                            break;
                        case 1024:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_1024));
                            break;
                        case 2048:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_2048));
                            break;
                        default:
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                            break;
                }
            }
        }
        binding.textViewScore.setText(getString(R.string.score) + " " + game.getScore());
        if (game.isGameOver()) {
            stopService(service);
            binding.gridLayout.setOnTouchListener(null);
            Intent intent2 = new Intent(Jeux.this, Classement.class);
            intent2.putExtra("pseudo",pseudo);
            startActivity(intent2);
        }
    }
}
