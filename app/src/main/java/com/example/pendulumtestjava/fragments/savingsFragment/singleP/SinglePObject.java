package com.example.pendulumtestjava.fragments.savingsFragment.singleP;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "single_table")
public class SinglePObject {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double a;
    private double r;
    private float g;
    private float damping;
    private int trace;
    private int ballColor;
    private int traceColor;
    private String pointsJson;
    private String timeStamp;
    private boolean infinity;
    private boolean isTraceOn;

    public SinglePObject(double a, double r, float g, float damping, int trace,
                         int ballColor, int traceColor, String pointsJson,
                         String timeStamp, boolean infinity, boolean isTraceOn) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.damping = damping;
        this.trace = trace;
        this.ballColor = ballColor;
        this.traceColor = traceColor;
        this.pointsJson = pointsJson;
        this.timeStamp = timeStamp;
        this.infinity = infinity;
        this.isTraceOn = isTraceOn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getA() {
        return a;
    }

    public double getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getDamping() {
        return damping;
    }

    public int getTrace() {
        return trace;
    }

    public int getBallColor() {
        return ballColor;
    }

    public int getTraceColor() {
        return traceColor;
    }

    public String getPointsJson() {
        return pointsJson;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean isInfinity() {
        return infinity;
    }

    public boolean isTraceOn() {
        return isTraceOn;
    }
}
