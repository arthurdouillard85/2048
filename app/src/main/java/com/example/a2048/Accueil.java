package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a2048.databinding.ActivityAccueilBinding;

public class Accueil extends AppCompatActivity {

    private ActivityAccueilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_accueil);
    }
}