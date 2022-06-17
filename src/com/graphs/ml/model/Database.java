package com.graphs.ml.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private ArrayList<Data> data;
    public Database(){
        data = new ArrayList<Data>();
    }

    public void addData(Data dat){
        data.add(dat);
    }

    public List<Data> getData(){
        return data;
    }

    public void clear(){
        data.clear();
    }

}
