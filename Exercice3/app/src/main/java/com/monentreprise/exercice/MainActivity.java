package com.monentreprise.exercice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
	Button boutonOk;
	final String TAG="classe MainActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// appel obligatoire :
		super.onCreate(savedInstanceState);

		// on signale que l'activité doit afficher le contenu de app/src/main/res/layout/activity_main.xml :
		setContentView(R.layout.activity_main);

		// récupération du libellé de l'action bar :
		String libelle = getString(R.string.app_name);

		// affichage du toast (ne pas oublier le .show() à la fin !) :
		Toast.makeText(this, libelle, Toast.LENGTH_LONG).show();

		Button boutonOk= (Button)findViewById(R.id.bouton_ok);
		boutonOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Log.i(TAG, "click sur le boutonJBA");
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				//startActivity(intent);
                startActivityForResult(intent,Constantes.ID_RETOUR);
			}
		});
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.ID_RETOUR
                && resultCode == Constantes.RESULT_OK)
        {
            Integer retour = data.getIntExtra(Constantes.EXTRA_RETOUR, 0);
            Toast.makeText(this, retour.toString(), Toast.LENGTH_LONG).show();
        }
    }



}
