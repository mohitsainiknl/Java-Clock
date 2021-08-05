/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

public class ClockFrame extends JFrame {
	public static final int OPACITY_100_PERCENT = 255;
	public static final int OPACITY_80_PERCENT = (255 * 80) / 100;
	public static final int OPACITY_50_PERCENT = (255 * 50) / 100;
	public static final int OPACITY_0_PERCENT = 0;
	
    private int frameWidth = 298;
    private int frameHeight = 338 + 29;
    private JPanel mainPanel;
    private int opacity;

    public ClockFrame(int opacity) {
        super();
    	this.opacity = opacity;
        setDefaultLookAndFeelDecorated(true);

        setSize(frameWidth, frameHeight);

        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
    }

    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        makeClockFrame();
    }

    private void makeClockFrame() {
        mainPanel = new JPanel(new BorderLayout()){
            @Override
            public void paintComponent(Graphics g) {
                Paint paint = new Color(255, 255, 255, opacity);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(paint);
                g2d.fillRect(0, 0, frameWidth, frameHeight);
            }
        };
        setContentPane(mainPanel);
    }
}
