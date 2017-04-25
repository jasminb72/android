package com.monentreprise.exercice.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.adapters.ExemplePagerAdapter;

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

	/**
	 * Retourne la valeur aléatoire.
	 * @return valeurAleatoire
	 */
	public int getValeurAleatoire()
	{
		return valeurAleatoire;
	}

}
