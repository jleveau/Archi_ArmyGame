package Weapons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.Target;
import gameframework.core.GameMovable;

public class Rock extends GameMovable implements GameWeapon, Target {

	private static int RENDERING_SIZE = 6;
	private Point target;
	

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(this.getPosition().x, this.getPosition().y, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		return;
	}

	@Override
	public Point getTarget_position() {
		return target;
	}

	@Override
	public void setTarget_position(Point target_pos) {
		target = target_pos;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}
}
