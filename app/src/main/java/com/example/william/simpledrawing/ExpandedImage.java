//CODE FROM ASSIGNMENT 2 BETWEEN THIS START COMMENT AND CORRESPONDING END COMMENT
package com.example.william.simpledrawing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

public class ExpandedImage extends AppCompatActivity
{
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    private ImageView imageView;

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
        FloatingActionButton editImage = findViewById(R.id.edit);
        editImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ExpandedImage.this, EditImage.class);
                intent.putExtra("photoBackground", true);
                intent.putExtra("Location", stringLocation);
                intent.putExtra("Orientation", imageRotation);
                intent.putExtra("Scale", scaleFactor);
                startActivity(intent);
            }
        });
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
