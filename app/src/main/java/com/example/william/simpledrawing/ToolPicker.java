//Floating activity called from Edit Image, returns an integer indicating the current tool.
// From there the edit image class calls the appropriate method when the user draws to the screen.

package com.example.william.simpledrawing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;

public class ToolPicker  extends AppCompatActivity
{
    // -- Variables -- //
    ImageView toolPreview;
    Button squareButton;
    Button circleButton;
    Button triangleButton;
    Button starButton;
    Button brushButton;
    Button lineButton;
    Button doneButton;
    CheckBox filledCheckBox;
    SeekBar brushSize;

    int currentTool;
    int currentSize;
    boolean checkFilled;

// -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_picker);
        this.setFinishOnTouchOutside(false);

        Intent intent = getIntent();
        currentTool = intent.getIntExtra("tool", 0);
        currentSize = intent.getIntExtra("size", 0);
        checkFilled = intent.getBooleanExtra("filled", false);
        init();
    }

    // -- User Defined Methods -- //
    private void init()
    {
        toolPreview = findViewById(R.id.toolPreview);

        squareButton = findViewById(R.id.buttonSquare);
        circleButton = findViewById(R.id.buttonCircle);
        triangleButton = findViewById(R.id.buttonTriangle);
        starButton = findViewById(R.id.buttonStar);
        brushButton = findViewById(R.id.buttonBrush);
        lineButton = findViewById(R.id.buttonLine);
        doneButton = findViewById(R.id.buttonDone);
        filledCheckBox = findViewById(R.id.checkBoxFilled);
        brushSize = findViewById(R.id.seekBarSize);



        squareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentTool = 0;
                update();
            }
        });
        circleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentTool = 1;
                update();
            }
        });
        triangleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentTool = 2;
                update();
            }
        });
        starButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentTool = 3;
                update();
            }
        });
        brushButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentTool = 4;
                update();
            }
        });
        lineButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentTool = 5;
                update();
            }
        });
        filledCheckBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkFilled = filledCheckBox.isChecked();
                update();
            }
        });
        brushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //Empty
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                //Empty
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                currentSize = brushSize.getProgress();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent data = new Intent();
                data.putExtra("tool", currentTool);
                data.putExtra("size", currentSize);
                data.putExtra("filled", checkFilled);
                setResult(RESULT_OK, data);

                finish();
            }
        });

        filledCheckBox.setChecked(checkFilled);
        brushSize.setProgress(currentSize);

        update();
    }

    private void update()
    {
        checkFilled = filledCheckBox.isChecked();

        if(checkFilled)
        {
            switch(currentTool)
            {
                case 0: //Square
                    toolPreview.setImageResource(R.drawable.usr_square_black);
                    break;
                case 1: //
                    toolPreview.setImageResource(R.drawable.usr_circle_black);
                    break;
                case 2: //Triangle
                    toolPreview.setImageResource(R.drawable.usr_triangle_black);
                    break;
                case 3: //Star
                    toolPreview.setImageResource(R.drawable.ic_star_black);
                    break;
                case 4: //Brush
                    toolPreview.setImageResource(R.drawable.ic_brush_black);
                    break;
                case 5: //Line
                    toolPreview.setImageResource(R.drawable.usr_line);
                    break;
            }
        }
        else
        {
            switch(currentTool)
            {
                case 0: //Square
                    toolPreview.setImageResource(R.drawable.usr_square_border_black);
                    break;
                case 1: //Circle
                    toolPreview.setImageResource(R.drawable.usr_circle_border_black);
                    break;
                case 2: //Triangle
                    toolPreview.setImageResource(R.drawable.usr_triangle_border_black);
                    break;
                case 3: //Star
                    toolPreview.setImageResource(R.drawable.ic_star_border_black);
                    break;
                case 4: //Brush
                    toolPreview.setImageResource(R.drawable.ic_brush_black);
                    break;
                case 5: //Line
                    toolPreview.setImageResource(R.drawable.usr_line);
                    break;
            }
        }
    }
}
