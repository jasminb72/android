package com.monentreprise.exercice.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.monentreprise.exercice.R;
import com.monentreprise.exercice.holders.CourseHolder;
import com.monentreprise.exercice.metier.dto.CourseDTO;

import java.util.Collections;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CourseHolder>
{

	// Liste d'items :
	private List<CourseDTO> listeCoursesItems = null;


	/**
	 * Constructeur.
	 * @param listeCoursesItems Liste d'items
	 */
	public CoursesAdapter(List<CourseDTO> listeCoursesItems)
	{
		this.listeCoursesItems = listeCoursesItems;
	}

	@Override
	public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		// construction du holder qui garde une référence sur les vues de chaque item pour des raisons de performance.
		// ATTENTION : bien mettre "wrap_content" comme hauteur dans le layout racine de l'item !
		return new CourseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_course, parent, false));
	}

	@Override
	public void onBindViewHolder(CourseHolder holder, int position)
	{
		// mise à jour de chaque item :
		holder.getTextViewLibelle().setText(listeCoursesItems.get(position).getLibelle());
	}

	@Override
	public int getItemCount()
	{
		return listeCoursesItems.size();
	}

	/**
	 * Déplacement.
	 * @param fromPosition Position début
	 * @param toPosition Position fin
	 * @return True si déplacé
	 */
	public boolean onItemMove(int fromPosition, int toPosition)
	{
		Log.i("CoursesAdapter", "onItemMove(" + fromPosition + ", " + toPosition + ")");

		Collections.swap(listeCoursesItems, fromPosition, toPosition);
		notifyItemMoved(fromPosition, toPosition);
		return true;
	}

	/**
	 * Suppression.
	 * @param position Position à supprimer
	 */
	public void onItemDismiss(int position)
	{
		if (position > -1)
		{
			listeCoursesItems.remove(position);
			notifyItemRemoved(position);
		}
	}

}
