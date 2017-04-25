package com.monentreprise.exercice.metier.dto;

import android.database.Cursor;

import com.monentreprise.exercice.metier.BaseContrat;

public class CourseDTO
{

	// Attributs :
	private String libelle = null;
	private int etatValide = 0;


	/**
	 * Constructeur.
	 * @param libelle libellé
	 * @param etatValide 0 si invalide, 1 si valide (course achetée par exemple)
	 */
	public CourseDTO(String libelle, int etatValide)
	{
		this.libelle = libelle;
	}

	/**
	 * Getter libellé
	 * @return libelle
	 */
	public String getLibelle()
	{
		return libelle;
	}

	/**
	 * Setter libellé
	 * @param libelle libellé
	 */
	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}

	/**
	 * Getter état valide
	 * @return Etat valide
	 */
	public int getEtatValide()
	{
		return etatValide;
	}

	/**
	 * Setter état valide
	 * @param etatValide Etat valide
	 */
	public void setEtatValide(int etatValide)
	{
		this.etatValide = etatValide;
	}


}
