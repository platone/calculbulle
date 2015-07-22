package com.groovygames.groovylib.resources;

import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

/**
 * Provides an adapter to store localised string resources into shared preferences.
 */
public class PreferenceResourceAdapter implements IResourceAdapter
{
    /**
     * Separator between basename and locale.
     */
    private static final String PREF_NAME_SEPARATOR = "_";
    /**
     * The base name of the preference.
     */
    private final String baseName;
    /**
     * A map that holds every preference in memory.
     */
    private final Hashtable<Locale, PreferenceBase> preferencesMap;

    /**
     * Create a new adapter using the default locale.
     * 
     * @param baseName
     *            The base name of the preferences.
     * @param supportedLocales
     *            A list of supported locales.
     * 
     */
    public PreferenceResourceAdapter(final String baseName, final List<Locale> supportedLocales)
    {
        if (baseName == null || baseName.isEmpty())
        {
            throw new IllegalArgumentException("Base name cannot be empty or null");
        }

        if (supportedLocales == null || supportedLocales.size() == 0)
        {
            throw new IllegalArgumentException("Supported locales cannot be empty or null");
        }

        this.baseName = baseName;

        preferencesMap = new Hashtable<Locale, PreferenceBase>();
        for (final Locale locale : supportedLocales)
        {
            final StringBuilder strBuilder = new StringBuilder(baseName);
            strBuilder.append(PreferenceResourceAdapter.PREF_NAME_SEPARATOR);
            strBuilder.append(locale.toString());

            preferencesMap.put(locale, new PreferenceBase(strBuilder.toString()));
        }
    }

    /**
     * Used to retrieve the base name of this preference.
     * 
     * @return baseName.
     */
    public String getPreferenceBaseName()
    {
        return baseName;
    }

    @Override
    public boolean exists(final int resId)
    {
        return exists(resId, Locale.getDefault());
    }

    @Override
    public void add(final int resId, final String value)
    {
        add(resId, value, Locale.getDefault());
    }

    @Override
    public String get(final int resId, final String defaultValue)
    {
        String result;
        final Locale defaultLocale = Locale.getDefault();

        result = get(resId, null, defaultLocale);

        if (result != null)
        {
            return result;
        }

        // Language fallback with variant and country
        if (!defaultLocale.getCountry().isEmpty())
        {
            final Locale locale = new Locale(defaultLocale.getLanguage(), defaultLocale.getCountry());

            result = get(resId, null, locale);

            if (result != null)
            {
                return result;
            }
        }

        final Locale locale = new Locale(defaultLocale.getLanguage());

        result = get(resId, null, locale);

        return (result != null) ? result : defaultValue;
    }

    @Override
    public String get(final int resId, final String defaultValue, final Locale locale)
    {
        final PreferenceBase preferenceBase = preferencesMap.get(locale);

        if (preferenceBase == null)
        {
            return defaultValue;
        }

        return preferenceBase.getString(String.valueOf(resId), defaultValue);
    }

    @Override
    public boolean exists(final int resId, final Locale locale)
    {
        return (get(resId, null, locale) != null);
    }

    @Override
    public void add(final int resId, final String value, final Locale locale)
    {
        final PreferenceBase preferenceBase = preferencesMap.get(locale);

        if (preferenceBase == null)
        {
            throw new IllegalArgumentException(locale + " does not appear in supportedLocales list.");
        }

        preferenceBase.setString(String.valueOf(resId), value);
    }

    @Override
    public String get(final int resId)
    {
        return get(resId, null);
    }
}
