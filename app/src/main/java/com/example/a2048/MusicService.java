package com.example.a2048;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    private final MediaPlayer player = new MediaPlayer();
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player.reset();

        int resId = getResources().getIdentifier("demouler_un_cake", "raw", getPackageName());

        if (resId != 0) {
            // Ouvrir un flux de ressources pour la ressource brute
            AssetFileDescriptor afd = getResources().openRawResourceFd(resId);

            if (afd != null) {
                try {
                    // Définir le lecteur de données sur le flux de ressources
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                    // Libérer le flux de ressources après utilisation
                    afd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // La ressource brute n'a pas été trouvée
            Log.e("MediaPlayer", "Ressource brute non trouvée");
        }
        // Planifier le changement de musique toutes les minutes
        //timer = new Timer();
        //timer.scheduleAtFixedRate(new TimerTask() {
        //    @Override
        //    public void run() {
        //        // Changer la musique
        //        changeMusic();
        //    }
        //}, 0, 60 * 1000); // Change la musique toutes les minutes
    }

    private void changeMusic() {
        // Changez la musique ici (charger une nouvelle piste, etc.)
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Arrêter la musique et le timer lors de la destruction du service
        player.stop();
        player.release();
        timer.cancel();
    }
}