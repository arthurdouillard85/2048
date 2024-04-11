package com.example.a2048;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialiser le lecteur de musique
        mediaPlayer = MediaPlayer.create(this, R.raw.master_of_puppet);
        mediaPlayer.setLooping(true); // Pour répéter la musique

        // Planifier le changement de musique toutes les minutes
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Changer la musique
                changeMusic();
            }
        }, 0, 60 * 1000); // Change la musique toutes les minutes
    }

    private void changeMusic() {
        // Changez la musique ici (charger une nouvelle piste, etc.)
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Arrêter la musique et le timer lors de la destruction du service
        mediaPlayer.stop();
        mediaPlayer.release();
        timer.cancel();
    }
}