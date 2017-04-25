package com.monentreprise.exercice.items;

public class CourseItem
{

	// Attributs :
	private String libelle = null;


	/**
	 * Constructeur.
	 * @param libelle libellé
	 */
	public CourseItem(String libelle)
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

}
