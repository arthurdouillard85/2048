package com.example.a2048;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.FragmentTransaction;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;

        //import com.example.a2048.databinding.ActivityAccueilBinding;
        import com.example.a2048.databinding.ActivityClassementBinding;

        import java.util.ArrayList;
        import java.util.List;

public class Classement extends AppCompatActivity {

    private static ArrayList statScore;
    private ActivityClassementBinding binding;
    private List<ScoreJoueur> fragments;
    private SharedPreferences prefs;
    private String pseudo;
    private int score;
    private int best_tile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences("LISTE_INFOS",MODE_PRIVATE);
        pseudo = prefs.getString("PSEUDO", "guest");
        score = prefs.getInt("SCORE", 0);
        best_tile = prefs.getInt("BEST_TILE", 0);


        fragments = new ArrayList<>();
        fragments.add(ScoreJoueur.newInstance("1", pseudo, score, best_tile));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for(ScoreJoueur frag : fragments) {
            ft.add(R.id.fragment_container,frag);
        }
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.accueil.setOnClickListener(v -> {
            Intent intent = new Intent(Classement.this,Accueil.class);
            startActivity(intent);
        });

    }
}