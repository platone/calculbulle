package com.groovygames.calculbulle.component;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TouchableTextComponent extends Text
{
	private ITouchableTextListener touchableTextListener;

	public TouchableTextComponent(float pX, float pY, IFont pFont, CharSequence pText, TextOptions pTextOptions,
			VertexBufferObjectManager pVertexBufferObjectManager)
	{
		this(pX, pY, pFont, pText, null != pText ? pText.length() : 1, pTextOptions, pVertexBufferObjectManager, null);
	}
	
	public TouchableTextComponent(float pX, float pY, IFont pFont, CharSequence pText, TextOptions pTextOptions,
			VertexBufferObjectManager pVertexBufferObjectManager, ITouchableTextListener touchableTextListener)
	{
		this(pX, pY, pFont, pText, null != pText ? pText.length() : 1, pTextOptions, pVertexBufferObjectManager, touchableTextListener);
	}

	public TouchableTextComponent(float pX, float pY, IFont pFont, CharSequence pText, int pCharactersMaximum, TextOptions pTextOptions,
			VertexBufferObjectManager pVertexBufferObjectManager)
	{
		this(pX, pY, pFont, pText, pCharactersMaximum, pTextOptions, pVertexBufferObjectManager, null);
	}
	
	public TouchableTextComponent(float pX, float pY, IFont pFont, CharSequence pText, int pCharactersMaximum, TextOptions pTextOptions,
			VertexBufferObjectManager pVertexBufferObjectManager, ITouchableTextListener touchableTextListener)
	{
		super(pX, pY, pFont, pText, pCharactersMaximum, pTextOptions, pVertexBufferObjectManager);

		this.touchableTextListener = touchableTextListener;
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
	{

		if (null != touchableTextListener)
			touchableTextListener.onAreaTouched(this, pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);

		return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}

	interface ITouchableTextListener
	{
		public void onAreaTouched(TouchableTextComponent text, TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY);
	}
}
