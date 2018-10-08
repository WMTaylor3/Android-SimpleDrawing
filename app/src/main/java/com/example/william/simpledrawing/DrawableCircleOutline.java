package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class DrawableCircleOutline extends DrawableShape
{
    private Paint paint = new Paint();
    private RectF coordinates = new RectF(xStart, yStart, xEnd, yEnd);
    int stroke;

    DrawableCircleOutline(int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
        this.stroke = stroke;
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        canvas.drawOval(coordinates, paint);
    }
}