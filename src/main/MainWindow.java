package main;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1920 / 2;
	public static final int HEIGHT = 1080 / 2;
	private MainPanel panel = new MainPanel();
	
	public MainWindow() {
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setUndecorated(true);
		setVisible(true);
	}
}