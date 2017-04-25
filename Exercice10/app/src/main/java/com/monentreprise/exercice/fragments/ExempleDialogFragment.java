package com.monentreprise.exercice.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.monentreprise.exercice.R;


public class ExempleDialogFragment extends DialogFragment
{

	// Constantes :
	public static final String EXTRA_VALEUR_ALEATOIRE = "EXTRA_VALEUR_ALEATOIRE";


	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// récupération de l'argument :
		int valeurAleatoire = getArguments().getInt(EXTRA_VALEUR_ALEATOIRE, -1);

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("valeur aléatoire : " + valeurAleatoire)
				.setPositiveButton(R.string.libelle_bouton_valider_dialog, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// FIRE ZE MISSILES!
					}
				})
				.setNegativeButton(R.string.libelle_bouton_annuler_dialog, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// User cancelled the dialog
					}
				});
		// Create the AlertDialog object and return it
		return builder.create();
	}

}
