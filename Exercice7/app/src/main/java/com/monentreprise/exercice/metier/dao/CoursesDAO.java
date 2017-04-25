package com.monentreprise.exercice.metier.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.monentreprise.exercice.metier.BaseContrat;
import com.monentreprise.exercice.metier.dbhelpers.DatabaseHelper;
import com.monentreprise.exercice.metier.dto.CourseDTO;

import java.util.ArrayList;
import java.util.List;

public class CoursesDAO
{

	/**
	 * Retourne la liste de courses.
	 * @param context Context
	 * @return Liste de CourseDTO
	 */
	public List<CourseDTO> getListeCourses(Context context)
	{
		// projection (colonnes utilisées après la requète) :
		String[] projection = {BaseContrat.CoursesContrat._ID,
				BaseContrat.CoursesContrat.COLONNE_INTITULE,
				BaseContrat.CoursesContrat.COLONNE_VALIDE};

		// tri :
		String tri = BaseContrat.CoursesContrat.COLONNE_INTITULE + " ASC " ;

		// accès en lecture (query) :
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();

		// requête :
		Cursor cursor = db.query(
				BaseContrat.CoursesContrat.TABLE_COURSES,	// table sur laquelle faire la requète
				projection,									// colonnes à retourner
				null,										// colonnes pour la clause WHERE (inactif)
				null,										// valeurs pour la clause WHERE (inactif)
				null,										// GROUP BY (inactif)
				null,										// HAVING (inactif)
				tri,										// ordre de tri
				null);										// LIMIT (inactif)

		// construction de la liste de courses
		List<CourseDTO> listeCourses = new ArrayList<>();
		if (cursor != null)
		{
			try
			{
				cursor.moveToFirst();
				while (!cursor.isAfterLast())
				{
					// conversion des données remontées en un objet métier :
					CourseDTO courseDTO=new CourseDTO(
							cursor.getString(cursor.getColumnIndex(BaseContrat.CoursesContrat.COLONNE_INTITULE)),
							cursor.getInt(cursor.getColumnIndex(BaseContrat.CoursesContrat.COLONNE_VALIDE)));

                    //ajout de l'objet métier à la liste
                    listeCourses.add(courseDTO);

                    //curseur suivant
					cursor.moveToNext();
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
			finally
			{
				cursor.close();
			}
		}

		return listeCourses;
	}
}
