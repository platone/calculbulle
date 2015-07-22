package com.groovygames.groovylib.resources;

import java.util.HashMap;
import java.util.Locale;

/**
 * This class manages resources using custom adapters. AndroidResourceAdapter is set by default.
 * 
 */
public class ResourceManager
{
    /**
     * The resource adapter used to add or retrieve the available resources.
     */
    private IResourceAdapter resourceAdapter;

    /**
     * The unique instance of this manager.
     */
    private static ResourceManager instance;
    /**
     * Cache of base preferences
     */
    private HashMap<String, PreferenceBase> preferences;

    private ResourceManager()
    {
        resourceAdapter = new AndroidResourceAdapter();
    }

    /**
     * Retrieves the single instance of this manager.
     * 
     * @return a ResourceManager instance.
     */
    public static final ResourceManager getInstance()
    {
        if (ResourceManager.instance == null)
        {
            synchronized (ResourceManager.class)
            {
                if (ResourceManager.instance == null)
                {
                    ResourceManager.instance = new ResourceManager();
                }
            }
        }
        return ResourceManager.instance;
    }

    /**
     * Sets the adapter used to retrieve or add resources. The default is Android's system.
     * 
     * @param resourceAdapter
     *            an object that implements the {@link IResourceAdapter} interface.
     */
    public void setResourceAdapter(final IResourceAdapter resourceAdapter)
    {
        this.resourceAdapter = resourceAdapter;
    }

    /**
     * Retrieve the application shared preferences.
     * 
     * @param preferenceName
     *            the name of the created SharedPreference. If null it returns default preferences.
     * @return Create a new preference based on the custom name provided with preferenceName if any.
     *         Else, it returns the default preferences.
     */
    public PreferenceBase getPreferenceBase(final String preferenceName)
    {
        PreferenceBase prefBase = null;

        if (null == preferences)
        {
            preferences = new HashMap<String, PreferenceBase>();
        }
        else
        {
            prefBase = preferences.get(preferenceName);
        }

        if (null == prefBase)
        {
            prefBase = new PreferenceBase(preferenceName);
            preferences.put(preferenceName, prefBase);
        }

        return prefBase;
    }

    /**
     * Retrieves the currently used resources adapter.
     * 
     * @return the adapter.
     */
    public IResourceAdapter getResourceAdapter()
    {
        return resourceAdapter;
    }

    /**
     * Alias to the resource adapter. Check it the specified string exist in resource.
     * 
     * @param resId
     *            the id of the string to check.
     * @return true if string exists, false otherwise.
     */
    public boolean exists(final int resId)
    {
        return resourceAdapter.exists(resId);
    }

    /**
     * Alias to the resource adapter. Check it the specified string exist in resource.
     * 
     * @param resId
     *            the id of the string to check.
     * @param locale
     *            the specified locale to check in.
     * @return true if string exists, false otherwise.
     */
    public boolean exists(final int resId, final Locale locale)
    {
        return resourceAdapter.exists(resId, locale);
    }

    /**
     * Adds the specified string to the resource package.
     * 
     * @param resId
     *            a unique ID.
     * @param value
     *            the value to store at resId ID.
     */
    public void add(final int resId, final String value)
    {
        resourceAdapter.add(resId, value);
    }

    /**
     * Adds the specified string to the resource package.
     * 
     * @param resId
     *            a unique ID.
     * @param value
     *            the value to store at resId ID.
     * @param locale
     *            the specified locale to add the resource in.
     */
    public void add(final int resId, final String value, final Locale locale)
    {
        resourceAdapter.add(resId, value, locale);
    }

    /**
     * Gets the String stored at the specified resId ID.
     * 
     * @param resId
     *            the unique ID of the string.
     * @return the string stored at resId ID or null if not found.
     */
    public String get(final int resId)
    {
        return resourceAdapter.get(resId);
    }

    /**
     * Gets the String stored at the specified resId ID.
     * 
     * @param resId
     *            the unique ID of the string.
     * @param defaultValue
     *            the value returned if no string was found.
     * @return the string stored at resId ID or defaultValue if not found.
     */
    public String get(final int resId, final String defaultValue)
    {
        return resourceAdapter.get(resId, defaultValue);
    }
}
