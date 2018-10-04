package com.example.william.simpledrawing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditImage extends AppCompatActivity
{
    // -- Variables and Constants -- //
    public static final int REQUEST_CODE = 1;
    private int selectedColor;

    // -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_edit);

        FloatingActionButton openColorPicker = findViewById(R.id.fab);
        openColorPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(EditImage.this, ColorPicker.class);
                startActivityForResult(intent, selectedColor);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK)
        {
            selectedColor = data.getIntExtra("color", 0);
        }
    }
}