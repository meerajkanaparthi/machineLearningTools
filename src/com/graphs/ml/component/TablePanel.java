package com.graphs.ml.component;

import com.graphs.ml.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    private static final int PREF_W = 600;
    private static final int PREF_H = 650;
    private JTable table;

    private JTable generateTable;
    private DataTableModel tableModel;
    private GenerateDataTableModel generateTableModel;

    private TextPanel textPanel;

    public TablePanel() {
        tableModel = new DataTableModel();
        table = new JTable(tableModel);
        textPanel = new TextPanel();
        generateTableModel = new GenerateDataTableModel();
        generateTable = new JTable(generateTableModel);
        setLayout(new BorderLayout());
        add(table,BorderLayout.WEST);
        add(generateTable,BorderLayout.EAST);
        add(textPanel, BorderLayout.SOUTH);
    }

    public void setData(java.util.List<Data> data){
        tableModel.setData(data);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public LineData generate() {
        double xMean = tableModel.getXMean();
        textPanel.appendText("x mean : "+xMean+"\n");
        double yMean = tableModel.getYMean();
        textPanel.appendText("y mean : "+yMean+"\n");
        java.util.List<Data> data = tableModel.getData();
        java.util.List<GenerateData> gData = new java.util.ArrayList<GenerateData>();
        for(Data dat : data){
            double xX = dat.getX()-xMean;
            textPanel.appendText("xX : "+xX+"\n");
            double yY = dat.getY()-yMean;
            textPanel.appendText("yY : "+yY+"\n");
            GenerateData generateData = new GenerateData(dat.getId(),xX, yY, xX * yY, xX * xX );
            gData.add(generateData);
        }
        double xXyY = gData.stream().mapToDouble(GenerateData::getxX_yY).sum();
        textPanel.appendText("xXyY : "+xXyY+"\n");
        double xX2 = gData.stream().mapToDouble(GenerateData::getxX2).sum();
        textPanel.appendText("xX2 : "+xX2+"\n");
        double slope = xXyY/xX2;
        textPanel.appendText("slope : "+slope+"\n");
        double intercept = yMean - slope * xMean;
        textPanel.appendText("intercept : "+intercept+"\n");
        int minX = data.stream().mapToInt(Data::getX).min().getAsInt();
        double minY = slope * minX + intercept;
        int maxX = data.stream().mapToInt(Data::getX).max().getAsInt();
        double maxY = slope * maxX + intercept;
        generateTableModel.setData(gData);
        generateTableModel.fireTableDataChanged();

        DataPoint startPoint = new DataPoint(new Double(minX), minY);
        DataPoint endPoint = new DataPoint(new Double(maxX), maxY);
        LineData line = new LineData(slope, intercept, startPoint, endPoint);
        textPanel.appendText("line : "+line+"\n");
        return line;
    }
}
