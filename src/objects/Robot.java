package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import interfaces.AnimatedObject;

public class Robot extends AnimatedObject {
	public static final double width = 55;
	public static final double loopTime = 0.02;
	protected static final Random rand = new Random();

	// unit: cm
	public Coordinates position = new Coordinates(0, 0);
	// unit: raidans
	private double phi = 0;

	// private final Motor left = new Motor(new Encoder(), 0.01, 0.8), right = new
	// Motor(new Encoder(), 0.1, 1);
	private final Motor left = new Motor(new Encoder(), 10, 0.7), right = new Motor(new Encoder(), 10, 1);

	private double gyro;

//	final double graphicScale = 10;

	public Robot() {
		super();
	}

	public void update() {
		double leftSpeed = left.output();
		double rightSpeed = right.output();

		position.add(Coordinates.fromRadial(leftSpeed + rightSpeed, phi).scale(0.5).scale(loopTime));
		phi += (leftSpeed - rightSpeed) * loopTime / width;
		gyro = phi + rand.nextGaussian() * 0.001;
//		System.out.println(phi);
//		System.out.println(position);
	}

	@Override
	public void render(Graphics2D g) {
		int width = (int) (Robot.width), height = (int) (70);
		Rectangle robot = new Rectangle(-height / 2, -width / 2, height, width);

		// do a conjugate on the coords system
		g.translate(position.x, position.y);
		g.rotate(phi);

		// red robot
		g.setColor(new Color(255, 0, 0));
		g.fill(robot);

		// green direction
		g.setColor(new Color(0, 255, 0));
		g.setStroke(new BasicStroke(3));
		g.drawLine(0, 0, height, 0);

		g.rotate(-phi);
		g.translate(-position.x, -position.y);

	}

	public long getLeftEncoder() {
		return left.encoder.position;
	}

	public long getRightEncoder() {
		return right.encoder.position;
	}



	public Motor getLeft() {
		return left;
	}

	public Motor getRight() {
		return right;
	}

	public double getAngle() {
		return gyro;
	}

	public String toString() {
		return left.encoder.position + " " + right.encoder.position + " " + gyro;
	}
}
