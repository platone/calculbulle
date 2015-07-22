package com.groovygames.calculbulle.andengine;

import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

import com.groovygames.groovylib.ContextManager;

public class BackgroundFactory
{
	public static IBackground createImageBackground(final TextureManager textureManager, final int textureWidth, final int textureHeight,
			final String fileName, final VertexBufferObjectManager vertexBufferObjectManager)
	{
		final Context context = ContextManager.getInstance().getContext();

		final BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(textureManager, textureWidth, textureHeight, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		final TextureRegion textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, fileName, 0, 0);

		bitmapTextureAtlas.load();

		final Sprite sprite = new Sprite(0, 0, textureRegion, vertexBufferObjectManager);

		return new SpriteBackground(sprite);
	}
}
