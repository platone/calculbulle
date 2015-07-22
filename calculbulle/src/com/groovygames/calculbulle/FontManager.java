package com.groovygames.calculbulle;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

import com.groovygames.groovylib.AbstractActivity;
import com.groovygames.groovylib.utils.ScreenUtils;

public class FontManager
{
	static private FontManager instance;

	private FontManager()
	{
	}

	public static final FontManager getInstance()
	{
		if (FontManager.instance == null)
		{
			synchronized (FontManager.class)
			{
				if (FontManager.instance == null)
				{
					FontManager.instance = new FontManager();
				}
			}
		}

		return FontManager.instance;
	}

	public Font loadFont(String fontFile, AbstractActivity activity)
	{
		return this.loadFont(fontFile, activity, 1.0f);
	}

	public Font loadFont(String fontFile, AbstractActivity activity, float ratio)
	{
		int density = ScreenUtils.getDensity();

		int fontSize = (int) ((22 * density) * ratio);

		final Font font = FontFactory.createFromAsset(activity.getFontManager(), activity.getTextureManager(), 1024, 1024, activity.getAssets(), fontFile,
				fontSize, true, android.graphics.Color.WHITE);

		font.load();

		return font;
	}

	public Font defaultFont(AbstractActivity activity)
	{
		BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256);

		Font font = new Font(activity.getFontManager(), bitmapTextureAtlas, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.BLACK);

		activity.getTextureManager().loadTexture(bitmapTextureAtlas);

		activity.getFontManager().loadFont(font);

		return font;
	}
}
