package com.monentreprise.exercice.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.activites.DetailActivity;


public class ExempleFragment extends Fragment implements View.OnClickListener
{

	// Bouton :
	private Button buttonExemple = null;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// création de la vue du fragment pour la retourner :
		return inflater.inflate(R.layout.fragment_exemple, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// bouton :
		if (getView() != null)
		{
			buttonExemple = (Button) getView().findViewById(R.id.bouton_exemple);
		}
	}

	@Override
	public void onStart()
	{
		super.onStart();

		// listener :
		buttonExemple.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		// on vérifie que l'activité parente est bien DetailActivity (on pourrait aussi utiliser une interface si on utilisait le fragment à plusieurs endroits) :
		if (getActivity() instanceof DetailActivity)
		{
			// affichage de la valeur (on utilise getActivity() au lieu de this car le fragment n'a pas son propre Context) :
			Toast.makeText(getActivity(), "valeur de DetailActivity : " + ((DetailActivity) getActivity()).getValeurAleatoire(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onStop()
	{
		super.onStop();

		// listener :
		buttonExemple.setOnClickListener(null);
	}

}
