package com.groovygames.calculbulle;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import com.groovygames.groovylib.utils.ScreenUtils;

abstract public class AbstractActivity extends BaseGameActivity
{
	static final int CAMERA_WIDTH = ScreenUtils.getWidth();
	static final int CAMERA_HEIGHT = ScreenUtils.getHeight();

	private Camera camera;

	private Scene scene;

	private BitmapTextureAtlas bitmapTextureAtlas;

	private ITextureRegion textureRegion;

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

		//sPlayer.setRotation(45.0f);
		
		sPlayer.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);

		this.scene.attachChild(sPlayer);

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
}
