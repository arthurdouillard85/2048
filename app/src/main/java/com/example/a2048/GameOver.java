package com.example.a2048;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.a2048.databinding.ActivityGameOverBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private void saveScores(List<Score> scores) {
        Gson gson = new Gson();
        String json = gson.toJson(scores);
        editor.putString("SCORES", json);
        editor.apply();
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
            ArrayList<Score> scores = loadScores();
            scores.add(createScoreObject(p));
            saveScores(scores);
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

    private ArrayList<Score> loadScores() {
        String json = prefs.getString("SCORES", null);
        ArrayList<Score> scores = new ArrayList<>();
        if (json != null) {
            Gson gson = new Gson();
            Score[] scoreArray = gson.fromJson(json, Score[].class);
            scores.addAll(Arrays.asList(scoreArray));
        }
        return scores;
    }

    private Score createScoreObject(EndGame p) {
        Score score = new Score();
        score.setPseudo(p.getNomJoueur());
        score.setBestTile(p.getBestTile());
        score.setScore(p.getScore());
        return score;
    }
}
