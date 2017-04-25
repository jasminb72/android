package com.monentreprise.exercice.activites;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.monentreprise.exercice.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class DessinActivity extends AppCompatActivity implements View.OnTouchListener
{

	// Constantes :
	private static final String REPERTOIRE = "dessin";
	private static final String FICHIER = "monoeuvre";

	// Image dessin :
	private ImageView imageViewDessin = null;

	// Dessin :
	private Bitmap bitmap = null;
	private Canvas canvas = null;
	private Paint paint = null;
	private float xDessin = 0;
	private float yDessin = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init :
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dessin);

		// image dessin :
		imageViewDessin = (ImageView) findViewById(R.id.image_dessin);

		// récupération du dessin s'il existe :
		ContextWrapper contextWrapper = new ContextWrapper(this);
		File repertoire = contextWrapper.getDir(REPERTOIRE, Context.MODE_PRIVATE);
		File fichierBitmap = new File(repertoire, FICHIER);
		Picasso.with(this).invalidate(fichierBitmap);
		Picasso.with(this).load(fichierBitmap).into(imageViewDessin);
	}

	@Override
	protected void onStart()
	{
		// init :
		super.onStart();

		// listeners :
		imageViewDessin.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent)
	{
		if (view == imageViewDessin)
		{
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
			{
				// création d'une bitmap si elle n'existe pas déjà :
				if (bitmap == null)
				{
					// initialisation dessin :
					Bitmap.Config config = Bitmap.Config.ARGB_8888;
					bitmap = Bitmap.createBitmap(imageViewDessin.getWidth(), imageViewDessin.getHeight(), config);
					canvas = new Canvas(bitmap);
					paint = new Paint(Paint.ANTI_ALIAS_FLAG);
				}

				// point de départ du dessin :
				xDessin = motionEvent.getX();
				yDessin = motionEvent.getY();

				// couleur et épaisseur aléatoires :
				Random random = new Random();
				paint.setColor(0xFF000000 + random.nextInt(0xFFFFFF));
				paint.setStrokeWidth(random.nextInt((int) getResources().getDimension(R.dimen.epaisseur_dessin_max)));
				paint.setStrokeCap(Paint.Cap.ROUND);
			}
			else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE)
			{
				// dessin :
				canvas.drawLine(xDessin, yDessin, motionEvent.getX(), motionEvent.getY(), paint);

				// on affecte le dessin à l'imageView :
				BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
				imageViewDessin.setImageDrawable(bitmapDrawable);

				// sauvegarde des coordonnées actuelles :
				xDessin = motionEvent.getX();
				yDessin = motionEvent.getY();
			}
			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed()
	{
		if (bitmap != null)
		{
			// wrapper :
			ContextWrapper contextWrapper = new ContextWrapper(this);
			
			// chemin vers /data/data/com.monentreprise.monapplication/app_<nomRepertoire> :
			File repertoire = contextWrapper.getDir(REPERTOIRE, Context.MODE_PRIVATE);
			
			// création répertoire et fichier :
			File fichierBitmap = new File(repertoire, FICHIER);
			
			// enregistrement :
			FileOutputStream fileOutputStream = null;
			try
			{
				fileOutputStream = new FileOutputStream(fichierBitmap);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (fileOutputStream != null)
				{
					try
					{
						fileOutputStream.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
			// libération de ressources :
			bitmap.recycle();
		}
		
		// retour :
		super.onBackPressed();
	}

	@Override
	protected void onStop()
	{
		// init :
		super.onStop();

		// listeners :
		imageViewDessin.setOnTouchListener(null);
	}

}
