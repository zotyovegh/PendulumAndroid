package com.example.pendulumtestjava.singlePendulum;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pendulumtestjava.DrawingPath;
import com.example.pendulumtestjava.R;

import java.util.Timer;
import java.util.TimerTask;


public class SinglePendulum extends AppCompatActivity implements View.OnClickListener {
    private TextView stick, ball, middle;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private DrawingPath path;
    private SinglePSettings singlePSettings = new SinglePSettings();
    private SinglePData data = SinglePData.getInstance();

    private Button reset, pause, settings;
    private double widthMiddleBall, heightMiddleBall;
    private double widthMiddle, heightPoint;
    private double angularAcc, angularVel;

    private double a = data.getA();
    private float gravity = data.getGravity();
    private float damping = data.getDamping();
    private boolean stop = false;
    private int traceDrawColor = data.getTraceDrawColor();
    private int ballDrawColor = data.getBallDrawColor();
    private int trace = data.getTrace();
    private double r = data.getR();
    private double x, y;
    private boolean onHold;
    private boolean endlessTrace = data.isEndlessTrace();
    private boolean isTraceOn = data.isTraceOn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pendulum);

        stick = (TextView) findViewById(R.id.stickBox);
        ball = (TextView) findViewById(R.id.ballPaint);
        middle = (TextView) findViewById(R.id.middlePaint);
        path = (DrawingPath) findViewById(R.id.path);
        reset = (Button) findViewById(R.id.reset);
        pause = (Button) findViewById(R.id.pause);
        settings = (Button) findViewById(R.id.settings);


        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 2;
        widthMiddleBall = widthMiddle - 30;
        heightMiddleBall = heightPoint - 30;

        reset.setOnClickListener(this);
        pause.setOnClickListener(this);
        settings.setOnClickListener(this);

        update();
    }

    public void update() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    if (!stop && !onHold) {
                        calc();
                    }
                    draw();
                });
            }
        }, 0, 10);
    }

    public void draw() {
        stick.setRotation((float) Math.toDegrees(-a));
        stick.setX((float) (widthMiddle - 2));
        stick.setY((float) (heightPoint));

        ball.setX((float) x);
        ball.setY((float) y);

        middle.setX((float) (widthMiddle - 5));
        middle.setY((float) (heightPoint - 5));
        middle.setBackgroundResource(R.color.colorPrimaryDark);
    }

    public void calc() {
        angularAcc = (-1 * gravity / r) * Math.sin(a);
        angularVel += angularAcc;
        angularVel *= damping;
        a += angularVel;

        calcPositions();
    }

    public void calcPositions() {
        x = widthMiddleBall + (r * Math.sin(a));
        y = heightMiddleBall + (r * Math.cos(a));

        stick.setLayoutParams(new FrameLayout.LayoutParams(4, (int) r));
        if(isTraceOn) {
            path.setVariables(x, y, trace, traceDrawColor, endlessTrace);
        }
        ball.getBackground().setColorFilter(ballDrawColor, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int newx = (int) event.getX() - 28;
        int newy = (int) event.getY() - 90;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                x = newx;
                y = newy;
                if (newy - heightMiddleBall > 0) {
                    a = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall));
                }
                if (newy - heightMiddleBall < 0) {
                    a = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall)) + Math.PI;
                }
                if (newy - heightMiddleBall == 0) {
                    if (newx - widthMiddleBall >= 0) {
                        a = Math.PI / 2;
                    } else {
                        a = -(Math.PI / 2);
                    }
                }
                r = Math.sqrt(((newx - widthMiddleBall) * (newx - widthMiddleBall)) + ((newy - heightMiddleBall) * (newy - heightMiddleBall)));
                stick.setLayoutParams(new FrameLayout.LayoutParams(5, (int) r));

                if(isTraceOn) {
                    path.setVariables(x, y, trace, traceDrawColor, endlessTrace);
                }


                angularVel = 0;
                angularAcc = 0;
                onHold = true;
                break;
            case MotionEvent.ACTION_UP:
                onHold = false;
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                resetVariables();
                break;
            case R.id.pause:
                stopCheck();
                break;
            case R.id.settings:
                openSettings();
                break;
        }
    }

    public void stopCheck() {
        if (stop) {
            stop = false;
        } else {
            stop = true;
        }
    }

    public void resetVariables() {
        a = data.getA();
        r = data.getR();
        gravity = data.getGravity();
        damping = data.getDamping();
        trace = data.getTrace();
        traceDrawColor = data.getTraceDrawColor();
        ballDrawColor = data.getBallDrawColor();

        angularVel = 0;
        angularAcc = 0;

        calcPositions();
        draw();
        endlessTrace = data.isEndlessTrace();
        isTraceOn = data.isTraceOn();
        path.reset();
    }

    public void openSettings()
    {
        singlePSettings.show(getSupportFragmentManager(), "Settings");
    }
}
