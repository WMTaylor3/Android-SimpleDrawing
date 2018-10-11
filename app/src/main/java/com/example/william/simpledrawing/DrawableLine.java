package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class DrawableLine extends DrawableShape
{
    private Paint paint = new Paint();
    private Path path = new Path();
    private int currentX = 0;
    private int currentY = 0;

    DrawableLine(int color, int stroke)
    {
        super(color, stroke);
    }

    void MoveTo(int x, int y)
    {
        path.moveTo(x, y);
        currentX = x;
        currentY = y;
    }

    void LineTo(int x, int y)
    {
        int xDelta = Math.abs(currentX - x);
        int yDelta = Math.abs(currentY - y);

        if(xDelta >= 5 || yDelta >= 5)
        {
            path.lineTo(x, y);
            currentX = x;
            currentY = y;
        }
    }

    void DeletePath()
    {
        this.path = new Path();
    }

    public void Draw(Canvas canvas)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        canvas.drawPath(path, paint);
    }
}