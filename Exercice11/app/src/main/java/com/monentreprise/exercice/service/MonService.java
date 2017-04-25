package com.monentreprise.exercice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MonService extends Service {
    private Integer compteur = 0;

    // Binder :
    private IBinder binder = new MonBinder();

    // Classe MonBinder :
    public class MonBinder extends Binder {
        public MonService getService() {
            return MonService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // Méthode d'exemple exposée aux composants clients :
    public void updateCompteur() {
        compteur++;
        Log.i("classe MonService","compteur=" + compteur);
    }
}