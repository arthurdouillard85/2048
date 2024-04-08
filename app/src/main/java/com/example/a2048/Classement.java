package com.example.a2048;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;

        //import com.example.a2048.databinding.ActivityAccueilBinding;
        import com.example.a2048.databinding.ActivityClassementBinding;

public class Classement extends AppCompatActivity {

    private ActivityClassementBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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