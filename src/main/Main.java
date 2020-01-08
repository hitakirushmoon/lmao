package main;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
//		for (double i = 0; i < 15; i += 0.02) {
//			MotorController.update();
//			MotorController.r.update();
//			System.out.println(MotorController.r);
//		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
	}

}