package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

class DrawableTriangleOutline extends DrawableShape
{
    private Paint paint = new Paint();
    private Path trianglePath = new Path();
    private int stroke;

    DrawableTriangleOutline(int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
        trianglePath.moveTo(50,0);
        trianglePath.lineTo(100, 100);
        trianglePath.lineTo(0,100);
        trianglePath.lineTo(50,0);
        trianglePath.close();

        this.stroke = stroke;
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        canvas.drawPath(trianglePath, paint);
    }
}