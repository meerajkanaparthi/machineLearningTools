package com.graphs.ml.controller;

import com.graphs.ml.events.DataPointEvent;
import com.graphs.ml.model.Data;
import com.graphs.ml.model.Database;

import java.util.List;

public class Controller {
    Database db = new Database();
    public List<Data> getData(){
        return db.getData();
    }

    public void addData(DataPointEvent event){
        int x = event.getX();
        int y = event.getY();
        int category = event.getCategory();
        Data data = new Data(x, y, category);
        db.addData(data);
    }

    public void clear() {
        db.clear();
    }
}
