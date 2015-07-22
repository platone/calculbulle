package com.groovygames.calculbulle.component;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import com.groovygames.groovylib.AbstractActivity;

public class PlateComponent extends AbstractComponent
{
	public PlateComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font darkFont, Font lightFont)
	{
		super(pX, pY, pTiledTextureRegion, activity, darkFont, lightFont);
	}

	public PlateComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font font)
	{
		super(pX, pY, pTiledTextureRegion, activity, font);
	}

	@Override
	public void doLayout()
	{
	}
}
