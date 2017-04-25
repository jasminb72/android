package com.monentreprise.exercice.activites;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.adapters.ExemplePagerAdapter;
import com.monentreprise.exercice.service.MonService;
import com.monentreprise.exercice.transformers.DetailPageTransformer;

import java.util.Random;

public class DetailActivity extends AppCompatActivity
{

	// Constantes :
	private static final String CLE_VALEUR_ALEATOIRE = "CLE_VALEUR_ALEATOIRE";
	private static final int VALEUR_DEFAUT = -1;

	// Viewpager :
	private ViewPager viewPager = null;

	// Valeur aléatoire :
	private int valeurAleatoire = VALEUR_DEFAUT;

	private MonService monService=null;
	private ServiceConnection serviceConnection=new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			monService = ((MonService.MonBinder) binder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			monService=null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// initialisation :
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// on tente de récupérer la valeur aléatoire si précédemment sauvegardée :
		if (savedInstanceState != null)
		{
			valeurAleatoire = savedInstanceState.getInt(CLE_VALEUR_ALEATOIRE, VALEUR_DEFAUT);
		}

		// on attribue une valeur aléatoire si égal à -1 :
		if (valeurAleatoire == VALEUR_DEFAUT)
		{
			Random random = new Random();
			valeurAleatoire = random.nextInt(100) + 1; // entre 1 et 100
		}

		// on affiche la valeur aléatoire :
		Toast.makeText(this, "valeur aléatoire : " + valeurAleatoire, Toast.LENGTH_SHORT).show();

		// initialisation du viewPager :
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		ExemplePagerAdapter exemplePagerAdapter = new ExemplePagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(exemplePagerAdapter);
		viewPager.setPageTransformer(true, new DetailPageTransformer());
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// sauvegarde valeur aléatoire :
		outState.putInt(CLE_VALEUR_ALEATOIRE, valeurAleatoire);

		// appel à la méthode parente utile notamment si on veut qu'Android sauvegarde automatiquement des champs de saisie (devant obligatoirement avoir un id) :
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed()
	{
		// envoi de la valeur aléatoire à l'activité appelante :
		Intent intent = new Intent();
		intent.putExtra(MainActivity.EXTRA_RETOUR_ALEATOIRE, valeurAleatoire);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	@Override
	public void finish()
	{
		// init :
		super.finish();

		// animation de sortie :
		overridePendingTransition(R.anim.page_slide_out, R.anim.page_slide_vertical_back);
	}

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,MonService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (monService!=null){unbindService(serviceConnection);}
    }

    public void onBoutonClick(){
        if (monService!=null){
            monService.updateCompteur();
        }
    }
}
