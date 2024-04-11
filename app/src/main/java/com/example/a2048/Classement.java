package com.example.a2048;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.FragmentTransaction;

        import android.content.Intent;
        import android.os.Bundle;

        //import com.example.a2048.databinding.ActivityAccueilBinding;
        import com.example.a2048.databinding.ActivityClassementBinding;

        import java.util.ArrayList;
        import java.util.List;

public class Classement extends AppCompatActivity {

    private ActivityClassementBinding binding;
    private List<ScoreJoueur> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragments = new ArrayList<>();
        fragments.add(ScoreJoueur.newInstance("1", "Bastien", 10001, 2048));
        fragments.add(ScoreJoueur.newInstance("2", "Loan", 10001, 2));
        fragments.add(ScoreJoueur.newInstance("3", "Arthur", 10001, 4));
        fragments.add(ScoreJoueur.newInstance("4", "Armoif", 10001, 8));
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