package com.graphs.ml;

import com.graphs.ml.component.*;
import com.graphs.ml.controller.Controller;
import com.graphs.ml.events.*;
import com.graphs.ml.model.LineData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame {
    private MLPanel mlPanel;
    private Toolbar toolbar;
    private SidePanel sidePanel;
    private Controller controller;
    private TablePanel tablePanel;
    public MainFrame(){
        super("Machine learning");
        setLayout(new BorderLayout());
        mlPanel = new MLPanel();
        toolbar = new Toolbar();
        controller = new Controller();
        DataPointListener dataPointListener = getDataPointListener();
        toolbar.setDataPointListener(dataPointListener);
        tablePanel = new TablePanel();
        tablePanel.setData(controller.getData());
        mlPanel.setData(controller.getData());
        sidePanel = new SidePanel();
        sidePanel.setDataPointListener(dataPointListener);
        add(mlPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.EAST);
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private DataPointListener getDataPointListener() {
        return new DataPointListener() {
            @Override
            public void dataPointEvent(DataPointEvent event) {
                controller.addData(event);
                tablePanel.refresh();
                mlPanel.refresh();
            }

            @Override
            public void clearDataPointEvent() {
                controller.clear();
                tablePanel.refresh();
                mlPanel.refresh();
            }

            @Override
            public void generate() {
                LineData line = tablePanel.generate();
                sidePanel.setLine(line);
                mlPanel.generate(line);
            }
        };
    }

}
