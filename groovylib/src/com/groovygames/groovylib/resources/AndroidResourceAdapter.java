package com.groovygames.groovylib.resources;

import java.util.Locale;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;

import com.groovygames.groovylib.ContextManager;

/**
 * Provide a basic implementation in order to retrieve String in /res/values.
 * 
 */
public class AndroidResourceAdapter implements IResourceAdapter
{

    /**
     * Keeping instance of the resource package.
     */
    private final Resources resources;

    /**
     * Creates a new instance of this adapter.
     */
    public AndroidResourceAdapter()
    {
        resources = ContextManager.getInstance().getContext().getResources();
    }

    /**
     * Will only throw UnsupportedOperationException
     */
    @Override
    public void add(final int resId, final String value)
    {
        throw new UnsupportedOperationException("Operation not supported with Android system base adapter.");
    }

    /**
     * Will only throw UnsupportedOperationException
     */
    @Override
    public void add(final int resId, final String value, final Locale locale)
    {
        throw new UnsupportedOperationException("Operation not supported with Android system base adapter.");
    }

    @Override
    public String get(final int resId)
    {
        return get(resId, null);
    }

    @Override
    public String get(final int resId, final String defaultValue)
    {
        try
        {
            return resources.getString(resId);
        }
        catch (final NotFoundException nfe)
        {
            return defaultValue;
        }
    }

    @Override
    public boolean exists(final int resId)
    {
        return (null != get(resId, null));
    }

    @Override
    public String get(final int resId, final String defaultValue, final Locale locale)
    {
        final Configuration config = new Configuration(resources.getConfiguration());
        config.locale = locale;

        final Resources defaultResources = new Resources(resources.getAssets(), resources.getDisplayMetrics(), config);

        try
        {
            return defaultResources.getString(resId);
        }
        catch (final NotFoundException nfe)
        {
            return defaultValue;
        }
    }

    @Override
    public boolean exists(final int resId, final Locale locale)
    {
        return (null != get(resId, null, locale));
    }
}
