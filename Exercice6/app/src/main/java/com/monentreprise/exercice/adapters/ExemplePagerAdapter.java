package com.monentreprise.exercice.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.monentreprise.exercice.fragments.ExempleFragment;

public class ExemplePagerAdapter extends FragmentStatePagerAdapter
{

	// Constantes :
	private static final int NOMBRE_PAGES = 2;


	// Constructeur
	public ExemplePagerAdapter(FragmentManager fragmentManager)
	{
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position)
	{
		// le switch est ici pour montrer l'exemple, dans le cas où on veut plusieurs fragments différents.
		// ici un simple return new ExempleFragment(); aurait suffi :
		switch (position)
		{
			case 1:
				return new ExempleFragment();
			default:
				return new ExempleFragment();
		}
	}

	@Override
	public int getCount()
	{
		return NOMBRE_PAGES;
	}

}