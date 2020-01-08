package controller;

public class StraightLine extends Command {

	double startingAngle = MotorController.r.getAngle();
	double targetX;
	double targetDis;

	public StraightLine(double targetDistance, double timeout) {
		targetDis = targetDistance;
		targetX = MotorController.r.position.x + targetDis;
		setTimeout(timeout);
	}

	@Override
	void initialize() {
	}

	PIDControl angle = new PIDControl(5, 0.1, 0);
	PIDControl distance = new PIDControl(1, 0.15, 0);

	@Override
	void execute() {
		double errorAngle = 2 * (MotorController.r.getAngle() - startingAngle);
		double errorX = -(MotorController.r.position.x - targetX) / targetDis;
		double leftSpeed = 0, rightSpeed = 0;
		if (Math.abs(errorAngle) > 1E-3) {
			double PIDangle = angle.output(errorAngle);
			System.out.println("angle: " + PIDangle);
			leftSpeed -= PIDangle;
			rightSpeed += PIDangle;
		}
		if (Math.abs(errorX) > 1E-3) {
			double PIDdistance = distance.output(errorX);
			System.out.println("distance: " + PIDdistance);
			leftSpeed += PIDdistance;
			rightSpeed += PIDdistance;
		}
		System.out.println("phi: " + MotorController.r.getAngle() + " position: " + MotorController.r.position);
		System.out.println("left: " + leftSpeed);
		System.out.println("right: " + rightSpeed);

		MotorController.r.getLeft().setSpeed(leftSpeed);
		MotorController.r.getRight().setSpeed(rightSpeed);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public void finish() {
		MotorController.r.getLeft().setSpeed(0);
		MotorController.r.getRight().setSpeed(0);
	}

	@Override
	public void setTimeout(double time) {
		super.setTimeout(time);
	}

}
