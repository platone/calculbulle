package com.groovygames.calculbulle;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

import com.groovygames.groovylib.utils.ScreenUtils;

abstract public class AbstractActivity extends BaseGameActivity
{
	static final int CAMERA_WIDTH = ScreenUtils.getWidth();
	static final int CAMERA_HEIGHT = ScreenUtils.getHeight();

	private Camera camera;

	private Scene scene;

	private BitmapTextureAtlas bitmapTextureAtlas;

	private BitmapTextureAtlas bitmapTextureAtlas2;

	private ITextureRegion textureRegion;

	private TiledTextureRegion textureRegionBulles;
	
	private BitmapTextureAtlas mFontTexture;
    private Font mFont;

	@Override
	public EngineOptions onCreateEngineOptions()
	{
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception
	{
		this.loadGfx();

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	protected void loadGfx()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		bitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 2048, 2048);
		textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, this, "background.jpg", 0, 0);
		bitmapTextureAtlas.load();

		bitmapTextureAtlas2 = new BitmapTextureAtlas(getTextureManager(), 1024, 128);

		// textureRegionBulles =
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas2,
		// this, "bulles.jpg", 0, 0);

		textureRegionBulles = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bitmapTextureAtlas2, this, "bulles.png", 0, 0, 10, 1);
		bitmapTextureAtlas2.load();
		
		this.mFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256);
		
        this.mFont = new Font(this.getFontManager(), this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.BLACK);
        
        this.mEngine.getTextureManager().loadTexture(this.mFontTexture);

        this.getFontManager().loadFont(this.mFont);
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception
	{
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.scene = new Scene();
		this.scene.setBackground(new Background(0, 0, 0));

		pOnCreateSceneCallback.onCreateSceneFinished(this.scene);
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception
	{
		Sprite sPlayer = new Sprite(0, 0, textureRegion, this.mEngine.getVertexBufferObjectManager());

		TiledSprite button1 = new TiledSprite(150, 150, textureRegionBulles, this.getVertexBufferObjectManager());
		TiledSprite button2 = new TiledSprite(200, 150, textureRegionBulles, this.getVertexBufferObjectManager());
		TiledSprite button3 = new TiledSprite(250, 150, textureRegionBulles, this.getVertexBufferObjectManager());
		TiledSprite button4 = new TiledSprite(300, 150, textureRegionBulles, this.getVertexBufferObjectManager());
		TiledSprite button5 = new TiledSprite(350, 150, textureRegionBulles, this.getVertexBufferObjectManager());

		// sPlayer.setRotation(45.0f);

		sPlayer.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
		
		button1.setSize(50, 50);
		button1.setCurrentTileIndex(2);
		
		button2.setSize(50, 50);
		button2.setCurrentTileIndex(3);
		
		button3.setSize(50, 50);
		button3.setCurrentTileIndex(4);
		
		button4.setSize(50, 50);
		button4.setCurrentTileIndex(5);
		
		button5.setSize(50, 50);
		button5.setCurrentTileIndex(6);

		final Text textCenter = new Text(100, 60, this.mFont, "Calcul Bulle !!!", this.getVertexBufferObjectManager());
		
		this.scene.attachChild(sPlayer);
		
		this.scene.attachChild(button1);
		this.scene.attachChild(textCenter);
		
		this.scene.attachChild(button2);
		this.scene.attachChild(button3);
		this.scene.attachChild(button4);
		this.scene.attachChild(button5);
		
		 final AnimatedSprite helicopter = new AnimatedSprite(320, 50, this.textureRegionBulles, this.getVertexBufferObjectManager());
         helicopter.animate(new long[] { 100, 100 }, 1, 2, true);
         
         this.scene.attachChild(helicopter);

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
}
