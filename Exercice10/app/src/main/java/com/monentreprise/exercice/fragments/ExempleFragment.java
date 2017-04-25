package com.monentreprise.exercice.fragments;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.vues.RondView;
import com.squareup.picasso.Picasso;


public class ExempleFragment extends Fragment implements View.OnClickListener
{

	// Image de fond :
	private ImageView imageViewFond = null;

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

		if (getView() != null)
		{
			// fond :
			imageViewFond = (ImageView) getView().findViewById(R.id.image_fond);
			Picasso.with(getContext())
					.load("http://www.denis-fremond.com/photos_art/20062014195748u38892506.JPG")
					.fit()
					.centerCrop()
					.into(imageViewFond);

			// affiche un triangle en haut à gauche de l'image permettant de savoir d'où elle est chargée (bleu = disque, vert = en mémoire vive, rouge = réseau) :
			Picasso.with(getContext()).setIndicatorsEnabled(true);

			// bouton :
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
