package com.example.william.simpledrawing;

import android.app.Application;
import android.graphics.Bitmap;

public class PersistentBitmap extends Application
{
    // -- Variables -- //
    private static Bitmap storedBitmap;
    private static int storedRotation;

    // -- Methods -- //
    public static Bitmap getBitmap()
    {
        return storedBitmap;
    }

    public static void setBitmap(Bitmap bitmap)
    {
        storedBitmap = bitmap;
    }

    public static int getRotation()
    {
        return storedRotation;
    }

    public static void setRotation(int rotation)
    {
        storedRotation = rotation;
    }
}