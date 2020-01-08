package interfaces;

import java.awt.Graphics2D;

import main.MainPanel;

public class AnimatedObject implements Animatable {
	public double time = 0;

	public AnimatedObject() {
		MainPanel.renderables.add(this);
		MainPanel.updatables.add(this);
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		time += 0.02;
		// TODO Auto-generated method stub

	}

}
