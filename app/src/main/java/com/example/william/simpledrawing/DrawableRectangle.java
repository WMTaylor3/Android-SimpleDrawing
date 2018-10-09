package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class DrawableRectangle extends DrawableShape
{
    private Paint paint = new Paint();
    private Rect rectangle = new Rect(xStart, yStart, xEnd, yEnd);

    DrawableRectangle(int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectangle, paint);
    }
}