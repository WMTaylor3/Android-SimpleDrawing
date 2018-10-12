package com.example.william.simpledrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
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
    private ArrayList<DrawableShape> listOfShapes = new ArrayList<>();
    private DrawableShape newShape;

    // -- Override Methods -- //
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for(DrawableShape s : listOfShapes)
        {
            s.Draw(canvas);
        }
        if(newShape != null)
        {
            newShape.Draw(canvas);
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

    public void drawImage(int xStart, int yStart, int xEnd, int yEnd, Uri imageURI)
    {
        newShape = new DrawableImage(xStart, yStart, xEnd, yEnd, imageURI);
    }

    public void drawRectangle(boolean filled, int xStart, int yStart, int xEnd, int yEnd, int color, int stroke)
    {
        if(filled)
        {
            newShape = new DrawableRectangle(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableRectangleOutline(xStart, yStart, xEnd, yEnd, color, stroke);
        }
    }

    public void drawCircle(boolean filled, int xStart, int yStart, int xEnd, int yEnd, int color, int stroke)
    {
        if(filled)
        {
            newShape = new DrawableCircle(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableCircleOutline(xStart, yStart, xEnd, yEnd, color, stroke);
        }
    }

    public void drawTriangle(boolean filled, int xStart, int yStart, int xEnd, int yEnd, int color, int stroke)
    {
        if(filled)
        {
            newShape = new DrawableTriangle(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableTriangleOutline(xStart, yStart, xEnd, yEnd, color, stroke);
        }
    }

    public void drawStar(boolean filled, int xStart, int yStart, int xEnd, int yEnd, int color, int stroke)
    {
        if(filled)
        {
            newShape = new DrawableStar(xStart, yStart, xEnd, yEnd, color);
        }
        else
        {
            newShape = new DrawableStarOutline(xStart, yStart, xEnd, yEnd, color, stroke);
        }
    }

    public DrawableLine newLine(int color, int stroke)
    {
        DrawableShape newLine = new DrawableLine(color, stroke);
        listOfShapes.add(newLine);
        return (DrawableLine)newLine;
    }

    public void StoreShape()
    {
        listOfShapes.add(newShape);
        newShape = null;
    }

    public void UndoLast()
    {
        if(listOfShapes.size() > 0)
        {
            listOfShapes.remove(listOfShapes.size()-1);
        }
        invalidate();
    }

    public void UndoAll()
    {
        listOfShapes.clear();
        invalidate();
    }

    public void saveBitmap()
    {
        try
        {
            //Create a bitmap from view.
            Bitmap imageAsBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),Bitmap.Config.ARGB_8888);

            //Bind a canvas to it
            Canvas canvas = new Canvas(imageAsBitmap);

            //Get the view's background
            Drawable bgDrawable =this.getBackground();
            if (bgDrawable!=null)
            {
                bgDrawable.draw(canvas);
            }
            else
            {
                canvas.drawColor(Color.WHITE);
            }
            this.draw(canvas);

            //Create a string of the current date and time for use in the file name.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
            String dateTime = sdf.format(new Date());

            File folder = new File(Environment.getExternalStorageDirectory(), "SimpleDraw");
            if(!folder.exists())
            {
                folder.mkdirs();
            }

            File file = new File(folder, "SimpleDraw"+dateTime+".png");
            OutputStream outStream = new FileOutputStream(file);
            imageAsBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

            Toast.makeText(getContext(),"File saved to external storage", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}