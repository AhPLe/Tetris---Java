package com.arthur.tetris.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestingLayout extends JFrame{

    public static void main(String[] args) {
        new TestingLayout();
    }

    public TestingLayout(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 800);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        JPanel container = new JPanel();
        JPanel panelOne = new JPanel();
        Dimension d = new Dimension(200, 800);
        panelOne.setSize(d);
        JPanel panelTwo = new JPanel();
        panelTwo.setSize(d);

        panelOne.add(new JLabel("1"));
        panelTwo.add(new JLabel("2"));

        container.setLayout(new GridLayout(1,2));
        container.add(panelOne);
        container.add(panelTwo);
        panelOne.setVisible(true);
        panelTwo.setVisible(true);
        container.setVisible(true);


        this.add(container);
        panelOne.setBackground(Color.RED);
        panelTwo.setBackground(Color.BLACK);
        this.getIgnoreRepaint();
        panelOne.repaint();
        panelTwo.repaint();
    }
}