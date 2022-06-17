package com.graphs.ml.events;

import java.util.EventObject;

public class DataPointEvent extends EventObject {
    private int x;
    private int y;
    private int category;

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

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DataPointEvent(Object source) {
        super(source);
    }

    public DataPointEvent(Object source, int x, int y, int category) {
        super(source);
        this.x = x;
        this.y = y;
        this.category = category;
    }
}
