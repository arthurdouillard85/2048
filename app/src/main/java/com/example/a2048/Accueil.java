package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.a2048.databinding.ActivityAccueilBinding;

import java.util.Locale;

public class Accueil extends AppCompatActivity {

    private ActivityAccueilBinding binding;

    private static String langue = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.classement.setText(R.string.ranking);
        Intent stopAllServicesIntent = new Intent("com.example.a2048.STOP_ALL_SERVICES");
        sendBroadcast(stopAllServicesIntent);


        Resources res = getResources();
        Configuration conf = res.getConfiguration();

        switch(langue){
            default:
                binding.languageSpinner.setSelection(0);
                conf.setLocale(Locale.getDefault());
                res.updateConfiguration(conf, res.getDisplayMetrics());
                break;
            case "en":
                binding.languageSpinner.setSelection(0);
                conf.setLocale(new Locale("en"));
                res.updateConfiguration(conf, res.getDisplayMetrics());
                break;
            case "fr":
                binding.languageSpinner.setSelection(1);
                conf.setLocale(new Locale("fr"));
                res.updateConfiguration(conf, res.getDisplayMetrics());
                break;
            case "de":
                binding.languageSpinner.setSelection(2);
                conf.setLocale(new Locale("de"));
                res.updateConfiguration(conf, res.getDisplayMetrics());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.classement.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this,Classement.class);
            startActivity(intent);
        });
        binding.jouer.setOnClickListener(v -> {
            Intent musicServiceIntent = new Intent(getApplicationContext(), MusicService.class);
            startService(musicServiceIntent);
            Intent intent = new Intent(Accueil.this,Jeux.class);
            intent.putExtra("pseudo",binding.pseudo.getText().toString());
            intent.putExtra("service",musicServiceIntent);
            startActivity(intent);
        });

        binding.languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Resources res = getResources();
                Configuration conf = res.getConfiguration();

                String selectedLanguage = parent.getItemAtPosition(position).toString();
                switch (selectedLanguage){
                    case "English":
                        conf.setLocale(new Locale("en"));
                        res.updateConfiguration(conf, res.getDisplayMetrics());
                        langue = "en";
                        break;
                    case "Français":
                        conf.setLocale(new Locale("fr"));
                        res.updateConfiguration(conf, res.getDisplayMetrics());
                        langue = "fr";
                        break;
                    case "Deutsch":
                        conf.setLocale(new Locale("de"));
                        res.updateConfiguration(conf, res.getDisplayMetrics());
                        langue = "de";
                        break;
                    default:
                        conf.setLocale(Locale.getDefault());
                        res.updateConfiguration(conf, res.getDisplayMetrics());
                        langue = "en";
                        break;
                }
                binding.classement.setText(R.string.ranking);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // L'utilisateur n'a rien sélectionné
            }
        });
    }
}