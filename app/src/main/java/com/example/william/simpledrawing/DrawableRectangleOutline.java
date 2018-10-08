package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class DrawableRectangleOutline extends DrawableShape
{
    private Paint paint = new Paint();
    private Rect rectangle = new Rect(xStart, yStart, xEnd, yEnd);
    private int stroke;

    DrawableRectangleOutline(int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
        this.stroke = stroke;
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        canvas.drawRect(rectangle, paint);
    }
}
