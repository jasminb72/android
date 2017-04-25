package com.monentreprise.exercice.activites;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import android.widget.EditText;
import android.widget.Toast;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.adapters.CoursesAdapter;
import com.monentreprise.exercice.callbacks.ItemTouchHelperCallback;
import com.monentreprise.exercice.metier.dao.CoursesDAO;
import com.monentreprise.exercice.metier.dbhelpers.DatabaseHelper;
import com.monentreprise.exercice.metier.dto.CourseDTO;
import com.monentreprise.exercice.metier.sharedpreferences.SharedPreferencesHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener, NavigationView.OnNavigationItemSelectedListener
{

	// Constantes :
	public static final int ID_RETOUR_ALEATOIRE = 123;
	public static final String EXTRA_RETOUR_ALEATOIRE = "EXTRA_RETOUR_ALEATOIRE";
	private static final int MA_PERMISSION_CAMERA = 456;
	private static final String TAG = MainActivity.class.getSimpleName();

	// Liste :
	private RecyclerView recyclerViewListe = null;

	// Saisie :
	private EditText editTextSaisie = null;

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

		// création de la base de données, si inexistante :
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		databaseHelper.getReadableDatabase();

		// accès à la base de données :
		CoursesDAO coursesDAO = new CoursesDAO();
		List<CourseDTO> listeCourseDTOs = coursesDAO.getListeCourses(this);

		// liste :
		CoursesAdapter coursesAdapter = new CoursesAdapter(listeCourseDTOs);
		recyclerViewListe = (RecyclerView) findViewById(R.id.liste_courses);
		recyclerViewListe.setHasFixedSize(true);
		recyclerViewListe.setLayoutManager(new LinearLayoutManager(this));
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

		// saisie :
		editTextSaisie = (EditText) findViewById(R.id.champ_saisie);
		String precedenteSaisie = SharedPreferencesHelper.getSaisie(this);
		if (precedenteSaisie != null)
		{
			editTextSaisie.setText(precedenteSaisie);
		}
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
		// sauvegarde de la saisie :
		SharedPreferencesHelper.setSaisie(this, editTextSaisie.getText().toString());

		// lancement de l'activité DetailActivity :
		Intent intent = new Intent(this, DetailActivity.class);
		startActivityForResult(intent, ID_RETOUR_ALEATOIRE);

		// personnalisation de la transition, qui est un peu différente car on a utilisé un startActivityForResult() :
		overridePendingTransition(R.anim.page_slide_horizontal_in, R.anim.page_slide_out);
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

			// personnalisation de la transition, qui est un peu différente car on a utilisé un startActivityForResult() :
			overridePendingTransition(R.anim.page_slide_horizontal_in, R.anim.page_slide_out);
		}
		else if (id == R.id.navigation_dessin)
		{
			// lancement de l'activité DessinActivity :
			Intent intent = new Intent(this, DessinActivity.class);
			startActivity(intent);
		}

		// fermeture une fois l'item sélectionné :
		drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	/**
	 * Listener clic bouton accès.
	 * @param view Bouton accès
	 */
	public void onClicBoutonAcces(View view)
	{
		// on vérifie si la permission nous est accordée ou non :
		int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
		if (permission != PackageManager.PERMISSION_GRANTED)
		{
			// utilitaire qui dit si c'est approprié d'afficher une explication :
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
			{
				// on peut afficher un message d'explication ici et lui redemander après la permission :
				Toast.makeText(this, "la caméra est nécessaire au bon fonctionnement de l'application", Toast.LENGTH_SHORT).show();
			}

			// affichage de la popup demande de permission :
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MA_PERMISSION_CAMERA);
		}
		else
		{
			Toast.makeText(this, "Permission caméra accordée !", Toast.LENGTH_SHORT).show();
		}
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
