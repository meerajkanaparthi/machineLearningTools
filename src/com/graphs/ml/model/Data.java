package com.graphs.ml.model;

public class Data {
    private static int count = 0;
    private int id;
    int x;
    int y;
    int category;

    public Data(int x, int y, int category) {
        this.x = x;
        this.y = y;
        this.category = category;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
