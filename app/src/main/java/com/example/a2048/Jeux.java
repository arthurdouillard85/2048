package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.a2048.databinding.ActivityJeuxBinding;
import com.example.a2048.databinding.ActivityMainBinding;

public class Jeux extends AppCompatActivity {

    private ActivityJeuxBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJeuxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.imgAccueil.setOnClickListener(v -> {
            Intent intent2 = new Intent(Jeux.this,Accueil.class);
            startActivity(intent2);
        });
        binding.imgRejouer.setOnClickListener(v -> {
            Intent intent2 = new Intent(Jeux.this,Jeux.class);
            startActivity(intent2);
        });
        Intent intent = getIntent();
        if(intent != null) {
            binding.LoginVar.setText(intent.getStringExtra("pseudo"));
        }
    }
}