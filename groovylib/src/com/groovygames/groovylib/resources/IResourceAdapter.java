package com.groovygames.groovylib.resources;

import java.util.Locale;

/**
 * Provides method to implement in order to create a resource adapter.
 * 
 */
public interface IResourceAdapter
{
    /**
     * Check whether string exists in resources or not.
     * 
     * @param resId
     *            the unique ID of the string resource.
     * @return true if it exists, false otherwise.
     */
    public boolean exists(int resId);

    /**
     * Check whether string exists in resources or not.
     * 
     * @param resId
     *            the unique ID of the string resource.
     * @param locale
     *            the locale in which you want to check.
     * @return true if it exists, false otherwise.
     * @throws UnsupportedOperationException
     *             if not supported by adapter.
     */
    public boolean exists(int resId, Locale locale) throws UnsupportedOperationException;

    /**
     * Add a string with the specified ID to the resources.
     * 
     * @param resId
     *            the unique ID of the string.
     * @param value
     *            the value of the string.
     * @throws UnsupportedOperationException
     *             if not supported by adapter.
     */
    public void add(int resId, String value) throws UnsupportedOperationException;

    /**
     * Add a string with the specified ID to the resources.
     * 
     * @param resId
     *            the unique ID of the string.
     * @param locale
     *            the locale you want to add the value in.
     * @param value
     *            the value of the string.
     * @throws UnsupportedOperationException
     *             if not supported by adapter.
     */
    public void add(int resId, String value, Locale locale) throws UnsupportedOperationException;

    /**
     * Retrieve a string with the specified ID from the resources with locale fallback.
     * 
     * @param resId
     *            the unique ID of the string.
     * @return the string if found in current locale, else in default locale or if it does not
     *         exists anywhere returns null.
     */
    public String get(int resId);

    /**
     * Retrieve a string with the specified ID from the resources with locale fallback.
     * 
     * @param resId
     *            the unique ID of the string.
     * @param defaultValue
     *            the default value if nothing was found.
     * @return the string if found, else its default value.
     */
    public String get(int resId, String defaultValue);

    /**
     * Retrieve a string with the specified ID from the resources only for the specific locale.
     * 
     * @param resId
     *            the unique ID of the string.
     * @param defaultValue
     *            the default value if nothing was found.
     * @return the string if found, else its default value.
     */
    public String get(int resId, String defaultValue, Locale locale);
}
