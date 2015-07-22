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

public class ScoreComponent extends AbstractComponent implements ITouchableTextListener
{
	private static final String DEFAULT_OPERAND_CHARACTER = "?";

	private static final String DEFAULT_OPERATOR_CHARACTER = "?";

	private Text equalText;

	private Text resultText;

	private Text operationText;

	private Text firstOperandText;

	private Text secondOperandText;

	private AbstractActivity activity;

	private IScoreTouchListener scoreTouchListener;

	public ScoreComponent(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, AbstractActivity activity, Font font,
			IScoreTouchListener scoreTouchListener)
	{
		super(pX, pY, pTiledTextureRegion, activity, font);

		this.activity = activity;
		this.scoreTouchListener = scoreTouchListener;

		this.initComponent(pX, pY);
		this.attachComponent();
	}

	public void doLayout()
	{
		firstOperandText.setPosition((float) (this.getWidth() * 0.02f), this.getHeight() / 2 - firstOperandText.getHeight() / 2);
		operationText.setPosition((float) (this.getWidth() * 0.22f), this.getHeight() / 2 - operationText.getHeight() / 2);
		secondOperandText.setPosition((float) (this.getWidth() * 0.42f), this.getHeight() / 2 - secondOperandText.getHeight() / 2);
		equalText.setPosition((float) (this.getWidth() * 0.62f), this.getHeight() / 2 - equalText.getHeight() / 2);
		resultText.setPosition((float) (this.getWidth() * 0.72f), this.getHeight() / 2 - resultText.getHeight() / 2);
		
		activity.getEngine().getScene().registerTouchArea(firstOperandText);
		activity.getEngine().getScene().registerTouchArea(secondOperandText);
		activity.getEngine().getScene().registerTouchArea(resultText);
	}

	private void attachComponent()
	{
		this.attachChild(firstOperandText);
		this.attachChild(operationText);
		this.attachChild(secondOperandText);
		this.attachChild(equalText);
		this.attachChild(resultText);
	}

	private void initComponent(float pX, float pY)
	{
		Font pFont = FontManager.getInstance().loadFont("CarterOne.ttf", this.activity, 3.5f);

		VertexBufferObjectManager pVertexBufferObjectManager = activity.getVertexBufferObjectManager();

		firstOperandText = new TouchableTextComponent(0, 0, pFont, DEFAULT_OPERAND_CHARACTER, 2, new TextOptions(HorizontalAlign.CENTER),
				pVertexBufferObjectManager, this);
		operationText = new TouchableTextComponent(0, 0, pFont, DEFAULT_OPERATOR_CHARACTER, new TextOptions(HorizontalAlign.CENTER), pVertexBufferObjectManager);
		secondOperandText = new TouchableTextComponent(0, 0, pFont, DEFAULT_OPERAND_CHARACTER, 2, new TextOptions(HorizontalAlign.CENTER),
				pVertexBufferObjectManager, this);
		equalText = new TouchableTextComponent(0, 0, pFont, "=", new TextOptions(HorizontalAlign.CENTER), pVertexBufferObjectManager);
		resultText = new TouchableTextComponent(0, 0, pFont, DEFAULT_OPERAND_CHARACTER, 2, new TextOptions(HorizontalAlign.CENTER), pVertexBufferObjectManager,
				this);
	}

	public void updateFirst(int i)
	{
		this.updateText(firstOperandText, (i > 0) ? String.valueOf(i) : DEFAULT_OPERAND_CHARACTER);
	}

	public void updateSecond(int i)
	{
		this.updateText(secondOperandText, (i > 0) ? String.valueOf(i) : DEFAULT_OPERAND_CHARACTER);
	}

	public void updateResult(int i)
	{
		this.updateText(resultText, (i > 0) ? String.valueOf(i) : DEFAULT_OPERAND_CHARACTER);
	}

	public void updateOperator(String operator)
	{
		this.updateText(operationText, (operator != null) ? operator : DEFAULT_OPERATOR_CHARACTER);
	}

	private void updateText(final Text text, final String value)
	{
		this.activity.runOnUpdateThread(new Runnable()
		{
			@Override
			public void run()
			{
				text.setText(value);
			}
		});
	}

	@Override
	public void onAreaTouched(TouchableTextComponent text, TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
	{
		if (null != scoreTouchListener)
		{
			if (text == firstOperandText)
				scoreTouchListener.onFirstOperandTouch();

			if (text == secondOperandText)
				scoreTouchListener.onSecondOperandTouch();

			if (text == resultText)
				scoreTouchListener.onResultOperandTouch();
		}
	}

	public interface IScoreTouchListener
	{
		public void onFirstOperandTouch();

		public void onSecondOperandTouch();

		public void onResultOperandTouch();
	}
}
