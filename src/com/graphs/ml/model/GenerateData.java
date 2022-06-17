package com.graphs.ml.model;

public class GenerateData {

    private int id;
    private double xX;
    private double xY;
    private double xX_yY;
    private double xX2;

    public GenerateData(int id, double xX, double xY, double xX_yY, double xX2) {
        this.id = id;
        this.xX = xX;
        this.xY = xY;
        this.xX_yY = xX_yY;
        this.xX2 = xX2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getxX() {
        return xX;
    }

    public void setxX(double xX) {
        this.xX = xX;
    }

    public double getxY() {
        return xY;
    }

    public void setxY(double xY) {
        this.xY = xY;
    }

    public double getxX_yY() {
        return xX_yY;
    }

    public void setxX_yY(double xX_yY) {
        this.xX_yY = xX_yY;
    }

    public double getxX2() {
        return xX2;
    }

    public void setxX2(double xX2) {
        this.xX2 = xX2;
    }
}
