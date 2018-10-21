package com.example.william.simpledrawing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;

public class DrawableImage extends DrawableShape
{
    private Bitmap bitmap;

    DrawableImage(int xStartScreen, int yStartScreen, int xEndScreen, int yEndScreen, Uri bitmapURI, int orientation)
    {
        super(0, 0, 1, 1);
        Bitmap rawBitmap = BitmapFactory.decodeFile(bitmapURI.toString());

        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);
        Bitmap rotatedBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.getWidth(), rawBitmap.getHeight(), matrix, true);

        int imageWidth = rotatedBitmap.getWidth();
        int imageHeight = rotatedBitmap.getHeight();
        int screenWidth = xEndScreen - xStartScreen;
        int screenHeight = yEndScreen - yStartScreen;

        Log.e("=====Screen Width", Integer.toString(screenWidth));
        Log.e("=====Screen Height", Integer.toString(screenHeight));
        Log.e("=====Image Width", Integer.toString(imageWidth));
        Log.e("=====Image Height", Integer.toString(imageHeight));

        boolean fitToWidth = (((float) imageWidth / (float) screenWidth) > ((float) imageHeight / (float) screenHeight));

        Log.e("=====", Boolean.toString(fitToWidth));

        if(fitToWidth)
        {
            int newHeight = (int)(imageHeight * ((float)screenWidth/(float)imageWidth));
            bitmap = Bitmap.createScaledBitmap(rotatedBitmap, screenWidth, newHeight, false);
            yStart = (screenHeight/2)-(newHeight/2);
        }
        else
        {
            int newWidth = (int)(imageWidth * ((float)screenHeight/(float)imageHeight));
            bitmap = Bitmap.createScaledBitmap(rotatedBitmap, newWidth, screenHeight, false);
            xStart = (screenWidth/2)-(newWidth/2);
        }
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, xStart, yStart, null);
    }
}