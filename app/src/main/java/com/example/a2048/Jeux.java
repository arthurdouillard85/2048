package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a2048.databinding.ActivityMainBinding;

public class Jeux extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}