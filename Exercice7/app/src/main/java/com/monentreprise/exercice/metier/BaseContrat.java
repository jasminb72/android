package com.monentreprise.exercice.metier;

import android.provider.BaseColumns;

public final class BaseContrat
{

	/**
	 * Constructeur privé afin de ne pas instancier la classe.
	 */
	private BaseContrat() {};

	/**
	 * Contenu de la table "courses".
	 */
	public static class CoursesContrat implements BaseColumns
	{
		public static final String TABLE_COURSES = "courses";
		public static final String COLONNE_INTITULE = "intitule";
		public static final String COLONNE_VALIDE = "valide";
	}

}
