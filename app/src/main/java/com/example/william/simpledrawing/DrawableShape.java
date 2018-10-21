//Abstract class to provide common fields and "Draw" method to all the shapes which inherit from it.
//Used for purposes of polymorphism so that each shape the user draws can be stored in a lost of shapes and run through when being drawn to screen.

package com.example.william.simpledrawing;

import android.graphics.Canvas;
import android.graphics.Color;

abstract class DrawableShape
{
    // -- Variables -- //
    protected int xStart;
    protected int yStart;
    protected int xEnd;
    protected int yEnd;
    protected int color;
    protected int stroke;

    DrawableShape(int xStart, int yStart, int xEnd, int yEnd)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = Color.BLACK;
        this.stroke = 1;
    }

    DrawableShape(int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
        this.stroke = 1;
    }

    DrawableShape(int xStart, int yStart, int xEnd, int yEnd, int color, int stroke)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
        this.stroke = stroke;
    }

    DrawableShape(int color, int stroke)
    {
        this.xStart = 1;
        this.yStart = 1;
        this.xEnd = 1;
        this.yEnd = 1;
        this.color = color;
        this.stroke = stroke;
    }

    abstract void Draw(Canvas canvas);
}
