package objects;

public class Motor {
	// unit : -1.0 to 1.0 of unknown measure
	private double speed = 0;
	
	// unit : cm/s
	private double lastOutput = 0;

	// unit : cm/s
	public static final double maxSpeed = 300;
	public static final double maxAcc = 15;
	public static final double drift = 5;
	
	public final double variability;
	public final double bias;
	
	public final Encoder encoder;

	public Motor(Encoder encoder, double variability, double bias) {
		this.variability = variability;
		this.bias = bias;
		this.encoder = encoder;
	}

	Motor(Encoder encoder) {
		this(encoder, 0.01, 1);
	}

	public double output() {
		lastOutput = MathUtils.limitChange(speed * maxSpeed * bias + Robot.rand.nextGaussian() * variability * speed,
				lastOutput, maxAcc, drift);

//		lastOutput = lastOutput > 0.3 ? lastOutput : 0;
		encoder.position += lastOutput * Robot.loopTime * 4096 / maxSpeed;
		return lastOutput;
	}

	// unit : -1.0 to 1.0 of unknown measure
	public void setSpeed(double speed) {
		this.speed = MathUtils.limit(speed, -1, 1);
	}

}
