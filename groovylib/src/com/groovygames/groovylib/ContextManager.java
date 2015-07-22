package com.groovygames.groovylib;

import android.content.Context;

/**
 * <pre>
 * This class manages the application context to be well linked with application resources.
 * This manager has to be called first and once when the application starts.
 * 
 * The context is always valid and never modified by the library.
 * 
 * A nice way to initialise this manager is to extend the android application like this :
 * 
 * public class MyApp extends Application
 * {
 *     {@literal @Override}
 *     public void onCreate()
 *     {
 *         super.onCreate();
 *      
 *         ContextManager.getInstance().setContext(getApplicationContext());
 *     }
 * }
 * </pre>
 * 
 */
public final class ContextManager
{
    static private ContextManager instance;

    private Context applicationContext;

    private ContextManager()
    {}

    public static final ContextManager getInstance()
    {
        if (ContextManager.instance == null)
        {
            synchronized (ContextManager.class)
            {
                if (ContextManager.instance == null)
                {
                    ContextManager.instance = new ContextManager();
                }
            }
        }

        return ContextManager.instance;
    }

    /**
     * Initialise the application context for the library
     * 
     * @param applicationContext
     *            The context of your application.
     */

    public void setContext(final Context applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    /**
     * Get the application context set by the application.
     * 
     * @return The corresponding application context.
     */

    public Context getContext()
    {
        if (applicationContext == null)
        {
            throw new RuntimeException("GroovyLib context hasn't been initialised !");
        }

        return applicationContext;
    }
}
