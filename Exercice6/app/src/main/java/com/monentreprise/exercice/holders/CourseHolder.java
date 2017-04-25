package com.monentreprise.exercice.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.monentreprise.exercice.R;


public class CourseHolder extends RecyclerView.ViewHolder
{

	// Eléments graphiques :
	private TextView textViewLibelle = null;


	/**
	 * Constructeur.
	 * @param itemView Vue de l'item
	 */
	public CourseHolder(View itemView)
	{
		// appel obligatoire :
		super(itemView);

		// mapping :
		textViewLibelle = (TextView) itemView.findViewById(R.id.libelle_course);
	}

	/**
	 * Getter TextView libellé course.
	 * @return TextView libellé course
	 */
	public TextView getTextViewLibelle()
	{
		return textViewLibelle;
	}

}
