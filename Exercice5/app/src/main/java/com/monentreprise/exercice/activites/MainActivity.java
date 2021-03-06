package com.monentreprise.exercice.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.adapters.CoursesAdapter;
import com.monentreprise.exercice.callbacks.ItemTouchHelperCallback;
import com.monentreprise.exercice.items.CourseItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener
{

	// Constantes :
	public static final int ID_RETOUR_ALEATOIRE = 123;
	public static final String EXTRA_RETOUR_ALEATOIRE = "EXTRA_RETOUR_ALEATOIRE";

	// Liste :
	private RecyclerView recyclerViewListe = null;

	// Gesture detector (pour la liste) :
	private GestureDetector gestureDetector = null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// appel obligatoire :
		super.onCreate(savedInstanceState);

		// on signale que l'activité doit afficher le contenu de app/src/main/res/layout/activity_main.xml :
		setContentView(R.layout.activity_main);

		// récupération de la liste depuis le xml :
		recyclerViewListe = (RecyclerView) findViewById(R.id.liste_courses);

		// optimisation : spécifie que l'objet RecyclerView ne changera pas de hauteur si son contenu change (ce qui est le cas presque tout le temps) :
		recyclerViewListe.setHasFixedSize(true);

		// attribution d'un LayoutManager par défaut (affichage des éléments l'un en dessous de l'autre) :
		recyclerViewListe.setLayoutManager(new LinearLayoutManager(this));

		// création de l'adapter, qui gère la création et la mise à jour du contenu de la liste :
		List<CourseItem> listeCoursesItems = new ArrayList<>();
		listeCoursesItems.add(new CourseItem("Patates"));
		listeCoursesItems.add(new CourseItem("Avocat"));
		listeCoursesItems.add(new CourseItem("Shampooing"));
		listeCoursesItems.add(new CourseItem("Riz"));
		listeCoursesItems.add(new CourseItem("Chocolat"));
		listeCoursesItems.add(new CourseItem("Oeufs"));
		listeCoursesItems.add(new CourseItem("Lait"));
		listeCoursesItems.add(new CourseItem("Pain"));
		listeCoursesItems.add(new CourseItem("Pâtes"));
		listeCoursesItems.add(new CourseItem("Viande"));
		listeCoursesItems.add(new CourseItem("Beurre"));
		listeCoursesItems.add(new CourseItem("Salade"));
		listeCoursesItems.add(new CourseItem("Oignons"));
		listeCoursesItems.add(new CourseItem("Raisins"));
		listeCoursesItems.add(new CourseItem("Sauce"));
		listeCoursesItems.add(new CourseItem("Croissant"));
		listeCoursesItems.add(new CourseItem("Pizza"));
		listeCoursesItems.add(new CourseItem("Chips"));
		CoursesAdapter coursesAdapter = new CoursesAdapter(listeCoursesItems);

		// attribution de l'adapter :
		recyclerViewListe.setAdapter(coursesAdapter);

		// déplacement / suppression :
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(coursesAdapter, ContextCompat.getColor(this, R.color.couleurSelectionItemMain)));
		itemTouchHelper.attachToRecyclerView(recyclerViewListe);

		// gesture detector :
		gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
		{
			@Override
			public boolean onSingleTapUp(MotionEvent event)
			{
				return true;
			}
		});
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		// ajout listener :
		recyclerViewListe.addOnItemTouchListener(this);
	}

	/**
	 * Listener bouton principal
	 * @param view Vue bouton principal
	 */
	public void onClicBoutonPrincipal(View view)
	{
		// lancement de l'activité DetailActivity :
		Intent intent = new Intent(this, DetailActivity.class);
		startActivityForResult(intent, ID_RETOUR_ALEATOIRE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		// récupération et affichage de la valeur aléatoire de DetailActivity :
		if (requestCode == ID_RETOUR_ALEATOIRE && resultCode == Activity.RESULT_OK)
		{
			int retour = data.getIntExtra(EXTRA_RETOUR_ALEATOIRE, 0);
			Toast.makeText(this, "valeur aléatoire retournée : " + retour, Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent)
	{
		if (gestureDetector.onTouchEvent(motionEvent))
		{
			// récupération de l'item cliqué :
			View child = recyclerViewListe.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
			// position dans la liste d'objets métier :
			if (child != null)
			{
				int position = recyclerViewListe.getChildAdapterPosition(child);
				Toast.makeText(this, "clic à la position : " + position, Toast.LENGTH_SHORT).show();

				return true;
			}
		}
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

	@Override
	public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}

	@Override
	protected void onStop()
	{
		super.onStop();

		// suppression listener :
		recyclerViewListe.removeOnItemTouchListener(this);
	}

}
