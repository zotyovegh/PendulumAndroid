package com.example.pendulumtestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import static android.content.ContentValues.TAG;
import androidx.annotation.Nullable;

public class DrawingPath extends View {
    private Paint paint;
    private float x, y, tempX, tempY;
    private float[] p = new float[100];
    private int flag = 0;
    private int counter = 0;
    private boolean reset = false;
    private Path path;
    private int traceing;

    public DrawingPath(Context context) {
        super(context);
    }

    public DrawingPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setStrokeWidth(5);

    }

    public DrawingPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if(reset)
        {
            Log.i("Tag", "hali");
            for(int i = 0; i < 100; i++) {
                canvas.drawColor(Color.WHITE);
                p = new float[4];
                p = new float[traceing];
            }
            reset = false;
        }else{
            canvas.drawLines(p, paint);
        }


        invalidate();
    }
    public void setVariables(double x, double y, int trace, int color) {
        this.paint.setColor(color);
        int temp = trace % 4;
        traceing = trace - temp;

        if(counter == 0)
        {
            p = new float[traceing];
        }
        this.x = (float) x + 30;
        this.y = (float) y + 30;

        if (flag > p.length - 1)
        {
            flag = 0;
        } else if(counter == 0)
        {
            tempX = this.x;
            tempY = this.y;
        } else if(counter != 0)
        {
            p[flag] = tempX;
            flag++;
            p[flag] =tempY;
            flag++;
            p[flag] = this.x;
            flag++;
            p[flag] = this.y;
            flag++;

            tempX = this.x;
            tempY = this.y;
        }
        counter++;
        invalidate();
    }
    public void reset()
    {

        reset = true;
    }
}