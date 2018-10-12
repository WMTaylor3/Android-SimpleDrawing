package com.example.william.simpledrawing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;

public class DrawableImage extends DrawableShape
{
    private Bitmap bitmap;

    DrawableImage(int xStart, int yStart, int xEnd, int yEnd, Uri bitmapURI)
    {
        super(xStart, yStart, xEnd, yEnd);
        bitmap = BitmapFactory.decodeFile(bitmapURI.toString());
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, xStart, yStart, null);
    }
}