package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

class DrawableTriangleOutline extends DrawableShape
{
    private Paint paint = new Paint();
    private Path trianglePath = new Path();

    DrawableTriangleOutline(int xStart, int yStart, int xEnd, int yEnd, int color, int stroke)
    {
        super(xStart, yStart, xEnd, yEnd, color, stroke);
        trianglePath.moveTo(xStart+((xEnd-xStart)/2), yStart);
        trianglePath.lineTo(xEnd, yEnd);
        trianglePath.lineTo(xStart, yEnd);
        trianglePath.lineTo(xStart+((xEnd-xStart)/2),yStart);
        trianglePath.close();
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        canvas.drawPath(trianglePath, paint);
    }
}