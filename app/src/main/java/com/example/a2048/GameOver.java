package com.example.a2048;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a2048.databinding.ActivityAccueilBinding;
import com.example.a2048.databinding.ActivityGameOverBinding;

public class GameOver extends AppCompatActivity {

    private ActivityGameOverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            EndGame p = (EndGame) bundle.getSerializable("STATS");
            binding.textMeilleurJoueur.setText(p.getNomJoueur());
            binding.textBestTile.setText(getString(R.string.end_best_tile) + " " + p.getBestTile());
            binding.textScore.setText(getString(R.string.end_score) + " " + p.getScore());
        }
        binding.accueilButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOver.this, Accueil.class);
            startActivity(intent2);
        });
        binding.restartButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOver.this, Jeux.class);
            //intent2.putExtra("pseudo", pseudo);
            startActivity(intent2);
        });
    }
}
