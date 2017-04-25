package com.monentreprise.exercice.metier.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesHelper
{

	// Constantes :
	private static final String SAISIE = "SAISIE";


	/**
	 * Retourne la dernière saisie.
	 * @param context Context
	 * @return Saisie
	 */
	public static String getSaisie(Context context)
	{
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(SAISIE, null);
	}

	/**
	 * Enregistre la saisie.
	 * @param context Context
	 * @param saisie Saisie
	 */
	public static void setSaisie(Context context, String saisie)
	{
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(SAISIE, saisie);
		editor.apply();
	}
}
