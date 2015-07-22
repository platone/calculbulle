package com.groovygames.calculbulle.component;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

import com.groovygames.groovylib.AbstractActivity;

abstract public class AbstractComponent extends AnimatedSprite
{
	private Font darkFont;

	private Font lightFont;
	
	private AbstractActivity activity;

	public AbstractComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font font)
	{
		this(pX, pY, pTiledTextureRegion, activity, font, font);
	}

	public AbstractComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font darkFont, Font lightFont)
	{
		super(pX, pY, pTiledTextureRegion, activity.getVertexBufferObjectManager());

		this.setDarkFont(darkFont);
		this.setLightFont(lightFont);
		this.setActivity(activity);
		
		this.attachUpdateHandler();
	}

	private void attachUpdateHandler()
	{
		this.registerUpdateHandler(new IUpdateHandler()
		{
			@Override
			public void onUpdate(float pSecondsElapsed)
			{
				doLayout();
			}

			@Override
			public void reset()
			{
				doLayout();
			}
		});
	}

	abstract public void doLayout();
	
	public VertexBufferObjectManager getVertexBufferObjectManager()
	{
		return this.getActivity().getVertexBufferObjectManager();
	}
	
	public AbstractActivity getActivity()
	{
		return activity;
	}

	public void setActivity(AbstractActivity activity)
	{
		this.activity = activity;
	}

	public Font getDarkFont()
	{
		return darkFont;
	}

	public void setDarkFont(Font darkFont)
	{
		this.darkFont = darkFont;
	}

	public Font getLightFont()
	{
		return lightFont;
	}

	public void setLightFont(Font lightFont)
	{
		this.lightFont = lightFont;
	}
}
