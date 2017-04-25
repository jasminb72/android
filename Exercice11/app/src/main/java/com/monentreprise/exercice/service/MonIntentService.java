package com.monentreprise.exercice.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MonIntentService extends IntentService {

    private int compteur = 0;
    public MonIntentService() {
        super("MonIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        compteur++;
        Log.i("class MonIntentService","compteur =" + compteur);
    }
}
