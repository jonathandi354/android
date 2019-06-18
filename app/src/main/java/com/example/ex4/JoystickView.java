package com.example.ex4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;

public class JoystickView extends View {
    private Paint color_in;
    private Paint color_out;
    private int r_in = 100;
    private int r_out = 500;
    private int x_in;
    private int y_in;
    private int x_out;
    private int y_out;
    private boolean move = false;
    private boolean pressed = false;
    private int x_cur;
    private int y_cur;
    public JoystickView(Context context) {
        super(context);
        color_in = new Paint(Paint.ANTI_ALIAS_FLAG);
        color_out = new Paint(Paint.ANTI_ALIAS_FLAG);
        color_in.setColor(Color.BLUE);
        color_out.setColor(Color.YELLOW);
        int mWidth= this.getResources().getDisplayMetrics().widthPixels;
        int mHeight= this.getResources().getDisplayMetrics().heightPixels;
        x_in = mWidth / 2;
        y_in = mHeight / 2;
        x_out = mWidth / 2;
        y_out = mHeight / 2;
        x_cur = x_in;
        y_cur = y_in;

    }
    @Override
    ///////////////////////
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int xPad = getPaddingLeft() + getPaddingRight();
        int yPad = getPaddingTop() + getPaddingBottom();


    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF out = new RectF(x_out - r_out, y_out - r_out, x_out+r_out, y_out+r_out);
        RectF in  = new RectF(x_cur - r_in, y_cur - r_in, x_cur+r_in, y_cur+r_in);
        canvas.drawOval(out, color_out);
        canvas.drawOval(in, color_in);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action){
            case MotionEvent.ACTION_DOWN:
            {
                float x = event.getX();
                float y = event.getY();
                if(dis(x, y, x_in, y_in) < r_in) {
                    pressed = true;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!pressed) {
                    return true;
                }
                float x = event.getX();
                float y = event.getY();

                if (inside_bounderies(x,y)) {
                    x_cur = (int)x;
                    y_cur = (int)y;
                    //send to someone....
                    invalidate();
                }
                break;

            }
            case MotionEvent.ACTION_UP: {
                x_cur = x_in;
                y_cur = y_in;
                invalidate();

            }
            case MotionEvent.ACTION_CANCEL: {
                pressed = false;
                break;
            }

        }
        return true;
    }



    private float dis(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }
    private boolean inside_bounderies(float x,  float y) {
        if (dis(x,y,x_out, y_out) < r_out - r_in) {
            return true;
        }
        return false;

    }
}
