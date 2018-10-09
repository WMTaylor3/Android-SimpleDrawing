package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

class DrawableStar extends DrawableShape
{
    private Paint paint = new Paint();
    private Path starPath = new Path();

    DrawableStar(int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        super(xStart, yStart, xEnd, yEnd, color);

        float height = xEnd-xStart;
        float width = yEnd-yStart;

        starPath.moveTo(width * 0.500f + xStart, height * 0.000f + yStart); //Point 1
        starPath.lineTo(width * 0.620f + xStart, height * 0.384f + yStart); //Vert 1
        starPath.lineTo(width * 1.000f + xStart, height * 0.384f + yStart); //Point 2
        starPath.lineTo(width * 0.693f + xStart, height * 0.618f + yStart); //Vert 2
        starPath.lineTo(width * 0.809f + xStart, height * 1.000f + yStart); //Point 3
        starPath.lineTo(width * 0.500f + xStart, height * 0.765f + yStart); //Vert 3
        starPath.lineTo(width * 0.196f + xStart, height * 1.000f + yStart); //Point 4
        starPath.lineTo(width * 0.312f + xStart, height * 0.618f + yStart); //Vert 4
        starPath.lineTo(width * 0.000f + xStart, height * 0.384f + yStart); //Point 5
        starPath.lineTo(width * 0.384f + xStart, height * 0.384f + yStart); //Vert 5
        starPath.lineTo(width * 0.500f + xStart, height * 0.000f + yStart); //Point 1
        starPath.close();
    }

    void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(starPath, paint);
    }
}