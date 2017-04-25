package com.monentreprise.exercice;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class DetailActivity extends AppCompatActivity {
    private Integer varAleatoire = -1;
    static final String VARALEATOIRE = "varAleatoire";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //récupération de var aléatoire sauvegardée
        if (savedInstanceState!=null){
            varAleatoire=savedInstanceState.getInt(VARALEATOIRE);
        }

        //affichage valeur avant init
        Toast.makeText(this, varAleatoire.toString(), Toast.LENGTH_LONG).show();

        if (varAleatoire==-1){
            varAleatoire=new Random().nextInt(100);
        }

        // affichage valeur après init
        Toast.makeText(this, varAleatoire.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(VARALEATOIRE,varAleatoire);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constantes.EXTRA_RETOUR,varAleatoire);
        setResult(Constantes.RESULT_OK, intent);
        super.onBackPressed();
        finish();
    }
}
