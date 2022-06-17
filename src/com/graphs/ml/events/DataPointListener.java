package com.graphs.ml.events;

import java.util.EventListener;

public interface DataPointListener extends EventListener {
    public void dataPointEvent(DataPointEvent event);
    public void clearDataPointEvent();

    void generate();
}
