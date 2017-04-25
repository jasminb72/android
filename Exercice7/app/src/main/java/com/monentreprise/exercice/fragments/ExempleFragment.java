package com.monentreprise.exercice.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.monentreprise.exercice.R;
import com.monentreprise.exercice.activites.DetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


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
			// récupération de la valeur aléatoire :
			int valeurAleatoire = ((DetailActivity) getActivity()).getValeurAleatoire();

			// affichage de la popup. Comme c'est un fragment, on lui passe un argument :
			ExempleDialogFragment exempleDialogFragment = new ExempleDialogFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(ExempleDialogFragment.EXTRA_VALEUR_ALEATOIRE, valeurAleatoire);
			exempleDialogFragment.setArguments(bundle);
			exempleDialogFragment.show(getActivity().getSupportFragmentManager(), "exemple");

			// création des variables à envoyer au webservice
			RequestParams requestParams = new RequestParams();
			requestParams.put("test", Integer.toString(valeurAleatoire));

			// appel webservice en POST :
			AsyncHttpClient client = new AsyncHttpClient();
			client.post("http://httpbin.org/post", requestParams, new AsyncHttpResponseHandler()
			{
				@Override
				public void onSuccess(int statusCode, Header[] headers, byte[] response)
				{
					// succès :
					String retour = new String(response);

					if (getContext() != null)
					{
						// décodage du retour JSON :
						JSONObject jsonObject;
						try
						{
							jsonObject = new JSONObject(retour);
							JSONObject jsonObjectLot = jsonObject.getJSONObject("form");
							String retourValeurAleatoire = jsonObjectLot.getString("test");

							// affichage :
							Toast.makeText(getContext(), "valeur aléatoire depuis webservice : " + retourValeurAleatoire, Toast.LENGTH_SHORT).show();
						}
						catch (JSONException e)
						{
							// erreur :
							Toast.makeText(getContext(), "erreur de décodage du retour du webservice", Toast.LENGTH_SHORT).show();
						}
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e)
				{
					// erreur :
					if (getContext() != null)
					{
						Toast.makeText(getContext(), "erreur de connexion ...", Toast.LENGTH_SHORT).show();
					}
					e.printStackTrace();
				}
			});
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
