package com.groovygames.groovylib.resources;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;

import com.groovygames.groovylib.ContextManager;

public class ResourceHandler
{
    public static final String ID = "id";
    public static final String STRING = "string";
    public static final String STRING_ARRAY = "array";
    public static final String DRAWABLE = "drawable";
    public static final String BOOLEAN = "boolean";
    public static final String COLOR = "color";
    public static final String STYLE = "style";
    public static final String LAYOUT = "layout";

    public static int getResourceIdByName(final String resource, final String type, final String packageName) throws NotFoundException,
            IllegalArgumentException
    {
        if (resource == null || resource.isEmpty())
        {
            throw new IllegalArgumentException("resource cannot be null or empty.");
        }

        if (type == null)
        {
            throw new IllegalArgumentException("type cannot be null.");
        }

        return ContextManager.getInstance().getContext().getResources().getIdentifier(resource, type, packageName);
    }

    public static int getId(final String resource) throws NotFoundException, IllegalArgumentException
    {
        final Context context = ContextManager.getInstance().getContext();

        return ResourceHandler.getResourceIdByName(resource, ResourceHandler.ID, context.getPackageName());
    }

    public static String getString(final String resource) throws NotFoundException, IllegalArgumentException
    {
        final Context context = ContextManager.getInstance().getContext();

        final int resourceId = ResourceHandler.getResourceIdByName(resource, ResourceHandler.STRING, context.getPackageName());

        return context.getResources().getString(resourceId);
    }

    public static int getInteger(final String resource) throws NotFoundException, IllegalArgumentException
    {
        return Integer.valueOf(ResourceHandler.getString(resource));
    }

    public static boolean getBoolean(final String resource) throws NotFoundException, IllegalArgumentException
    {
        return Boolean.valueOf(ResourceHandler.getString(resource));
    }

    public static float getFloat(final String resource) throws NotFoundException, IllegalArgumentException
    {
        return Float.valueOf(ResourceHandler.getString(resource));
    }

    public static double getDouble(final String resource) throws NotFoundException, IllegalArgumentException
    {
        return Double.valueOf(ResourceHandler.getString(resource));
    }

    public static String[] getStringArray(final String resource) throws NotFoundException, IllegalArgumentException
    {
        final Context context = ContextManager.getInstance().getContext();

        final int resourceId = ResourceHandler.getResourceIdByName(resource, ResourceHandler.STRING_ARRAY, context.getPackageName());

        return context.getResources().getStringArray(resourceId);
    }

    public static Drawable getDrawable(final String resource) throws NotFoundException, IllegalArgumentException
    {
        final Context context = ContextManager.getInstance().getContext();

        final int resourceId = ResourceHandler.getResourceIdByName(resource, ResourceHandler.DRAWABLE, context.getPackageName());

        return context.getResources().getDrawable(resourceId);
    }

    public static Drawable getLayout(final String resource) throws NotFoundException, IllegalArgumentException
    {
        final Context context = ContextManager.getInstance().getContext();

        final int resourceId = ResourceHandler.getResourceIdByName(resource, ResourceHandler.LAYOUT, context.getPackageName());

        return context.getResources().getDrawable(resourceId);
    }

    public static Drawable getColor(final String resource) throws NotFoundException, IllegalArgumentException
    {
        final Context context = ContextManager.getInstance().getContext();

        final int resourceId = ResourceHandler.getResourceIdByName(resource, ResourceHandler.COLOR, context.getPackageName());

        return context.getResources().getDrawable(resourceId);
    }
}
