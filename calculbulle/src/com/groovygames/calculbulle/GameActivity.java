package com.groovygames.calculbulle;

import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.groovygames.calculbulle.andengine.BackgroundFactory;
import com.groovygames.calculbulle.component.BulleComponent;
import com.groovygames.calculbulle.component.HeaderComponent;
import com.groovygames.calculbulle.component.ITouchController;
import com.groovygames.calculbulle.component.PlateComponent;
import com.groovygames.calculbulle.component.ScoreComponent;
import com.groovygames.calculbulle.component.ScoreComponent.IScoreTouchListener;
import com.groovygames.groovylib.AbstractActivity;
import com.groovygames.groovylib.resources.ResourceManager;
import com.groovygames.groovylib.utils.ScreenUtils;
import com.groovygames.groovymath.IAction;
import com.groovygames.groovymath.IFillInAction;
import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.IOperation;
import com.groovygames.groovymath.MatrixFactory;
import com.groovygames.groovymath.MatrixNavigator;
import com.groovygames.groovymath.MatrixUtils;
import com.groovygames.groovymath.RandomUtils;
import com.groovygames.groovymath.impl.Matrix;

public class GameActivity extends AbstractActivity implements ITouchController, IScoreTouchListener
{
	private static final int GAME_MARGIN_WIDTH = 5;
	private static final int GAME_MARGIN_HEIGHT = 5;

	private static final int PLATE_MARGIN_WIDTH = 30;
	private static final int PLATE_MARGIN_HEIGHT = 30;

	private Font lightFont;

	private Font darkFont;

	private IMatrix matrix;

	private TiledTextureRegion textureRegion;

	private TiledTextureRegion textureRegionScore;

	private TiledTextureRegion textureRegionExplosion;

	private TiledTextureRegion plateTextureRegion;

	private TiledTextureRegion headerTextureRegion;

	private ScoreComponent scoreComponent;

	private BulleComponent firstOperand;

	private BulleComponent secondOperand;

	private BulleComponent resultOperand;

	private BulleComponent lastOperand;

	private int min;

	private int score;

	private IFillInAction action;

	private HeaderComponent headerComponent;

	private PlateComponent plateComponent;
	private boolean animated;

	@Override
	protected IBackground createBackground()
	{
		return BackgroundFactory.createImageBackground(this.getTextureManager(), 512, 1024, "background_level_1.jpg", this.getVertexBufferObjectManager());
	}

	@Override
	protected void loadResources()
	{
		super.loadResources();

		this.loadFont();

		BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas, this, "bulles.png", 0, 0, 10, 1);
		bitmapTextureAtlas.load();

		BitmapTextureAtlas bitmapTextureAtlasScore = new BitmapTextureAtlas(getTextureManager(), 1024, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		textureRegionScore = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlasScore, this, "background_brown.png", 0, 0, 1, 1);
		bitmapTextureAtlasScore.load();

		BitmapTextureAtlas bitmapTextureAtlasExplosione = new BitmapTextureAtlas(getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		textureRegionExplosion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlasExplosione, this, "explosion.png", 0, 0, 7, 7);
		bitmapTextureAtlasExplosione.load();

		BitmapTextureAtlas bitmapTextureAtlasPlate = new BitmapTextureAtlas(getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		plateTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlasPlate, this, "plate.png", 0, 0, 1, 1);
		bitmapTextureAtlasPlate.load();

		BitmapTextureAtlas bitmapTextureAtlasHeader = new BitmapTextureAtlas(getTextureManager(), 512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		headerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlasHeader, this, "header_background.png", 0, 0, 1, 1);
		bitmapTextureAtlasHeader.load();

