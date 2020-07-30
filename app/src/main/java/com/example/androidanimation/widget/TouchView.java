package com.example.androidanimation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TouchView extends View {

    int lastx,lasty;

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                lastx = (int)event.getX();
                lasty = (int)event.getY();
                break;


            case MotionEvent.ACTION_MOVE:

                int offsetX = (int)event.getX()-lastx;
                int offsetY = (int)event.getY()-lasty;

                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

                break;

            default:
                break;
        }

        return true;
    }
}
