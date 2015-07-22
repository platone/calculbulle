package com.groovygames.groovylib.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.groovygames.groovylib.ContextManager;

public class ScreenUtils
{
	static private int density = -1;

	static private int[] resolution = null;

	static public int[] getResolution()
	{
		if (null == resolution)
		{
			final DisplayMetrics metrics = new DisplayMetrics();
			final Context context = ContextManager.getInstance().getContext();
			final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

			windowManager.getDefaultDisplay().getMetrics(metrics);

			resolution = new int[] { metrics.widthPixels, metrics.heightPixels };
		}

		return resolution;
	}

	static public int getWidth()
	{
		return ScreenUtils.getResolution()[0];
	}

	static public int getHeight()
	{
		return ScreenUtils.getResolution()[1];
	}
	
	static public int[] getSquare()
	{
		final int min = Math.min(ScreenUtils.getWidth(), ScreenUtils.getHeight());
		
		return new int[] { min, min };
	}
	
	static public int getCenterX()
	{
		return ScreenUtils.getWidth() / 2;
	}

	static public int getCenterY()
	{
		return ScreenUtils.getHeight() / 2;
	}

	static public int getDensity()
	{
		if (-1 == density)
		{
			Context context = ContextManager.getInstance().getContext();

			WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

			Display display = windowManager.getDefaultDisplay();

			DisplayMetrics displayMetrics = new DisplayMetrics();
			display.getMetrics(displayMetrics);

			density = (int) (displayMetrics.density);
		}

		return density;
	}
}
