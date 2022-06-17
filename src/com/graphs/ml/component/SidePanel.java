package com.graphs.ml.component;

import com.graphs.ml.events.DataPointListener;
import com.graphs.ml.events.StringListener;
import com.graphs.ml.model.LineData;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private FormPanel formPanel;
    private TestingPanel testingPanel;

    private TextPanel textPanel;

    public SidePanel(){
        Dimension dim = getPreferredSize();
        dim.width = 350;
        setPreferredSize(dim);
        this.formPanel = new FormPanel();
        this.testingPanel = new TestingPanel();
        this.testingPanel.setStringListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
        });
        this.textPanel = new TextPanel();
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(testingPanel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.SOUTH);
    }


    public void setLine(LineData line){
        this.testingPanel.setLine(line);
    }
    public void setDataPointListener(DataPointListener dataPointListener){
        this.formPanel.setDataPointListener(dataPointListener);
    }
}
