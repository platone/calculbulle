package com.groovygames.calculbulle;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.groovygames.groovylib.AbstractActivity;
import com.groovygames.groovylib.component.TextButtonSprite;
import com.groovygames.groovylib.resources.ResourceManager;
import com.groovygames.groovylib.utils.ScreenUtils;

public class HomeActivity extends AbstractActivity
{
	private TextureRegion textureRegion;

	private ButtonSprite newGameButton;

	private ButtonSprite newGameButtonWithOutAnimation;

	private Font font;

	@Override
	protected void loadResources()
	{
		super.loadResources();

		this.loadFont();

		BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 1024, 256);

		textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this, "background_brown.png", 0, 0);

		bitmapTextureAtlas.load();
	}

	private void loadFont()
	{
		// determine the density
		WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		Display display = windowManager.getDefaultDisplay();

		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);

		int density = (int) (displayMetrics.density);

		int fontSize = (int) (25 * density);
		font = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 1024, 1024, this.getAssets(), "CarterOne.ttf", fontSize, true,
				android.graphics.Color.WHITE);
		font.load();
	}

	@Override
	public void onPopulateScene(final Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception
	{
		final int buttonWidth = ScreenUtils.getWidth() / 2;
		final int buttonHeight = ScreenUtils.getHeight() / 12;

		final int buttonX = ScreenUtils.getWidth() / 2 - buttonWidth / 2;
		final int buttonY = ScreenUtils.getHeight() / 2 - buttonHeight / 2;

		final String newGameText = ResourceManager.getInstance().get(R.string.new_game);

		final String newGameTextWithOutAnimation = ResourceManager.getInstance().get(R.string.new_game_withoutanimation);

		newGameButton = new TextButtonSprite(buttonX, buttonY, textureRegion, this.getVertexBufferObjectManager(), newGameText, font)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if (pSceneTouchEvent.isActionDown())
				{
					Intent intent = new Intent(HomeActivity.this, GameActivity.class);

					intent.putExtra("ANIMATED", true);

					startActivity(intent);

					return true;
				}

				return false;
			}
		};

		newGameButton.setSize(buttonWidth, buttonHeight);

		pScene.attachChild(newGameButton);
		pScene.registerTouchArea(newGameButton);

		newGameButtonWithOutAnimation = new TextButtonSprite(buttonX, newGameButton.getY() + newGameButton.getHeight() + 30, textureRegion,
				this.getVertexBufferObjectManager(), newGameTextWithOutAnimation, font)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
			{
				if (pSceneTouchEvent.isActionDown())
				{
					Intent intent = new Intent(HomeActivity.this, GameActivity.class);

					intent.putExtra("ANIMATED", false);

					startActivity(intent);

					return true;
				}

				return false;
			}
		};

		newGameButtonWithOutAnimation.setSize(buttonWidth, buttonHeight);

		pScene.attachChild(newGameButtonWithOutAnimation);
		pScene.registerTouchArea(newGameButtonWithOutAnimation);

		pScene.setTouchAreaBindingOnActionDownEnabled(true);

		super.onPopulateScene(pScene, pOnPopulateSceneCallback);
	}
}
