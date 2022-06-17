package com.graphs.ml.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DataTableModel extends AbstractTableModel {

    private List<Data> data;
    public DataTableModel() {
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Data dat = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dat.getX();
            case 1:
                return dat.getY();
            case 3:
                return dat.getCategory();
        }
        return null;
    }

    public double getXMean() {
        return this.data.stream().mapToInt(Data::getX).average().getAsDouble();
    }

    public double getYMean() {
        return this.data.stream().mapToInt(Data::getY).average().getAsDouble();
    }
}
