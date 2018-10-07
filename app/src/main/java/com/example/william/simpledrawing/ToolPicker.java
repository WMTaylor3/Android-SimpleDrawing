package com.example.william.simpledrawing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ToolPicker  extends AppCompatActivity
{
    // -- Variables -- //
    ImageView toolPreview;
    
// -- Override Methods -- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_picker);
        this.setFinishOnTouchOutside(false);


    }
}
