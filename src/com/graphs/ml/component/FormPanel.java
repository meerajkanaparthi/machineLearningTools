package com.graphs.ml.component;

import com.graphs.ml.events.DataPointEvent;
import com.graphs.ml.events.DataPointListener;
import com.graphs.ml.events.FormEvent;
import com.graphs.ml.events.FormListeners;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private DataPointListener dataPointListener;
    private JComboBox classCombo;
    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 300;
        dim.height = 300;
        setPreferredSize(dim);

        nameLabel = new JLabel("X: ");
        occupationLabel = new JLabel("Y: ");
        occupationField = new JTextField(10);
        classCombo = new JComboBox();
        DefaultComboBoxModel empMode = new DefaultComboBoxModel();
        empMode.addElement("Class A");
        empMode.addElement("Class B");
        classCombo.setModel(empMode);
        nameField = new JTextField(10);
        okBtn = new JButton("ok");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x = nameField.getText();
                String y = occupationField.getText();
                String classType =(String) classCombo.getSelectedItem();
                int index = 0;
                if(classType.equals("Class A")){
                    index = 0;
                } else {
                    index =1;
                }

                DataPointEvent dataPointEvent = new DataPointEvent(this,Integer.parseInt(x),Integer.parseInt(y), index);
                if(dataPointListener !=null){
                    dataPointListener.dataPointEvent(dataPointEvent);
                }
            }
        });


        Border innerBorder = BorderFactory.createTitledBorder("Add Entity");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        layoutComponents();
    }

    public void setDataPointListener(DataPointListener dataPointListener){
        this.dataPointListener = dataPointListener;
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

        //////////// Fourth row //////////////////////////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;


        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Type:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(classCombo, gc);

        //////////// Fifth row //////////////////////////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(okBtn, gc);


    }


}