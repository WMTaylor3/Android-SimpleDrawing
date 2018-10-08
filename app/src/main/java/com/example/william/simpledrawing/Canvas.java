package com.example.william.simpledrawing;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

public class Canvas extends View
{
    Paint paint;
    public Canvas(Context context)
    {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor();
    }

}
