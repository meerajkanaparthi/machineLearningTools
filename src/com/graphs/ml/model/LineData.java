package com.graphs.ml.model;

public class LineData {
    private double slope;
    private double interceptor;
    private DataPoint startDataPoint;
    private DataPoint endDataPoint;

    public LineData(DataPoint startDataPoint, DataPoint endDataPoint) {
        this.startDataPoint = startDataPoint;
        this.endDataPoint = endDataPoint;
    }

    public LineData(double slope, double interceptor, DataPoint startDataPoint, DataPoint endDataPoint) {
        this.slope = slope;
        this.interceptor = interceptor;
        this.startDataPoint = startDataPoint;
        this.endDataPoint = endDataPoint;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(double interceptor) {
        this.interceptor = interceptor;
    }

    public DataPoint getStartPoint() {
        return startDataPoint;
    }

    public void setStartPoint(DataPoint startDataPoint) {
        this.startDataPoint = startDataPoint;
    }

    public DataPoint getEndPoint() {
        return endDataPoint;
    }

    public void setEndPoint(DataPoint endDataPoint) {
        this.endDataPoint = endDataPoint;
    }

    @Override
    public String toString() {
        return "LineData{" +
                "startDataPoint=" + startDataPoint +
                ", endDataPoint=" + endDataPoint +
                '}';
    }
}
