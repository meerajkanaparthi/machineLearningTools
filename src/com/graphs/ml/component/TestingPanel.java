package com.graphs.ml.component;

import com.graphs.ml.events.StringListener;
import com.graphs.ml.model.LineData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestingPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private JTextArea textArea;
    private LineData line;

    private StringListener stringListener;

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
    public TestingPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 350;
        dim.height = 400;
        setPreferredSize(dim);
        nameLabel = new JLabel("X: ");
        occupationLabel = new JLabel("Y: ");
        occupationField = new JTextField(10);
        nameField = new JTextField(10);
        textArea = new JTextArea();
        okBtn = new JButton("ok");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(line !=null && stringListener != null){
                    double x = Double.parseDouble(nameField.getText());
                    double y = Double.parseDouble(occupationField.getText());
                    double expectedValue = line.getSlope() *x + line.getInterceptor();
                    double value = y - line.getSlope() * x;
                    if(value > line.getInterceptor()) {
                        System.out.println("A value : "+ value + " intercept: " + line.getInterceptor());
                        stringListener.textEmitted("Class A , and expected value : "+expectedValue);
                    } else {
                        System.out.println("B value : "+ value + " intercept: " + line.getInterceptor());
                        stringListener.textEmitted("Class B and expected value : "+expectedValue);
                    }
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Testing Panel");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        layoutComponents();
    }

    public void setLine(LineData line) {
        this.line = line;
    }


    public void layoutComponents(){

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        //////////// First row //////////////////////////
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(nameField, gc);
        //////////// Second row //////////////////////////
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(occupationLabel, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(occupationField, gc);

        //////////// Fifth row //////////////////////////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(okBtn, gc);



    }

}
