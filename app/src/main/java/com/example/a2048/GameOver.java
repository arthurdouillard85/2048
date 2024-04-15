package com.example.a2048;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.a2048.databinding.ActivityGameOverBinding;

import java.util.ArrayList;
import java.util.List;

public class GameOver extends AppCompatActivity {

    private ActivityGameOverBinding binding;
    private List<ScoreJoueur> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragments = new ArrayList<>();
        fragments.add(ScoreJoueur.newInstance("1", "Bastien", 10001, 2048));
        fragments.add(ScoreJoueur.newInstance("2", "Loan", 10001, 2));
        fragments.add(ScoreJoueur.newInstance("3", "Arthur", 10001, 4));
        fragments.add(ScoreJoueur.newInstance("4", "Armoif", 10001, 8));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(ScoreJoueur frag : fragments) {
            ft.add(R.id.fragment_container2,frag);
        }
        ft.commit();
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
        }
        binding.accueilButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(GameOver.this, Accueil.class);
            startActivity(intent2);
        });
        binding.restartButton.setOnClickListener(v -> {
            Intent musicServiceIntent = new Intent(getApplicationContext(), MusicService.class);
            startService(musicServiceIntent);
            Intent intent2 = new Intent(GameOver.this, Jeux.class);
            intent2.putExtra("pseudo", binding.pseudoJoueur.getText().toString());
            intent.putExtra("service",musicServiceIntent);
            startActivity(intent2);
        });
    }
}
