//CODE FROM ASSIGNMENT 2 BETWEEN THIS START COMMENT AND CORRESPONDING END COMMENT
package com.example.william.simpledrawing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

public class ExpandedImage extends AppCompatActivity
{
    // -- Variables -- //
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    private ImageView imageView;

    private View.OnClickListener onEditClick;

    // -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_expanded);

        Intent intent = getIntent();
        final String stringLocation = intent.getStringExtra("Location");
        final Uri imageURI = Uri.parse(stringLocation);
        final Integer imageRotation = intent.getIntExtra("Orientation", 0);
        imageView = findViewById(R.id.photo);
        imageView.setImageURI(imageURI);
        imageView.setRotation(imageRotation);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        //--------------------------------------------------------------------------------------------//
        //---CODE WRITTEN FOR ASSIGNMENT 3 BETWEEN THIS START COMMENT AND CORRESPONDING END COMMENT---//
        //--------------------------------------------------------------------------------------------//
        final FloatingActionButton editImage = findViewById(R.id.edit);

        onEditClick = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ExpandedImage.this, EditImage.class);
                intent.putExtra("BackgroundExists", true);
                intent.putExtra("Location", stringLocation);

                int[] location = new int[2];
                imageView.getLocationOnScreen(location);

                int xStart = location[0];
                int yStart = location[1];
                int xEnd = xStart + imageView.getWidth();
                int yEnd = yStart + imageView.getHeight();
                int width = xEnd - xStart;
                int height = yEnd - yStart;
                int xCenter = xStart + (width/2);
                int yCenter = yStart + (height/2);
                int leftFromCenter = xCenter - xStart;
                int rightFromCenter = xEnd - xCenter;
                int topFromCenter = yCenter - yStart;
                int bottomFromCenter = yEnd - yCenter;
                xStart -= leftFromCenter;
                xEnd += rightFromCenter;
                yStart -= topFromCenter;
                yEnd += bottomFromCenter;

                intent.putExtra("xStart", xStart);
                intent.putExtra("yStart", yStart);
                intent.putExtra("xEnd", xEnd);
                intent.putExtra("yEnd", yEnd);

                Log.e("======================", Integer.toString(xStart));
                Log.e("======================", Integer.toString(yStart));
                Log.e("======================", Integer.toString(xEnd));
                Log.e("======================", Integer.toString(yEnd));

                startActivity(intent);
            }
        };

        editImage.setOnClickListener(onEditClick);
        //--------------------------------------------------------------------------------------------//
        //---CODE WRITTEN FOR ASSIGNMENT 3 BETWEEN THIS END COMMENT AND CORRESPONDING START COMMENT---//
        //--------------------------------------------------------------------------------------------//
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    // -- Classes -- //
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector)
        {
            scaleFactor *= scaleGestureDetector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            imageView.setScaleX(scaleFactor);
            imageView.setScaleY(scaleFactor);
            return true;
        }
    }
//CODE FROM ASSIGNMENT 2 BETWEEN THIS END COMMENT AND CORRESPONDING START COMMENT
}
