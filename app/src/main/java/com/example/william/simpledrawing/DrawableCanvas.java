package com.example.william.simpledrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

    public void setBackgroundImage(Uri imageURI, Integer imageRotation, float imageScale)
    {

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

    public void saveBitmap()
    {
        //Define a bitmap with the same size as the view
        Bitmap imageAsBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(imageAsBitmap);
        //Get the view's background
        Drawable bgDrawable =this.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        this.draw(canvas);
        //return the bitmap

       try
       {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
           String dateTime = sdf.format(new Date());

           File file = new File(Environment.getExternalStorageDirectory().toString(), "SimpleDraw"+dateTime+".png");
           OutputStream fileOut = new FileOutputStream(file);

           imageAsBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOut);
           fileOut.close();

           MediaStore.Images.Media.insertImage(getContext().getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }
}