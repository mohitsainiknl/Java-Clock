/*
 * Copyright (c) 2021 Mohit Saini, Under MIT License. Use is subject to license terms.
 * 
 */

import java.time.LocalTime;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

public class MainLauncher extends MouseAdapter implements Runnable {
	private ClockFrame frame;
	private ClockTitleBar titleBar;
	private JPanel mainPanel;
	private ClockPanel clockPanel;
	private int clickLocationX, clickLocationY;

	private Thread timeThread, paintThread;
	private LocalTime time;

	public MainLauncher() {
		initializeFrame();
	}
	public static void main(String[] args) {
		new MainLauncher();
	}
	private void initializeFrame() {
		timeThread = new Thread(this);
		frame = new ClockFrame(ClockFrame.OPACITY_0_PERCENT);
		titleBar = new ClockTitleBar();

		mainPanel = (JPanel) frame.getContentPane();
		initializeClockPanel();

		mainPanel.add(titleBar, BorderLayout.NORTH);
		mainPanel.add(clockPanel, BorderLayout.CENTER);
		
		frame.setContentPane(mainPanel);
		frame.setVisible(true);

		timeThread.start();
	}
	
	private void initializeClockPanel() {
		
		time = LocalTime.now();
		clockPanel = new ClockPanel(time.getHour(), time.getMinute(), time.getSecond());
		clockPanel.addMouseMotionListener(this);
		clockPanel.addMouseListener(this);
	}

	public void run() {
		try {
			while(true) {
				paintThread = new Thread(new RePaint());
				paintThread.start();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class RePaint implements Runnable {
		public void run() {
			time = LocalTime.now();
			clockPanel.setTime(time.getHour(), time.getMinute(), time.getSecond());
			clockPanel.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int X = (int) frame.getLocation().getX();
		int Y = (int) frame.getLocation().getY();
		
		int x = e.getX() - clickLocationX;
		int y = e.getY() - clickLocationY;
		
		frame.setLocation(X + x, Y + y);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		clickLocationX = e.getX();
		clickLocationY = e.getY();
	}
}
