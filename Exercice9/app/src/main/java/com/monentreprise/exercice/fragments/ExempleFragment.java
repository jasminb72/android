package com.monentreprise.exercice.fragments;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.vues.RondView;


public class ExempleFragment extends Fragment implements View.OnClickListener
{

	// Bouton :
	private RondView rondView = null;


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
			rondView = (RondView) getView().findViewById(R.id.rond_exemple);
		}
	}

	@Override
	public void onStart()
	{
		super.onStart();

		// listener :
		rondView.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		if (getContext() != null)
		{
			AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.exemple_animation);
			set.setTarget(rondView);
			set.start();
		}
	}

	@Override
	public void onStop()
	{
		super.onStop();

		// listener :
		rondView.setOnClickListener(null);
	}

}
