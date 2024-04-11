package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.a2048.databinding.ActivityAccueilBinding;

public class Accueil extends AppCompatActivity {

    private ActivityAccueilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.classement.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this,Classement.class);
            startActivity(intent);
        });
        binding.jouer.setOnClickListener(v -> {
            Intent musicServiceIntent = new Intent(getApplicationContext(), MusicService.class); // Remplacez MusicService.class par votre classe de service réelle
            startService(musicServiceIntent);
            Intent intent = new Intent(Accueil.this,Jeux.class);
            intent.putExtra("pseudo",binding.pseudo.getText().toString());
            startActivity(intent);
        });
    }


}