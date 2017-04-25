package com.monentreprise.exercice.metier.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.monentreprise.exercice.metier.BaseContrat;


public class DatabaseHelper extends SQLiteOpenHelper
{

	// Constantes :
	private static final String NOM_DATABASE = "courses.db";
	private static final int VERSION_DATABASE = 1;
	private static final String CREATE_TABLE_COURSES = "CREATE TABLE " + BaseContrat.CoursesContrat.TABLE_COURSES + " ("
			+ BaseContrat.CoursesContrat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ BaseContrat.CoursesContrat.COLONNE_INTITULE + " TEXT NOT NULL, "
			+ BaseContrat.CoursesContrat.COLONNE_VALIDE + " INTEGER NOT NULL "
			+ ")";


	/**
	 * Constructeur.
	 * @param context Context
	 */
	public DatabaseHelper(Context context)
	{
		super(context, NOM_DATABASE, null, VERSION_DATABASE);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE_COURSES);
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Patates', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Avocat', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Shampooing', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Riz', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Chocolat', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Oeufs', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Lait', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Pain', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Pâtes', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Viande', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Beurre', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Salade', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Oignons', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Raisins', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Sauce', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Croissant', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Pizza', 0)");
		db.execSQL("INSERT INTO " + BaseContrat.CoursesContrat.TABLE_COURSES + " VALUES (NULL, 'Chips', 0)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + BaseContrat.CoursesContrat.TABLE_COURSES);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		onUpgrade(db, oldVersion, newVersion);
	}

}
