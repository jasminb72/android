package com.monentreprise.exercice.fragments;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.activites.DessinActivity;
import com.monentreprise.exercice.activites.DetailActivity;
import com.monentreprise.exercice.service.MonIntentService;
import com.monentreprise.exercice.vues.RondView;
import com.squareup.picasso.Picasso;


public class ExempleFragment extends Fragment implements View.OnClickListener
{

	// Constantes :
	private static final int NOTIFICATION_ID = 123;

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
					.load("http://www.denis-fremond.com/photos_texte/06012017184558222752.JPG")
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
			// animation :
			AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.exemple_animation);
			set.setTarget(rondView);
			set.start();

			// notification :
			ajouterNotification();

			//exercice 13 JBA
			((DetailActivity) getActivity()).onBoutonClick();
			Intent intent = new Intent(getActivity(), MonIntentService.class);
			getActivity().startService(intent);
		}
	}

	/**
	 * Ajout d'une notification.
	 */
	private void ajouterNotification()
	{
		if (getContext() != null)
		{
			// construction notification :
			NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
					.setSmallIcon(R.drawable.exemple_icone)
					.setContentTitle("Ma notification")
					.setAutoCancel(true)
					.setContentText("Bienvenue !");

			// action de retour simple (écrase le comportement de la notification, si elle est déjà existante) :
			Intent intent = new Intent(getContext(), DessinActivity.class);
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());

			// ajoute la back stack de l'activité à afficher :
			stackBuilder.addParentStack(DessinActivity.class);

			// ajoute l'intent en haut de la stack :
			stackBuilder.addNextIntent(intent);

			// intent lançant l'activité lorsqu'on clique sur la notification :
			PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			builder.setContentIntent(pendingIntent);

			// affichage notification (si ID existant, remplace la précédente) :
			NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
			manager.notify(NOTIFICATION_ID, builder.build());
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
