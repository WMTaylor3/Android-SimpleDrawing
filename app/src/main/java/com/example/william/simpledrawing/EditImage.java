package com.example.william.simpledrawing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditImage extends AppCompatActivity
{
    // -- Variables and Constants -- //
    private DrawableCanvas canvas;
    private int selectedColor = 0;
    private int selectedTool = 0;
    private int toolSize = 1;
    private boolean shapeFilled = false;

    // -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_edit);

        canvas = findViewById(R.id.drawableView);
        canvas.drawTriangle(true, 4, 100, 100, 200, 200, Color.RED);
        canvas.drawTriangle(false, 8, 300, 100, 400, 200, Color.GREEN);
        canvas.drawTriangle(true, 4, 500, 500, 700, 600, Color.BLUE);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                selectedColor = data.getIntExtra("color", 0);
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                selectedTool = data.getIntExtra("tool", 0);
                toolSize = data.getIntExtra("size", 0);
                shapeFilled = data.getBooleanExtra("filled", false);
            }
        }
    }
}