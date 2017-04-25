package com.monentreprise.exercice.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.adapters.CoursesAdapter;
import com.monentreprise.exercice.callbacks.ItemTouchHelperCallback;
import com.monentreprise.exercice.items.CourseItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener, NavigationView.OnNavigationItemSelectedListener
{

	// Constantes :
	public static final int ID_RETOUR_ALEATOIRE = 123;
	public static final String EXTRA_RETOUR_ALEATOIRE = "EXTRA_RETOUR_ALEATOIRE";
	private static final String TAG = MainActivity.class.getSimpleName();

	// Liste :
	private RecyclerView recyclerViewListe = null;

	// Eléments menu :
	private DrawerLayout drawerLayout = null;
	private NavigationView navigationView = null;

	// Gesture detector (pour la liste) :
	private GestureDetector gestureDetector = null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init :
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// action bar personnalisée :
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

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

		// burger menu :
		navigationView = (NavigationView) findViewById(R.id.navigation);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		toggle.syncState();
		drawerLayout.addDrawerListener(toggle);
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		// ajout listeners :
		recyclerViewListe.addOnItemTouchListener(this);
		navigationView.setNavigationItemSelectedListener(this);
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
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{
		// on récupère l'identifiant de l'item cliqué :
		int id = item.getItemId();
		if (id == R.id.navigation_main)
		{
			Log.i(TAG, "page main");
		}
		else if (id == R.id.navigation_detail)
		{
			// lancement de l'activité DetailActivity :
			Intent intent = new Intent(this, DetailActivity.class);
			startActivityForResult(intent, ID_RETOUR_ALEATOIRE);
		}

		// fermeture une fois l'item sélectionné :
		drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onBackPressed()
	{
		// lorsqu'on appuie sue le bouton "back" de l'appareil, il est préférable de fermer le menu si celui-ci est ouvert, plutôt que de laisser l'action par défaut :
		if (drawerLayout.isDrawerOpen(GravityCompat.START))
		{
			drawerLayout.closeDrawer(GravityCompat.START);
		}
		else
		{
			super.onBackPressed();
		}
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		// suppression listeners :
		recyclerViewListe.removeOnItemTouchListener(this);
		navigationView.setNavigationItemSelectedListener(null);
	}

}
