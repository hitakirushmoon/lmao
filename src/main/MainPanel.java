package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.MotorController;
import interfaces.Renderable;
import interfaces.Updatable;

public class MainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1267434478501521763L;
	public static List<Updatable> updatables = new LinkedList<Updatable>();
	public static List<Renderable> renderables = new LinkedList<Renderable>();
	public MotorController lmao = new MotorController();

	public List<Updatable> getUpdatables() {
		return updatables;
	}

	public List<Renderable> getRenderables() {
		return renderables;
	}
	double frame = System.nanoTime();
	Queue<Double> frames = new LinkedBlockingDeque<Double>(100);
//	public double scale = 1, x = MainWindow.WIDTH / 2, y = MainWindow.HEIGHT / 2;
	private Timer timer = new Timer(10, e -> {
		update();
		repaint();
	});

	public MainPanel() {
		timer.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(MainWindow.WIDTH / 2, MainWindow.HEIGHT / 2);
		g2.setColor(Color.BLACK);
		g2.fillRect(-MainWindow.WIDTH, -MainWindow.HEIGHT, MainWindow.WIDTH * 2, MainWindow.HEIGHT * 2);
		g2.setColor(Color.WHITE);
		for (Renderable renderable : renderables) {
			renderable.render(g2);
		}
		g2.translate(-MainWindow.WIDTH / 2, -MainWindow.HEIGHT / 2);
		double lmao = System.nanoTime() - frame;
		if (!frames.offer(lmao)) {
			frames.poll();
			frames.add(lmao);
		}
		var wrapper = new Object() {
			double average = 0;
		};

		frames.forEach(x -> wrapper.average += x / frames.size());
		g2.drawString("fps: " + (1000000000 / wrapper.average), 100, 200);
		g2.drawString("time: " + this.lmao.time, 100, 250);
	
		frame = System.nanoTime();
	}

	public void update() {
		for (Updatable updatable : updatables) {
			updatable.update();
		}
	}
}