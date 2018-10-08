package com.example.william.simpledrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

public class DrawableCanvas extends View
{
    // -- Variables -- //
    ArrayList<DrawableShape> listOfShapes = new ArrayList<>();

    // -- Override Methods -- //
    @Override
    protected void onDraw(Canvas canvas)
    {
        for(DrawableShape s : listOfShapes)
        {
            s.Draw(canvas);
        }
    }

    // -- User Methods -- //
    public DrawableCanvas(Context context)
    {
        super(context);
    }

    public DrawableCanvas(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public DrawableCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void drawRectangle(boolean filled, int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        DrawableShape newShape;
        if(filled)
        {
            newShape = new DrawableRectangle(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableRectangleOutline(stroke, xStart, yStart, xEnd, yEnd, color);
        }
        listOfShapes.add(newShape);
    }

    public void drawCircle(boolean filled, int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        DrawableShape newShape;
        if(filled)
        {
            newShape = new DrawableCircle(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableCircleOutline(stroke, xStart, yStart, xEnd, yEnd, color);
        }
        listOfShapes.add(newShape);
    }

    public void drawTriangle(boolean filled, int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        DrawableShape newShape;
        if(filled)
        {
            newShape = new DrawableTriangle(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableTriangleOutline(stroke, xStart, yStart, xEnd, yEnd, color);
        }
        listOfShapes.add(newShape);
    }

    public void drawStar(boolean filled, int stroke, int xStart, int yStart, int xEnd, int yEnd, int color)
    {
        DrawableShape newShape;
        if(filled)
        {
            newShape = new DrawableStar(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableStarOutline(stroke, xStart, yStart, xEnd, yEnd, color);
        }
        listOfShapes.add(newShape);
    }
}