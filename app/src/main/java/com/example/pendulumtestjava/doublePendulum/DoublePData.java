package com.example.pendulumtestjava.doublePendulum;

import java.util.ArrayList;

public class DoublePData {
    private static DoublePData instance;

    private double r1;
    private double r2;
    private double a1;
    private double a2;
    private double g;
    private double m1;
    private double m2;
    private int trace1;
    private int trace2;
    private int trace1Color;
    private int trace2Color;
    private int ball1Color;
    private int ball2Color;
    private boolean endlessTrace1;
    private boolean endlessTrace2;
    private boolean isTrace1On;
    private boolean isTrace2On;
    private boolean stop;
    private ArrayList<Float> points1;
    private ArrayList<Float> points2;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public ArrayList<Float> getPoints1() {
        return points1;
    }

    public void setPoints1(ArrayList<Float> points1) {
        this.points1 = points1;
    }

    public ArrayList<Float> getPoints2() {
        return points2;
    }

    public void setPoints2(ArrayList<Float> points2) {
        this.points2 = points2;
    }

    public boolean isTrace1On() {
        return isTrace1On;
    }

    public void setTrace1On(boolean trace1On) {
        isTrace1On = trace1On;
    }

    public boolean isTrace2On() {
        return isTrace2On;
    }

    public void setTrace2On(boolean trace2On) {
        isTrace2On = trace2On;
    }

    public boolean isEndlessTrace1() {
        return endlessTrace1;
    }

    public void setEndlessTrace1(boolean endlessTrace1) {
        this.endlessTrace1 = endlessTrace1;
    }

    public boolean isEndlessTrace2() {
        return endlessTrace2;
    }

    public void setEndlessTrace2(boolean endlessTrace2) {
        this.endlessTrace2 = endlessTrace2;
    }

    public int getBall1Color() {
        return ball1Color;
    }

    public void setBall1Color(int ball1Color) {
        this.ball1Color = ball1Color;
    }

    public int getBall2Color() {
        return ball2Color;
    }

    public void setBall2Color(int ball2Color) {
        this.ball2Color = ball2Color;
    }

    public double getR1() {
        return r1;
    }

    public void setR1(double r1) {
        this.r1 = r1;
    }

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = Math.toRadians(a1);
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = Math.toRadians(a2);
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getM1() {
        return m1;
    }

    public void setM1(double m1) {
        this.m1 = m1;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public int getTrace1() {
        return trace1;
    }

    public void setTrace1(int trace1) {
        this.trace1 = trace1;
    }

    public int getTrace2() {
        return trace2;
    }

    public void setTrace2(int trace2) {
        this.trace2 = trace2;
    }

    public int getTrace1Color() {
        return trace1Color;
    }

    public void setTrace1Color(int color1) {
        this.trace1Color = color1;
    }

    public int getTrace2Color() {
        return trace2Color;
    }

    public void setTrace2Color(int color2) {
        this.trace2Color = color2;
    }

    public static synchronized DoublePData getInstance() {
        if (instance == null) {
            instance = new DoublePData();
        }
        return instance;
    }

    public void resetValues() {
        r1 = 250;
        r2 = 250;
        a1 = Math.PI / 2;
        a2 = Math.PI / 2;
        g = 1;
        m1 = 10;
        m2 = 10;
        trace1 = 250;
        trace2 = 250;
        trace1Color = 0xFFFF0000;
        trace2Color = 0xFF0000FF;
        ball1Color = 0xFFFF0000;
        ball2Color = 0xFF0000FF;
        endlessTrace1 = false;
        endlessTrace2 = false;
        isTrace1On = true;
        isTrace2On = true;
        stop = false;
        points1 = new ArrayList<>();
        points2 = new ArrayList<>();

    }
}
