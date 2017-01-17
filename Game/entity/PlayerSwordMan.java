package entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.core.DrawableImage;
import gameframework.core.SpriteManagerDefaultImpl;

public class PlayerSwordMan extends GameUnit {

	protected static DrawableImage image = null;
	protected boolean active = true;
	private final SpriteManagerDefaultImpl spriteManager;

	public PlayerSwordMan(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/ghost.gif", defaultCanvas, RENDERING_SIZE, 6);
		spriteManager.setTypes(
				//
				"left", "right", "up", "down", //
				"beginAfraid-left", "beginAfraid-right", "beginAfraid-up", "beginAfraid-down", //
				"endAfraid-left", "endAfraid-right", "endAfraid-up", "endAfraid-down", //
				"inactive-left", "inactive-right", "inactive-up", "inactive-down", //
				"unused");
	}

	public boolean isActive() {
		return active;
	}

	public void setAlive(boolean aliveState) {
		active = aliveState;
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();

		if (!isActive()) {
			spriteType = "inactive-";
		}

		if (tmp.getX() == -1) {
			spriteType += "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
		} else {
			spriteType += "right";
		}

		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		spriteManager.increment();
	}

	@Override
	public int getSpeed() {
		return 3;
	}



}
