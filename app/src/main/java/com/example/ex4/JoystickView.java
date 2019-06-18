package com.example.ex4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;

public class JoystickView extends View {
    private Paint color_in;
    private Paint color_out;
    private int r_in;
    private int r_out;
    private int x_in;
    private int y_in;
    private int x_out;
    private int y_out;
    private boolean move = false;
    private boolean pressed = false;

    public JoystickView(Context context) {
        super(context);
        color_in = new Paint(Paint.ANTI_ALIAS_FLAG);
        color_out = new Paint(Paint.ANTI_ALIAS_FLAG);
        color_in.setColor(Color.BLUE);
        color_out.setColor(Color.YELLOW);
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
        RectF in  = new RectF(x_in - r_in, y_in - r_in, x_in+r_in, y_in+r_in);
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
                    x_in = (int)x;
                    y_in = (int)y;
                    //send to someone....
                }

            }
        }
    }

    private float dis(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }
    private boolean inside_bounderies(float x,  float y) {
        if (dis(x,y,x_out, y_out) < r_out) {
            return true;
        }
        return false;

    }
}
