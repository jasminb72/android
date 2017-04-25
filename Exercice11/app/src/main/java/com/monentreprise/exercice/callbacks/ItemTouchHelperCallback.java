package com.monentreprise.exercice.callbacks;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.monentreprise.exercice.adapters.CoursesAdapter;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback
{

	// Adapter :
	private CoursesAdapter adapter;

	// Couleur sélection :
	private int couleurSelection = 0;


	/**
	 * Constructeur.
	 * @param adapter Adapteur
	 * @param couleurSelection Couleur fond sélection
	 */
	public ItemTouchHelperCallback(CoursesAdapter adapter, int couleurSelection)
	{
		this.adapter = adapter;
		this.couleurSelection = couleurSelection;
	}
	
	@Override
	public boolean isLongPressDragEnabled()
	{
		return true;
	}
	
	@Override
	public boolean isItemViewSwipeEnabled()
	{
		return true;
	}
	
	@Override
	public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder)
	{
		int dragFlagsUpDown = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		int dragFlagsLeftRight = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
		return makeMovementFlags(dragFlagsUpDown, dragFlagsLeftRight);
	}
	
	@Override
	public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target)
	{
		adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}
	
	@Override
	public void onSwiped(ViewHolder viewHolder, int direction)
	{
		adapter.onItemDismiss(viewHolder.getAdapterPosition());
	}

	@Override
	public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState)
	{
		// change la couleur de fond au début du drag & drop :
		if (viewHolder != null && viewHolder.itemView != null)
		{
			viewHolder.itemView.setBackgroundColor(couleurSelection);
		}
		super.onSelectedChanged(viewHolder, actionState);
	}

	@Override
	public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
	{
		// remet la couleur de fond à l'état initial, une fois le drag & drop terminé :
		if (viewHolder != null && viewHolder.itemView != null)
		{
			viewHolder.itemView.setBackgroundColor(0);
		}
		super.clearView(recyclerView, viewHolder);
	}

}
