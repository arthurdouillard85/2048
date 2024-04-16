package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.a2048.databinding.ActivityClassementBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Classement extends AppCompatActivity {

    private ActivityClassementBinding binding;
    private ArrayList<ScoreJoueur> fragments = new ArrayList<>();
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences("LISTE_INFOS",MODE_PRIVATE);

        // Charger les scores Gson et les afficher
        loadScoresAndDisplay();
    }

    // Méthode pour charger les scores Gson et les afficher
    private void loadScoresAndDisplay() {
        String jsonScores = prefs.getString("SCORES", null);
        if (jsonScores != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Score>>(){}.getType();
            ArrayList<Score> scores = gson.fromJson(jsonScores, type);
            Collections.sort(scores, Comparator.comparing(Score::getScore).reversed());
            if (scores != null) {
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                ScoreAdapter adapter = new ScoreAdapter(scores);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    // Méthode pour rafraîchir le conteneur de fragments
    //private void refreshFragmentContainer() {
    //    FragmentManager fragmentManager = getSupportFragmentManager();
    //    FragmentTransaction ft = fragmentManager.beginTransaction();
    //    // Supprimer tous les fragments existants
    //    for (Fragment fragment : fragmentManager.getFragments()) {
    //        ft.remove(fragment);
    //    }
    //    ft.commitNow();
//
    //    // Ajouter les nouveaux fragments
    //    ft = fragmentManager.beginTransaction();
    //    for (ScoreJoueur frag : fragments) {
    //        ft.add(R.id.fragment_container, frag);
    //    }
    //    ft.commit();
    //}

    @Override
    protected void onResume() {
        super.onResume();
        binding.accueil.setOnClickListener(v -> {
            Intent intent = new Intent(Classement.this,Accueil.class);
            startActivity(intent);
        });

    }
}