package com.groovygames.calculbulle;

import android.app.Application;

import com.groovygames.groovylib.ContextManager;

public class MainApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();

		ContextManager.getInstance().setContext(getApplicationContext());
	}
}
