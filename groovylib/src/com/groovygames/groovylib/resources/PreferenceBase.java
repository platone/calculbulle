package com.groovygames.groovylib.resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.groovygames.groovylib.ContextManager;

/**
 * Provide a wrapper for SharedPreferences.
 * 
 */
public class PreferenceBase
{

    /**
     * The sharedpreference editor.
     */
    private final Editor editor;

    /**
     * The preference itself.
     */
    private SharedPreferences preferences;

    /**
     * The preference name.
     */
    private String preferenceName;

    /**
     * Creates a new SharedPreference.
     * 
     * @param preferenceName
     *            if null creates a defaultPreference.
     */
    public PreferenceBase(final String preferenceName)
    {
        if (preferenceName == null || preferenceName.isEmpty())
        {
            preferences = PreferenceManager.getDefaultSharedPreferences(ContextManager.getInstance().getContext());
        }
        else
        {
            this.preferenceName = preferenceName;
            preferences = ContextManager.getInstance().getContext().getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        }
        editor = preferences.edit();
    }

    /**
     * Returns the prefenceName.
     * 
     * @return the name or an empty string if defaultPreference was created.
     */
    public String getPreferenceBaseName()
    {
        return preferenceName;
    }

    /**
     * Gets the string at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @return the string or <code>null</code> if nothing was found.
     */
    public String getString(final String key)
    {
        return preferences.getString(key, null);
    }

    /**
     * Gets the string at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @param def
     *            the default value to return if no string was found.
     * @return the string or def if nothing was found.
     */
    public String getString(final String key, final String def)
    {
        return preferences.getString(key, def);
    }

    /**
     * Gets the boolean at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @return the boolean or <code>false</code> if nothing was found.
     */
    public boolean getBoolean(final String key)
    {
        return preferences.getBoolean(key, false);
    }

    /**
     * Gets the boolean at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @param def
     *            the default value to return if no boolean was found.
     * @return the boolean or def if nothing was found.
     */
    public boolean getBoolean(final String key, final boolean def)
    {
        return preferences.getBoolean(key, def);
    }

    /**
     * Gets the int at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @return the int or <code>0</code> if nothing was found.
     */
    public int getInt(final String key)
    {
        return preferences.getInt(key, 0);
    }

    /**
     * Gets the int at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @param def
     *            the default value to return if no int was found.
     * @return the int or def if nothing was found.
     */
    public int getInt(final String key, final int def)
    {
        return preferences.getInt(key, def);
    }

    /**
     * Gets the float at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @param def
     *            the default value to return if no float was found.
     * @return the float or def if nothing was found.
     */
    public float getFloat(final String key, final float def)
    {
        return preferences.getFloat(key, def);
    }

    /**
     * Gets the long at the specified key.
     * 
     * @param key
     *            the key to the preference itself.
     * @param def
     *            the default value to return if no long was found.
     * @return the long or def if nothing was found.
     */
    public long getLong(final String key, final long def)
    {
        return preferences.getLong(key, def);
    }

    /**
     * Sets the value at the specified key.
     * 
     * @param key
     *            the unique key of the string to set.
     * @param value
     *            the value of the string to set.
     */
    public void setString(final String key, final String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Sets the value at the specified key.
     * 
     * @param key
     *            the unique key of the int to set.
     * @param value
     *            the value of the int to set.
     */
    public void setInt(final String key, final int value)
    {
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Sets the value at the specified key.
     * 
     * @param key
     *            the unique key of the boolean to set.
     * @param value
     *            the value of the boolean to set.
     */
    public void setBoolean(final String key, final boolean value)
    {
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Sets the value at the specified key.
     * 
     * @param key
     *            the unique key of the float to set.
     * @param value
     *            the value of the float to set.
     */
    public void setFloat(final String key, final float value)
    {
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * Sets the value at the specified key.
     * 
     * @param key
     *            the unique key of the long to set.
     * @param value
     *            the value of the long to set.
     */
    public void setLong(final String key, final long value)
    {
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Removes the value at the specified key.
     * 
     * @param key
     *            the unique key to remove.
     */
    public void remove(final String key)
    {
        editor.remove(key);
        editor.commit();
    }

    /**
     * Check whether the preferences contains the specified key.
     * 
     * @param key
     *            the unique ID to check.
     * @return true if the preference contains the key, false otherwise.
     */
    public boolean contains(final String key)
    {
        return preferences.contains(key);
    }

    /**
     * Debug
     */
    public void clear()
    {
        editor.clear();
        editor.commit();
    }

}
