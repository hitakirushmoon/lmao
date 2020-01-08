package controller;

import interfaces.Updatable;
import main.MainPanel;

public abstract class Command implements Updatable {

	private double timeout = -1;
	private double time = 0;

	void initialize() {

	}

	@Override
	public final void update() {
		if (isFinished() || (time > timeout && timeout > 0)) {
			end();
			return;
		}
		time += 0.02;
		execute();
	}

	void execute() {

	}

	public final void start() {
		MainPanel.updatables.add(this);
		initialize();
	}

	private void end() {
		MainPanel.updatables.remove(this);
		finish();
	}

	public void finish() {

	}

	public boolean isFinished() {
		return false;
	}

	public void setTimeout(double time) {
		if (time < 0) {
			throw new IllegalArgumentException();
		}
		timeout = time;
	}
}
