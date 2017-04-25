package com.monentreprise.exercice.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.monentreprise.exercice.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final String TAG = "Classe MainActivity";
    private GestureDetector gestureDetector;

    // Eléments graphiques :
    private Button buttonOK = null;
    private EditText editTextSaisie = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // appel obligatoire :
        super.onCreate(savedInstanceState);

        // on signale que l'activité doit afficher le contenu de app/src/main/res/layout/activity_main.xml :
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Course> data = new ArrayList();
        for (int i=1;i<200;i++){
            data.add(new Course("tutu"+ i));
        }

        adapter = new CourseAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(this);


        gestureDetector= new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });


        // récupération du libellé de l'action bar :
        String libelle = getString(R.string.app_name);

        // affichage du toast (ne pas oublier le .show() à la fin !) :
        Toast.makeText(this, libelle, Toast.LENGTH_LONG).show();

        // éléments graphiques :
        buttonOK = (Button) findViewById(R.id.bouton_ok);
        editTextSaisie = (EditText) findViewById(R.id.champ_saisie);

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (gestureDetector.onTouchEvent(e)){
            View child = recyclerView.findChildViewUnder(e.getX(),e.getY());

            if(child!=null){
                int position = recyclerView.getChildAdapterPosition(child);
                Log.i(TAG,"clic à la position" + position);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}