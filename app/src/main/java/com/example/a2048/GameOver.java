package com.example.a2048;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a2048.databinding.ActivityAccueilBinding;
import com.example.a2048.databinding.ActivityGameOverBinding;

public class GameOver extends AppCompatActivity {

    private ActivityGameOverBinding binding;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences("LISTE_INFOS",MODE_PRIVATE);
        editor = prefs.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            EndGame p = (EndGame) bundle.getSerializable("STATS");
            binding.pseudoJoueur.setText(p.getNomJoueur());
            binding.textBestTile.setText(Integer.toString(p.getBestTile()));
            binding.textBestTile.setTextColor(ContextCompat.getColor(this, R.color.color_light_text));
            switch (p.getBestTile()){
                case 0:
                    binding.textBestTile.setText("");
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_0));
                    break;
                case 2:
                    binding.textBestTile.setTextColor(ContextCompat.getColor(this, R.color.color_text));
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_2));
                    break;
                case 4:
                    binding.textBestTile.setTextColor(ContextCompat.getColor(this, R.color.color_text));
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_4));
                    break;
                case 8:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_8));
                    break;
                case 16:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_16));
                    break;
                case 32:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_32));
                    break;
                case 64:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_64));
                    break;
                case 128:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_128));
                    break;
                case 256:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_256));
                    break;
                case 512:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_512));
                    break;
                case 1024:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_1024));
                    break;
                case 2048:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.color_tile_2048));
                    break;
                default:
                    binding.textBestTile.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                    break;
            }
            binding.textScore.setText(getString(R.string.end_score) + " " + p.getScore());
            editor.putString("PSEUDO", p.getNomJoueur());
            editor.putInt("BEST_TILE",p.getBestTile());
            editor.putInt("SCORE", p.getScore());
            editor.apply();


        }
        binding.accueilButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOver.this, Accueil.class);
            startActivity(intent2);
        });
        binding.restartButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOver.this, Jeux.class);
            intent2.putExtra("pseudo", binding.pseudoJoueur.getText().toString());
            startActivity(intent2);
        });
    }
}
