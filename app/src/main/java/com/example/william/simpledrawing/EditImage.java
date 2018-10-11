package com.example.william.simpledrawing;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class EditImage extends AppCompatActivity
{
    // -- Variables and Constants -- //
    private DrawableCanvas canvas;
    private int selectedColor = Color.BLACK;
    private int selectedTool = 4;
    private int toolSize = 10;
    private boolean shapeFilled = false;
    DrawableLine currentLine = null;
    int xStart = 0;
    int yStart = 0;

    // -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_edit);
        canvas = findViewById(R.id.drawableView);

        Intent intent = getIntent();
        boolean photoBackground = intent.getBooleanExtra("photoBackground", false);
        if(photoBackground)
        {
            final Uri imageURI = Uri.parse(intent.getStringExtra("Location"));
            final Integer imageRotation = intent.getIntExtra("Orientation", 0);
            final Float imageScale = intent.getFloatExtra("Scale", 1.0f);
            canvas.setBackgroundImage(imageURI, imageRotation, imageScale);
        }

        FloatingActionButton openColorPicker = findViewById(R.id.openColorPicker);
        openColorPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(EditImage.this, ColorPicker.class);
                intent.putExtra("redValue", Color.red(selectedColor));
                intent.putExtra("greenValue", Color.green(selectedColor));
                intent.putExtra("blueValue", Color.blue(selectedColor));

                startActivityForResult(intent, 1);
            }
        });

        FloatingActionButton saveImage = findViewById(R.id.saveImage);
        saveImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                canvas.saveBitmap();
            }
        });

        FloatingActionButton openToolPicker = findViewById(R.id.openToolPicker);
        openToolPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(EditImage.this, ToolPicker.class);
                intent.putExtra("tool", selectedTool);
                intent.putExtra("size", toolSize);
                intent.putExtra("filled", shapeFilled);

                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
            {
                selectedColor = data.getIntExtra("color", 0);
            }
        }
        else if (requestCode == 2)
        {
            if (resultCode == RESULT_OK) {
                selectedTool = data.getIntExtra("tool", 0);
                toolSize = data.getIntExtra("size", 0);
                shapeFilled = data.getBooleanExtra("filled", false);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
                touchBegin(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP :
                touchEnd();
                break;
        }

        return true;
    }

    // -- User Defined Methods -- //
    private void touchBegin(int x, int y)
    {
        xStart = x;
        yStart = y;

        if((selectedTool >= 4) && (selectedTool <= 5))
        {
            currentLine = canvas.newLine(selectedColor, toolSize);
            currentLine.MoveTo(x, y);
        }

        canvas.invalidate();
    }

    private void touchMove(int x, int y)
    {
        switch(selectedTool)
        {
            case 0: //Square
                canvas.drawRectangle(shapeFilled, xStart, yStart, x, y, selectedColor, toolSize);
                break;
            case 1: //
                canvas.drawCircle(shapeFilled, xStart, yStart, x, y, selectedColor, toolSize);
                break;
            case 2: //Triangle
                canvas.drawTriangle(shapeFilled, xStart, yStart, x, y, selectedColor, toolSize);
                break;
            case 3: //Star
                canvas.drawStar(shapeFilled, xStart, yStart, x, y, selectedColor, toolSize);
                break;
            case 4: //Brush
                currentLine.LineTo(x, y);
                break;
            case 5: //Straight Line
                currentLine.DeletePath();
                currentLine.MoveTo(xStart, yStart);
                currentLine.LineTo(x, y);
                break;
        }
        canvas.invalidate();
    }

    private void touchEnd()
    {
        if((selectedTool >= 0) && (selectedTool <= 3))
        {
            canvas.StoreShape();
        }
        else if((selectedTool >= 4) && (selectedTool <= 5))
        {
            currentLine = null;
        }
        canvas.invalidate();
    }
}