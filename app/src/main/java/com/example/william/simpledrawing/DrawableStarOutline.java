package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

class DrawableStarOutline extends DrawableShape
{
    private Paint paint = new Paint();
    private Path starPath = new Path();
    private int stroke;

    DrawableStarOutline(int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);
        starPath.moveTo(100.000f, 150.000f);
        starPath.lineTo(158.779f, 180.902f);
        starPath.lineTo(147.553f, 115.451f);
        starPath.lineTo(195.106f, 69.098f);
        starPath.lineTo(129.389f, 59.549f);
        starPath.lineTo(100.000f, 0.000f);
        starPath.lineTo(70.611f, 59.549f);
        starPath.lineTo(4.894f, 69.098f);
        starPath.lineTo(52.447f, 115.451f);
        starPath.lineTo(41.221f, 180.902f);
        starPath.lineTo(100.000f, 150.000f);
        starPath.close();

        this.stroke = stroke;
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(stroke);
        canvas.drawPath(starPath, paint);
    }
}
