//CODE FROM ASSIGNMENT 2 BETWEEN THIS START COMMENT AND CORRESPONDING END COMMENT
package com.example.william.simpledrawing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

public class SingleImage extends AppCompatActivity
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
        Uri imageURI = Uri.parse(intent.getStringExtra("Location"));
        Integer imageRotation = intent.getIntExtra("Orientation", 0);
        imageView = findViewById(R.id.photo);
        imageView.setImageURI(imageURI);
        imageView.setRotation(imageRotation);

        imageView=findViewById(R.id.photo);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
