package com.groovygames.calculbulle.component;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;

import com.groovygames.calculbulle.FontManager;
import com.groovygames.calculbulle.component.TouchableTextComponent.ITouchableTextListener;
import com.groovygames.groovylib.AbstractActivity;

public class HeaderComponent extends AbstractComponent implements ITouchableTextListener
{
	private static final int MARGIN = 10;
	
	private static final String DEFAULT_TEXT = "Score : 0";

	private Text scoreValue;

	private AbstractActivity activity;

	public HeaderComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font darkFont, Font lightFont)
	{
		super(pX, pY, pTiledTextureRegion, activity, darkFont, lightFont);

		this.activity = activity;

		this.initComponent(pX, pY);
		this.attachComponent();
	}

	public HeaderComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font font)
	{
		super(pX, pY, pTiledTextureRegion, activity, font);

		this.activity = activity;

		this.initComponent(pX, pY);
		this.attachComponent();
	}

	private void attachComponent()
	{
		this.attachChild(scoreValue);
	}

	private void initComponent(float pX, float pY)
	{
		Font pFont = FontManager.getInstance().loadFont("CarterOne.ttf", this.activity, 2.5f);

		VertexBufferObjectManager pVertexBufferObjectManager = activity.getVertexBufferObjectManager();

		scoreValue = new TouchableTextComponent( MARGIN, 0, pFont, DEFAULT_TEXT, 16, new TextOptions(HorizontalAlign.CENTER), pVertexBufferObjectManager, this);
	}

	public void updateScore(final int score)
	{
		this.activity.runOnUpdateThread(new Runnable()
		{
			@Override
			public void run()
			{
				scoreValue.setText(String.format("Score : %d", score));
			}
		});
	}

	@Override
	public void doLayout()
	{
		scoreValue.setSize(this.getWidth() - MARGIN * 2, this.getHeight());
	}

	@Override
	public void onAreaTouched(TouchableTextComponent text, TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
	{
	}
}
