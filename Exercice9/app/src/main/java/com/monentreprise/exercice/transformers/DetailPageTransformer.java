package com.monentreprise.exercice.transformers;

import android.support.v4.view.ViewPager;
import android.view.View;


public class DetailPageTransformer implements ViewPager.PageTransformer
{

	@Override
	public void transformPage(View view, float position)
	{
		// init :
		int pageWidth = view.getWidth();

		if (position <= -1)
		{
			// page en dehors de l'écran, à gauche :
			view.setAlpha(0);
			view.setTranslationX(0);
		}
		else if (position <= 0)
		{
			view.setAlpha(position + 1);
			view.setTranslationX(pageWidth * -position * 0.5F);
		}
		else if (position < 1)
		{
			view.setAlpha(1 - position);
			view.setTranslationX(pageWidth * -position * 0.5F);
		}
		else
		{
			// page en dehors de l'écran, à droite :
			view.setAlpha(0);
			view.setTranslationX(0);
		}
	}

}
