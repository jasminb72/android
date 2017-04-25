package com.monentreprise.exercice.vues;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.monentreprise.exercice.R;


public class RondView extends View
{

	// Pinceau :
	private Paint paint = null;

	// Dimensions :
	private float largeur = 0;
	private float hauteur = 0;


	/**
	 * Constructeur.
	 * @param context Context
	 */
	public RondView(Context context)
	{
		super(context);
		init(context, null); // normalement jamais appelé
	}

	/**
	 * Constructeur.
	 * @param context Context
	 * @param attrs Attributs
	 */
	public RondView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}

	/**
	 * Constructeur.
	 * @param context Context
	 * @param attrs Attributs
	 * @param defStyleAttr Style
	 */
	public RondView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	/**
	 * Suite constructeur.
	 * @param context Context
	 * @param attrs Attributs
	 */
	private void init(Context context, AttributeSet attrs)
	{
		if (context != null && attrs != null)
		{
			TypedArray arguments = context.obtainStyledAttributes(attrs, R.styleable.RondView);
			int couleur = arguments.getColor(R.styleable.RondView_couleurPerso, 0);

			// ne pas oublier !
			arguments.recycle();

			// pinceau :
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setColor(couleur);
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);

		// calcul des dimensions :
		largeur = right - left;
		hauteur = bottom - top;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		// dessin du rond :
		canvas.drawCircle(largeur / 2, hauteur / 2, largeur / 2, paint);
	}

}
