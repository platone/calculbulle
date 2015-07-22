package com.groovygames.groovylib;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;

import com.groovygames.groovylib.resources.ResourceManager;
import com.groovygames.groovylib.utils.ScreenUtils;

abstract public class AbstractActivity extends BaseGameActivity
{
	static final int CAMERA_WIDTH = ScreenUtils.getWidth();
	static final int CAMERA_HEIGHT = ScreenUtils.getHeight();

	private Camera camera;

	private Scene scene;

	@Override
	public EngineOptions onCreateEngineOptions()
	{
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception
	{
		this.loadResources();

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	protected void loadResources()
	{
		final String resourcesDirectory = ResourceManager.getInstance().get(R.string.RESOURCES_DIRECTORY);

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(resourcesDirectory);
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception
	{
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.scene = new Scene();
		this.scene.setBackground(this.createBackground());

		pOnCreateSceneCallback.onCreateSceneFinished(this.scene);
	}

	protected IBackground createBackground()
	{
		return new Background(0, 0, 0);
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception
	{
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
}
