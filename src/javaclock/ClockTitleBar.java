/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;

public class ClockTitleBar extends JPanel {
	public static final int OPACITY_100_PERCENT = 255;
	public static final int OPACITY_80_PERCENT = (255 * 80) / 100;
	public static final int OPACITY_50_PERCENT = (255 * 50) / 100;
	public static final int OPACITY_30_PERCENT = (255 * 30) / 100;
	public static final int OPACITY_0_PERCENT = 0;

    private JPanel buttonBox;
    private ClockButton closeButton;

    public ClockTitleBar() {
        super();
        setLayout(new BorderLayout());
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));

        initializeButtonBox();
    }

    private void initializeButtonBox() {
        closeButton = new ClockButton(ButtonName.CLOSE, new Color(232,17,35, 255)){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        };
        
        buttonBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonBox.setOpaque(false);
        buttonBox.setBackground(new Color(255, 255, 255, 0));
        buttonBox.add(closeButton);

        add(buttonBox, BorderLayout.EAST);
    }

    public void setButtonBackground(int opacity) {
        closeButton.setButtonOpacity(opacity);
        closeButton.setBackground(Color.RED);        
    }

    private enum ButtonName {
        CLOSE,
    }
    
    private class ClockButton extends JButton implements MouseListener {
        private Color backColor;
        private ButtonName button;
        private int opacity = OPACITY_30_PERCENT;

        private ClockButton(ButtonName button, Color backColor) {
            super();
            this.button = button;
            this.backColor = backColor;
            setPreferredSize(new Dimension(45, 29));
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
            setBorder(new EmptyBorder(0, 0, 0, 0));
            setFocusPainted(false);
            addMouseListener(this);
        }

        public void setButtonOpacity(int opacity) {
            this.opacity = opacity;
        }

        @Override
        public void paintComponent(Graphics g) {
            int width = getWidth();
            int height = getHeight();
      
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(backColor.getRed(), backColor.getGreen(), backColor.getBlue(), opacity));
            g2d.fillRect(0, 0, width, height);
      
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(new Color(255, 255, 255));
      
            Point markStPt = new Point(width / 2 - 5, height / 2 - 5);
            Point markEnPt = new Point(width / 2 + 5, height / 2 + 5);
      
            switch (button){
            case CLOSE:
                g2d.drawLine(markStPt.x, markStPt.y, markEnPt.x, markEnPt.y);
                markStPt = new Point(width / 2 + 5,height / 2 - 5);
                markEnPt = new Point(width / 2 - 5,height / 2 + 5);
                g2d.drawLine(markStPt.x, markStPt.y, markEnPt.x, markEnPt.y);
                break;
            }
            g2d.dispose();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
     
        @Override
        public void mousePressed(MouseEvent e) {
            opacity = OPACITY_30_PERCENT;
            repaint();
        }
     
        @Override
        public void mouseReleased(MouseEvent e) {
            opacity = OPACITY_100_PERCENT;
            repaint();
        }
     
        @Override
        public void mouseEntered(MouseEvent e) {
            opacity = OPACITY_100_PERCENT;
            repaint();
        }
     
        @Override
        public void mouseExited(MouseEvent e) {
            opacity = OPACITY_30_PERCENT;
            repaint();
        }
    }
}
