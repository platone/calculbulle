package com.groovygames.groovylib.component;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;

public class TextButtonSprite extends ButtonSprite
{
	private final Text mText;

	public TextButtonSprite(final float pX, final float pY, final ITextureRegion pNormalTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager, final String pText, final IFont pFont)
	{
		super(pX, pY, pNormalTextureRegion, pVertexBufferObjectManager);

		mText = new Text(pX, pX, pFont, pText, new TextOptions(HorizontalAlign.CENTER), pVertexBufferObjectManager);

		this.registerUpdateHandler(new IUpdateHandler()
		{

			@Override
			public void onUpdate(float pSecondsElapsed)
			{
				updateTextDimension();
			}

			@Override
			public void reset()
			{
				updateTextDimension();
			}
		});

		attachChild(mText);
	}

	private void updateTextDimension()
	{
		final float textHeight = mText.getHeight();
		final float buttonHeight = this.getHeight();

		final float textWidth = mText.getWidth();
		final float buttonWidth = this.getWidth();

		final float positionX = buttonWidth / 2 - textWidth / 2;
		final float positionY = buttonHeight / 2 - textHeight / 2;

		mText.setX(positionX);
		mText.setY(positionY);
	}

	@Override
	public void registerUpdateHandler(IUpdateHandler pUpdateHandler)
	{
		super.registerUpdateHandler(pUpdateHandler);
	}

	public void setBlendFunction(final int pBlendFunctionSource, final int pBlendFunctionDestination)
	{
		mText.setBlendFunction(pBlendFunctionSource, pBlendFunctionDestination);
	}

	public void setAlpha(final float pAlpha)
	{
		super.setAlpha(pAlpha);
		mText.setAlpha(pAlpha);
	}

	public void setColor(final float pRed, final float pGreen, final float pBlue)
	{
		mText.setColor(pRed, pGreen, pBlue);
	}
}