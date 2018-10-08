package com.example.william.simpledrawing;

import android.graphics.Canvas;

abstract class DrawableShape
{
    // -- Variables -- //
    protected int xStart;
    protected int yStart;
    protected int xEnd;
    protected int yEnd;
    protected int color;

    DrawableShape(int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    abstract void Draw(Canvas canvas);
}
