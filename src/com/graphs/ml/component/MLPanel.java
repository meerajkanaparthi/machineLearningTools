package com.graphs.ml.component;

import com.graphs.ml.model.Data;
import com.graphs.ml.model.DataPoint;
import com.graphs.ml.model.LineData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MLPanel extends JPanel {
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int MAX_SCORE = 20;

    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private static final int Y_HATCH_CNT = 10;
    int marg = 60;

    private java.util.List<Data> scores;
    private LineData line;
    public MLPanel() {
        scores = new ArrayList<Data>();
       /* Data data = new Data(10,10,1);
        scores.add(data);*/
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public void setDataPoint(int x, int y, int category){
        Data data = new Data(x,y,category);
        scores.add(data);
        this.repaint();
    }

    public void clear(){
        scores = new ArrayList<Data>();
        this.repaint();
    }



    protected void paintComponent(Graphics grf){
        //create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D)grf;

        //Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        // draw graph
        graph.draw(new Line2D.Double(marg, marg, marg, height-marg));
        graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));

        //find value of x and scale to plot points
        double x = (double)(width-2*marg)/(scores.size()-1);
        double scaleY = (double)(height-2*marg)/getMaxY();
        double scaleX = (double)(width-2*marg)/getMaxX();

        graph.setPaint(Color.RED);

        // set points to the graph
        for(int i=0; i<scores.size(); i++){
            if(scores.get(i).getCategory() == 0){
                graph.setPaint(Color.RED);
            } else {
                graph.setPaint(Color.BLUE);
            }
            double x1 = marg+scaleX*scores.get(i).getX();
            double y1 = height - marg-scaleY*scores.get(i).getY();
            graph.fill(new Ellipse2D.Double(x1, y1, 4, 4));
        }
        if(line!=null) {
            DataPoint startPoint = line.getStartPoint();
            DataPoint endPoint = line.getEndPoint();
            graph.setColor(GRAPH_COLOR);
            graph.setStroke(GRAPH_STROKE);
            int x1 = (new Double(marg + scaleX*startPoint.getX())).intValue();
            int y1 = (new Double(height - marg-startPoint.getY())).intValue();
            int x2 = (new Double(marg + scaleX*endPoint.getX())).intValue();
            int y2 = (new Double(height - marg-scaleY*endPoint.getY())).intValue();
            graph.drawLine(x1, y1, x2, y2);
        }
    }

    private int getMaxY(){
        int max = -Integer.MAX_VALUE;
        for(int i=0; i<scores.size(); i++){
            if(scores.get(i).getY()>max)
                max = scores.get(i).getY();

        }
        return max;
    }

    private int getMaxX(){
        int max = -Integer.MAX_VALUE;
        for(int i=0; i<scores.size(); i++){
            if(scores.get(i).getX()>max)
                max = scores.get(i).getX();

        }
        return max;
    }

    //@Override
    protected void paintComponent1(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        java.util.List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i).getY()) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }


    public void setData(java.util.List<Data> data){
        this.scores = data;
    }

    public void refresh() {
        this.repaint();
    }

    public void generate(LineData line) {
        this.line = line;
        this.repaint();
    }
}
