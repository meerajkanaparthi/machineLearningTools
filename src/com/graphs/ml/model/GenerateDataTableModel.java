package com.graphs.ml.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GenerateDataTableModel extends AbstractTableModel {

    private List<GenerateData> data = new ArrayList<GenerateData>();

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GenerateData dat = data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return dat.getxX();
            case 1:
                return dat.getxY();
            case 2:
                return dat.getxX_yY();
            case 3:
                return dat.getxX2();
        }
        return null;
    }

    public void setData(List<GenerateData> data) {
        this.data = data;
    }
}
