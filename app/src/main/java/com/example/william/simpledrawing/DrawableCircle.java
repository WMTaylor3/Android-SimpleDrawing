package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class DrawableCircle extends DrawableShape
{
    private Paint paint = new Paint();
    private RectF coordinates = new RectF(xStart, yStart, xEnd, yEnd);

    DrawableCircle(int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(coordinates, paint);
    }
}