		if (null != this.getIntent().getExtras())
			animated = this.getIntent().getExtras().getBoolean("ANIMATED");
	}

	private void loadFont()
	{
		WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		Display display = windowManager.getDefaultDisplay();

		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);

		int density = (int) (displayMetrics.density);

		int fontSize = (int) (25 * density);
		lightFont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 1024, 1024, this.getAssets(), "CarterOne.ttf", fontSize, true,
				android.graphics.Color.WHITE);
		lightFont.load();

		darkFont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 1024, 1024, this.getAssets(), "CarterOne.ttf", fontSize, true,
				android.graphics.Color.BLACK);
		darkFont.load();
	}

	@Override
	public void onPopulateScene(final Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception
	{
		this.initDimension();
		this.initAction();
		this.initMatrix();
		this.initHeader();
		this.initPlate();
		this.initScore();
		this.initBulles();

		pScene.setTouchAreaBindingOnActionDownEnabled(true);

		super.onPopulateScene(pScene, pOnPopulateSceneCallback);
	}

	private void initBulles()
	{

		MatrixNavigator.navigate(matrix, new IAction()
		{
			@Override
			public void execute(IMatrix matrix, int c, int r)
			{
				int value = matrix.get(c, r);

				addBulles(value, c, r);
			}
		});
	}

	private void initScore()
	{
		final float x = GAME_MARGIN_HEIGHT;
		final float y = (int) ((float) ScreenUtils.getHeight() * 0.80);
		final float w = ScreenUtils.getWidth() - GAME_MARGIN_HEIGHT * 2;
		final float h = ScreenUtils.getHeight() / 6;

		scoreComponent = new ScoreComponent(x, y, textureRegionScore, this, darkFont, this);

		scoreComponent.setSize(w, h);

		getEngine().getScene().attachChild(scoreComponent);
	}

	private void initDimension()
	{
		final int widthRatio = (ScreenUtils.getWidth() - GAME_MARGIN_WIDTH * 2) / this.getColumn();
		final int heightRatio = (ScreenUtils.getHeight() - GAME_MARGIN_HEIGHT * 2) / this.getRaw();

		min = Math.min(widthRatio, heightRatio);
	}

	private void initPlate()
	{
		final int[] square = ScreenUtils.getSquare();

		final float x = ScreenUtils.getCenterX() - square[0] / 2 + GAME_MARGIN_WIDTH;
		final float y = ScreenUtils.getCenterY() - square[1] / 2 + GAME_MARGIN_HEIGHT;
		final float w = square[0] - GAME_MARGIN_WIDTH * 2;
		final float h = square[1] - GAME_MARGIN_HEIGHT * 2;

		plateComponent = new PlateComponent(x, y, plateTextureRegion, GameActivity.this, darkFont, lightFont);

		plateComponent.setSize(w, h);

		getEngine().getScene().attachChild(plateComponent);
	}

	private void initHeader()
	{
		final float x = GAME_MARGIN_WIDTH;
		final float y = GAME_MARGIN_HEIGHT;
		final float w = this.getWidth(1.0f) - GAME_MARGIN_WIDTH * 2;
		final float h = this.getHeight(0.15f) - GAME_MARGIN_HEIGHT * 2;

		headerComponent = new HeaderComponent(x, y, headerTextureRegion, GameActivity.this, darkFont, lightFont);

		headerComponent.setSize(w, h);

		getEngine().getScene().attachChild(headerComponent);
	}

	private float getWidth(float ratio)
	{
		return ScreenUtils.getWidth() * ratio;
	}

	private float getHeight(float ratio)
	{
		return ScreenUtils.getHeight() * ratio;
	}

	private void initMatrix()
	{
		matrix = MatrixFactory.createMatrix(this.getColumn(), this.getRaw(), action);
	}

	private void initAction()
	{
		action = new MedianRandomAction();
	}

	private void addBulles(int value, int c, int r)
	{
		final float x = plateComponent.getX() + PLATE_MARGIN_WIDTH;
		final float y = plateComponent.getY() + PLATE_MARGIN_HEIGHT;
		final float w = (plateComponent.getWidth() - PLATE_MARGIN_WIDTH * 2) / matrix.getWidth();
		final float h = (plateComponent.getHeight() - PLATE_MARGIN_HEIGHT * 2) / matrix.getHeight();

		final BulleComponent bulle = new BulleComponent(value, c, r, x + c * w, y + r * h, textureRegion, GameActivity.this, GameActivity.this, darkFont,
				lightFont, animated);

		bulle.setSize(w, h);
		bulle.setAlpha(0.0f);
		if (animated)
			bulle.registerEntityModifier(new FadeInModifier(0.25f));
		else
			bulle.registerEntityModifier(new FadeInModifier(0.50f));

		getEngine().getScene().attachChild(bulle);
		getEngine().getScene().registerTouchArea(bulle);
	}

	@Override
	public boolean acceptTouch(BulleComponent bulleComponent)
	{
		if (null == firstOperand || null == secondOperand || null == resultOperand || bulleComponent == firstOperand || bulleComponent == secondOperand
				|| bulleComponent == resultOperand)
			return true;

		return false;
	}

	@Override
	public void fireTouch(BulleComponent bulleComponent)
	{
		if (bulleComponent.isSelected())
			this.deselect(bulleComponent);
		else
			this.select(bulleComponent);
	}

	private void deselect(BulleComponent bulleComponent)
	{
		if (bulleComponent == this.firstOperand)
			this.firstOperand = null;

		if (bulleComponent == this.secondOperand)
			this.secondOperand = null;

		if (bulleComponent == this.resultOperand)
			this.resultOperand = null;

		bulleComponent.setSelected(false);

		this.updateScore();
	}

	private void select(BulleComponent bulleComponent)
	{
		if (null == this.firstOperand && bulleComponent != this.secondOperand && bulleComponent != this.resultOperand)
			this.firstOperand = bulleComponent;

		if (bulleComponent != this.firstOperand && null == this.secondOperand && bulleComponent != this.resultOperand)
			this.secondOperand = bulleComponent;

		if (bulleComponent != this.firstOperand && bulleComponent != this.secondOperand && null == this.resultOperand)
			this.resultOperand = bulleComponent;

		this.lastOperand = bulleComponent;

		bulleComponent.setSelected(true);

		this.updateScore();
	}

	private void updateScore()
	{
		IOperation operation = null;

		this.scoreComponent.updateFirst(null != this.firstOperand ? this.firstOperand.getValue() : -1);
		this.scoreComponent.updateSecond(null != this.secondOperand ? this.secondOperand.getValue() : -1);
		this.scoreComponent.updateResult(null != this.resultOperand ? this.resultOperand.getValue() : -1);

		if (null != this.firstOperand && null != this.secondOperand && null != this.resultOperand)
		{
			operation = MatrixUtils.createOperation(matrix, firstOperand.getColumn(), firstOperand.getRaw(), secondOperand.getColumn(), secondOperand.getRaw(),
					resultOperand.getColumn(), resultOperand.getRaw());
		}

		final IOperation removeOperation = operation;

		this.scoreComponent.updateOperator(null != operation ? operation.toString() : null);

		if (null != this.firstOperand && null != this.secondOperand && null != this.resultOperand)
		{
			getEngine().getScene().setTouchAreaBindingOnActionDownEnabled(false);

			if (operation != null)
			{
				this.runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						score += removeOperation.getScore();

						if(animated)
							Toast.makeText(GameActivity.this, "Your score is : " + score, Toast.LENGTH_LONG).show();
						
						headerComponent.updateScore(score);

						if (animated)
						{
							try
							{
								Thread.sleep(1500);
							}
							catch (InterruptedException e)
							{
								e.printStackTrace();
							}

							OnExplodeBulle onExplodeBulle = new OnExplodeBulle()
							{

								@Override
								public void onAnimationFinished()
								{
									super.onAnimationFinished();

									if (index == 3)
									{
										MatrixUtils.removeOperation(removeOperation, matrix);
										MatrixUtils.fillIn(matrix, action);

										addBulles(matrix.get(firstOperand.getColumn(), firstOperand.getRaw()), firstOperand.getColumn(), firstOperand.getRaw());
										addBulles(matrix.get(secondOperand.getColumn(), secondOperand.getRaw()), secondOperand.getColumn(),
												secondOperand.getRaw());
										addBulles(matrix.get(resultOperand.getColumn(), resultOperand.getRaw()), resultOperand.getColumn(),
												resultOperand.getRaw());

										firstOperand = null;
										secondOperand = null;
										resultOperand = null;
										lastOperand = null;

										updateScore();
									}
								}
							};

							explode(firstOperand, textureRegionExplosion, onExplodeBulle);
							explode(secondOperand, textureRegionExplosion, onExplodeBulle);
							explode(resultOperand, textureRegionExplosion, onExplodeBulle);
						}
						else
						{
							MatrixUtils.removeOperation(removeOperation, matrix);
							MatrixUtils.fillIn(matrix, action);

							firstOperand.setVisible(false);
							secondOperand.setVisible(false);
							resultOperand.setVisible(false);

							getEngine().getScene().unregisterTouchArea(firstOperand);
							getEngine().getScene().detachChild(firstOperand);

							getEngine().getScene().unregisterTouchArea(secondOperand);
							getEngine().getScene().detachChild(secondOperand);

							getEngine().getScene().unregisterTouchArea(resultOperand);
							getEngine().getScene().detachChild(resultOperand);

							addBulles(matrix.get(firstOperand.getColumn(), firstOperand.getRaw()), firstOperand.getColumn(), firstOperand.getRaw());
							addBulles(matrix.get(secondOperand.getColumn(), secondOperand.getRaw()), secondOperand.getColumn(), secondOperand.getRaw());
							addBulles(matrix.get(resultOperand.getColumn(), resultOperand.getRaw()), resultOperand.getColumn(), resultOperand.getRaw());

							firstOperand = null;
							secondOperand = null;
							resultOperand = null;
							lastOperand = null;

							updateScore();
						}

						getEngine().getScene().setTouchAreaBindingOnActionDownEnabled(true);
					}
				});
			}
			else
			{
				this.runOnUiThread(new Runnable()
				{

					@Override
					public void run()
					{
						Toast.makeText(GameActivity.this, "ERROR", Toast.LENGTH_LONG).show();

						try
						{
							Thread.sleep(1500);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}

						if (lastOperand == firstOperand)
							firstOperand = lastOperand = null;

						if (lastOperand == secondOperand)
							secondOperand = lastOperand = null;

						if (lastOperand == resultOperand)
							resultOperand = lastOperand = null;

						updateScore();

						getEngine().getScene().setTouchAreaBindingOnActionDownEnabled(true);
					}
				});
			}
		}
	}

	public void explode(final BulleComponent bulleComponent, TiledTextureRegion textureRegionExplosion, final OnExplodeBulle onExplodeBulle)
	{
		final AnimatedSprite sprite = new AnimatedSprite((bulleComponent.getWidth() * -0.15f) + bulleComponent.getX(),
				(bulleComponent.getHeight() * -0.15f + bulleComponent.getY()), textureRegionExplosion, this.getVertexBufferObjectManager());

		sprite.setSize(bulleComponent.getWidth() * 1.3f, bulleComponent.getHeight() * 1.3f);

		long[] frameDurration = { 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };

		final int w = frameDurration.length / 2;

		sprite.animate(frameDurration, new IAnimationListener()
		{

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount)
			{
			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex, int pNewFrameIndex)
			{
				if (pOldFrameIndex == 14)
				{
					bulleComponent.setVisible(false);
				}
			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount, int pInitialLoopCount)
			{
				onExplodeBulle.onAnimationFinished();

				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						getEngine().getScene().unregisterTouchArea(sprite);
						getEngine().getScene().detachChild(sprite);
					}
				});
			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite)
			{

			}
		});

		getEngine().getScene().attachChild(sprite);
	}

	@Override
	public void onFirstOperandTouch()
	{
		if (null != firstOperand)
			deselect(firstOperand);
	}

	@Override
	public void onSecondOperandTouch()
	{
		if (null != secondOperand)
			deselect(secondOperand);
	}

	@Override
	public void onResultOperandTouch()
	{
		if (null != resultOperand)
			deselect(resultOperand);
	}

	private int getColumn()
	{
		return 7;
	}

	private int getRaw()
	{
		return 7;
	}

	class OnExplodeBulle
	{
		public int index;

		public void onAnimationFinished()
		{
			index++;
		}
	}

	class MedianRandomAction implements IFillInAction
	{
		private double matricRandomMaxValue;

		private double matricRandomMedianValue;

		public MedianRandomAction()
		{
			super();

			matricRandomMedianValue = Double.valueOf(ResourceManager.getInstance().get(R.string.DEFAULT_RANDOM_MATRIC_MEDIAN_VALUE));
			matricRandomMaxValue = Double.valueOf(ResourceManager.getInstance().get(R.string.DEFAULT_RANDOM_MATRIC_MAX_VALUE));
		}

		@Override
		public void execute(IMatrix matrix, int x, int y)
		{
			if (matrix.get(x, y) == Matrix.EMPTY)
			{
				double random = Math.random();

				if (random > matricRandomMedianValue)
					matrix.set(x, y, (int) RandomUtils.random(10, matricRandomMaxValue));
				else
					matrix.set(x, y, (int) RandomUtils.random(1, 9));
			}
		}
	};
}