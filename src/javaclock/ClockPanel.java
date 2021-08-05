/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

import java.io.IOException;
import java.io.File;

import java.lang.Math;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Image;
import java.awt.BasicStroke;

import javax.swing.JPanel;

import javax.imageio.ImageIO;

public class ClockPanel extends JPanel {
    private int hour, minute, second;
    private Image backClockImage, backHandImage;
    private boolean firstTime = true;

    public ClockPanel(int hour, int minute, int second) {
        super();
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        loadImages();
    }
    private void loadImages() {
        try {
            backClockImage = ImageIO.read(new File("res//Analog-Clock-Background-New.png"));
            backHandImage = ImageIO.read(new File("res//Clock-Hand-Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        
        int originX = (width / 2) - 1;
        int originY = (height / 2) + 7;

        int hrDegree;
        int hrHandLength;
        int hrPositionX, hrPositionY;

        int minDegree;
        int minHandLength;
        int minPositionX, minPositionY;

        int secDegree;
        int secHandLength;
        int secBacktipLength;
        int secPositionX, secPositionY;
        int secBacktipX, secBacktipY;


        //----------------- for Hour Hand -------------------
        
        hrDegree =  360*2 - (int)  ((     (hour-3) * 60 + minute     ) * 0.5);
        if(hrDegree > 360)
            hrDegree = hrDegree - 360;
        hrHandLength = 51;
        
        hrPositionX = originX + (int) (hrHandLength * Math.cos(Math.toRadians(hrDegree)));
        hrPositionY = originY - (int) (hrHandLength * Math.sin(Math.toRadians(hrDegree)));


        //----------------- for Minute Hand -------------------
        
        minDegree = 360 - (int)  ((     (minute-15) * 60 + second     ) * 0.1);
        if(minDegree > 360)
            minDegree = minDegree - 360;
        minHandLength = 72;
        
        minPositionX = originX + (int) (minHandLength * Math.cos(Math.toRadians(minDegree)));
        minPositionY = originY - (int) (minHandLength * Math.sin(Math.toRadians(minDegree)));


        //----------------- for Second_Hand -------------------
        
        secDegree = ((60 - second) + 15) * 6;
        if(secDegree > 360)
            secDegree = secDegree - 360;

        secHandLength = 82;
        secBacktipLength = 22;
        
        secPositionX = originX + (int) (secHandLength * Math.cos(Math.toRadians(secDegree)));
        secPositionY = originY - (int) (secHandLength * Math.sin(Math.toRadians(secDegree)));
        
        secBacktipX = originX + (int) (secBacktipLength * Math.cos(Math.toRadians(secDegree + 180)));
        secBacktipY = originY - (int) (secBacktipLength * Math.sin(Math.toRadians(secDegree + 180)));
        
        //====================== Arrow Head Decoration ===============================

        //---------------------- For Hour Hand --------------------------------

        int hrArrowTipX, hrArrowTipY;
        int hrArrowMidX, hrArrowMidY;
        int hrArrowStartDegree, hrArrowEndDegree;
        int hrArrowArcRadius, hrArrowCircleRadius;

        hrArrowArcRadius = 20;
        hrArrowCircleRadius = (int) (20 * Math.sin(Math.toRadians(30)) + 1);
        hrArrowStartDegree = hrDegree + 150;
        hrArrowEndDegree = 60;

        if(hrArrowStartDegree > 360)
        hrArrowStartDegree = hrArrowStartDegree - 360;
        if(hrArrowEndDegree > 360)
        hrArrowEndDegree = hrArrowEndDegree - 360;
        
        hrHandLength = hrHandLength + 10;
        hrArrowTipX = originX + (int) (hrHandLength * Math.cos(Math.toRadians(hrDegree)));
        hrArrowTipY = originY - (int) (hrHandLength * Math.sin(Math.toRadians(hrDegree)));
        
        hrHandLength = hrHandLength - (int) (20 * Math.cos(Math.toRadians(30)));
        hrArrowMidX = originX + (int) (hrHandLength * Math.cos(Math.toRadians(hrDegree)));
        hrArrowMidY = originY - (int) (hrHandLength * Math.sin(Math.toRadians(hrDegree)));


        //---------------------- For Minute Hand --------------------------------

        int minArrowTipX, minArrowTipY;
        int minArrowMidX, minArrowMidY;
        int minArrowStartDegree, minArrowEndDegree;
        int minArrowArcRadius, minArrowCircleRadius;

        minArrowArcRadius = 20;
        minArrowCircleRadius = (int) (20 * Math.sin(Math.toRadians(30)) + 1);
        minArrowStartDegree = minDegree + 150;
        minArrowEndDegree = 60;

        if(minArrowStartDegree > 360)
        minArrowStartDegree = minArrowStartDegree - 360;
        if(minArrowEndDegree > 360)
        minArrowEndDegree = minArrowEndDegree - 360;
        
        minHandLength = minHandLength + 10;
        minArrowTipX = originX + (int) (minHandLength * Math.cos(Math.toRadians(minDegree)));
        minArrowTipY = originY - (int) (minHandLength * Math.sin(Math.toRadians(minDegree)));
        
        minHandLength = minHandLength - (int) (20 * Math.cos(Math.toRadians(30)));
        minArrowMidX = originX + (int) (minHandLength * Math.cos(Math.toRadians(minDegree)));
        minArrowMidY = originY - (int) (minHandLength * Math.sin(Math.toRadians(minDegree)));
        
        

        if(firstTime) {
            g2d.drawImage(backClockImage, 0, 0, width, height, this);
            firstTime = false;
        } else {
            g2d.drawImage(backHandImage, 0+21, 0+50, 256, 255, this);
        }
        
        g2d.setPaint(new GradientPaint( 90, 90, new Color(80, 80, 80), 240, 240, new Color(0, 0, 0), true ));
        g2d.setStroke(new BasicStroke(7));
        g2d.drawLine(originX, originY, hrPositionX, hrPositionY);
        g2d.fillArc(hrArrowTipX - hrArrowArcRadius,    hrArrowTipY - hrArrowArcRadius,    hrArrowArcRadius*2,    hrArrowArcRadius*2,    hrArrowStartDegree,    60);
        g2d.fillArc(hrArrowMidX - hrArrowCircleRadius,    hrArrowMidY - hrArrowCircleRadius,    hrArrowCircleRadius*2,    hrArrowCircleRadius*2,    hrArrowStartDegree - 60,    180);

        g2d.setPaint(new GradientPaint( 0, 0, new Color(85, 85, 85), width, height, new Color(10, 10, 10), true ));
        g2d.setStroke(new BasicStroke(7));
        g2d.drawLine(originX, originY, minPositionX, minPositionY);
        g2d.fillArc(minArrowTipX - minArrowArcRadius,    minArrowTipY - minArrowArcRadius,    minArrowArcRadius*2,    minArrowArcRadius*2,    minArrowStartDegree,    60);
        g2d.fillArc(minArrowMidX - minArrowCircleRadius,    minArrowMidY - minArrowCircleRadius,    minArrowCircleRadius*2,    minArrowCircleRadius*2,    minArrowStartDegree - 60,    180);

        g2d.fillOval(originX - 12, originY - 12, 24, 24);

        g2d.setPaint(new GradientPaint( 0, 0, new Color(100, 100, 100), width, height, new Color(0, 0, 0), true ));
        g2d.setPaint(new Color(229, 56, 36));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(secBacktipX, secBacktipY, secPositionX, secPositionY);
        g2d.fillOval(originX - 10, originY - 10, 20, 20);
        

        System.out.println("Degree = " + " "+ hrDegree + " "+ minDegree + " " + secDegree );
       
    }
}
