package com.groovygames.calculbulle.modifier;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.util.modifier.IModifier;

import com.groovygames.groovylib.AbstractActivity;

public class DetachEntityModifier extends FadeOutModifier
{
	final AbstractActivity activity;

	final Entity target;

	public DetachEntityModifier(float pDuration, final Entity target, final AbstractActivity activity)
	{
		super(pDuration);

		this.target = target;
		this.activity = activity;

		this.addModifierListener(new DettachEntityModifierListener());
	}

	class DettachEntityModifierListener implements IEntityModifierListener
	{
		@Override
		public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem)
		{
		}

		@Override
		public void onModifierFinished(IModifier<IEntity> pModifier, final IEntity pItem)
		{
			activity.runOnUiThread(new Runnable()
			{
				public void run()
				{
					pItem.detachSelf();
				}
			});
		}
	}
}
