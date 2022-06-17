package com.graphs.ml.component;

import com.graphs.ml.events.DataPointListener;
import com.graphs.ml.events.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton helloButton;
    private JButton goodbyeButton;
    private DataPointListener dataPointListener;
    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());
        helloButton = new JButton("Generate");
        helloButton.addActionListener(this);
        goodbyeButton = new JButton("Clear");
        goodbyeButton.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloButton);
        add(goodbyeButton);

    }

    public void setDataPointListener(DataPointListener dataPointListener){
        this.dataPointListener = dataPointListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        if(clicked == helloButton){
            if (dataPointListener != null){
                dataPointListener.generate();
            }
        } else if(clicked == goodbyeButton) {
            if(dataPointListener != null ){
                dataPointListener.clearDataPointEvent();
            }
        }

    }
}
