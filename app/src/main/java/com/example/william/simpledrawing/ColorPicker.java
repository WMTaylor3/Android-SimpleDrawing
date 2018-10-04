package com.example.william.simpledrawing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class ColorPicker extends AppCompatActivity
{
    // -- Variables -- //
    private ImageView colorPreview;

    private SeekBar redSlider;
    private SeekBar greenSlider;
    private SeekBar blueSlider;

    private TextView redValueDisplay;
    private TextView greenValueDisplay;
    private TextView blueValueDisplay;

    SeekBar.OnSeekBarChangeListener seekBarChangeListener;

    // -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker);
        this.setFinishOnTouchOutside(false);

        Intent intent = getIntent();
        int redState = intent.getIntExtra("redValue", 0);
        int greenState = intent.getIntExtra("greenValue", 0);
        int blueState = intent.getIntExtra("blueValue", 0);

        init(redState, greenState, blueState);
    }

    // -- User Defined Methods -- //
    private void init(int redState, int greenState, int blueState)
    {
        //Initialize all variables.
        colorPreview = findViewById(R.id.colorPreview);

        redSlider = findViewById(R.id.redSlider);
        greenSlider = findViewById(R.id.greenSlider);
        blueSlider = findViewById(R.id.blueSlider);
        redSlider.setProgress(redState);
        greenSlider.setProgress(greenState);
        blueSlider.setProgress(blueState);
        redSlider.setMax(255);
        greenSlider.setMax(255);
        blueSlider.setMax(255);

        Button resetButton = findViewById(R.id.buttonReset);
        Button doneButton = findViewById(R.id.buttonDone);

        redValueDisplay = findViewById(R.id.redValue);
        greenValueDisplay = findViewById(R.id.greenValue);
        blueValueDisplay = findViewById(R.id.blueValue);

        seekBarChangeListener = new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // Empty Implement
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // Empty Implement
            }
        };

        redSlider.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSlider.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSlider.setOnSeekBarChangeListener(seekBarChangeListener);

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                redSlider.setProgress(0);
                greenSlider.setProgress(0);
                blueSlider.setProgress(0);
                update();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String redInHex = Integer.toString(redSlider.getProgress(), 16);
                String greenInHex = Integer.toString(greenSlider.getProgress(), 16);
                String blueInHex = Integer.toString(blueSlider.getProgress(), 16);

                if (redInHex.length() == 1) { redInHex = "0" + redInHex; }
                if (greenInHex.length() == 1) { greenInHex = "0" + greenInHex; }
                if (blueInHex.length() == 1) { blueInHex = "0" + blueInHex; }

                String completeColor = "#";
                completeColor = completeColor + redInHex;
                completeColor = completeColor + greenInHex;
                completeColor = completeColor + blueInHex;

                int colorToReturn = Color.parseColor(completeColor);

                Intent data = new Intent();
                data.putExtra("color", colorToReturn);
                setResult(RESULT_OK, data);

                finish();
            }
        });

        update();
    }

    private void update()
    {
        redValueDisplay.setText(String.format(Locale.getDefault(), "%d", redSlider.getProgress()));
        greenValueDisplay.setText(String.format(Locale.getDefault(), "%d", greenSlider.getProgress()));
        blueValueDisplay.setText(String.format(Locale.getDefault(), "%d", blueSlider.getProgress()));

        colorPreview.setBackgroundColor(Color.rgb(redSlider.getProgress(), greenSlider.getProgress(), blueSlider.getProgress()));
    }
}
