package com.groovygames.calculbulle.component;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;

import com.groovygames.groovylib.AbstractActivity;

public class BulleComponent extends AbstractComponent
{
	private int value;

	private int column;

	private int raw;

	private boolean selected = false;

	private Text valueText;

	private ITouchController touchController;

	private boolean animated;

	public BulleComponent(int value, int column, int raw, float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity,
			ITouchController touchController, Font darkFont, boolean animated)
	{
		this(value, column, raw, pX, pY, pTiledTextureRegion, activity, touchController, darkFont, darkFont, animated);
	}

	public BulleComponent(int value, int column, int raw, float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity,
			ITouchController touchController, Font darkFont, Font lightFont, boolean animated)
	{
		super(pX, pY, pTiledTextureRegion, activity, darkFont, lightFont);

		this.raw = raw;
		this.column = column;
		this.value = value;
		this.touchController = touchController;
		this.animated = animated;

		this.initComponent(pX, pY);
		this.updateTileIndex();
		this.doLayout();
		this.attachComponent();
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
	{
		if (pSceneTouchEvent.isActionUp() && touchController.acceptTouch(this))
		{
			if (animated)
			{
				valueText.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

				valueText.registerEntityModifier(new SequenceEntityModifier(new ParallelEntityModifier(new ScaleModifier(0.25f, 1.0f, 4.0f),
						new FadeOutModifier(0.25f)), new ParallelEntityModifier(new ScaleModifier(0.25f, 4.0f, 1.0f), new FadeInModifier(0.25f))));
			}

			this.touchController.fireTouch(this);
		}

		return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}

	private void updateBackground()
	{
		if (!this.isSelected())
			this.setAlpha(1.0f);
		else
			this.setAlpha(0.75f);
	}

	private void updateTileIndex()
	{
		this.setCurrentTileIndex(value > 9 ? 9 : value - 1);
	}

	public void doLayout()
	{
		valueText.setPosition(this.getWidth() / 2 - valueText.getWidth() / 2, this.getHeight() / 2 - valueText.getHeight() / 2);
	}

	private void attachComponent()
	{
		this.attachChild(valueText);
	}

	private void initComponent(float pX, float pY)
	{
		final Font font = (value > 9 ? this.getDarkFont() : this.getLightFont());

		VertexBufferObjectManager pVertexBufferObjectManager = this.getVertexBufferObjectManager();

		valueText = new Text(0.0f, 0.0f, font, String.valueOf(this.value), new TextOptions(HorizontalAlign.CENTER), pVertexBufferObjectManager);
	}

	public int getColumn()
	{
		return column;
	}

	public int getRaw()
	{
		return raw;
	}

	public int getValue()
	{
		return value;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
		
		this.updateBackground();
	}
}
