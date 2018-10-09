package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

class DrawableTriangle extends DrawableShape
{
    private Paint paint = new Paint();
    private Path trianglePath = new Path();

    DrawableTriangle(int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
        trianglePath.moveTo(xStart+((xEnd-xStart)/2), yStart);
        trianglePath.lineTo(xEnd, yEnd);
        trianglePath.lineTo(xStart, yEnd);
        trianglePath.lineTo(xStart+((xEnd-xStart)/2),yStart);
        trianglePath.close();
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(trianglePath, paint);
    }
}
