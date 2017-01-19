package entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import exceptions.IllegalAddException;
import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;
import gameframework.core.SpriteManagerDefaultImpl;

public abstract class GameUnitEntity extends GameMovable implements Drawable, GameEntity, Overlappable, Selectable{

	protected static DrawableImage image = null;
	protected boolean active = true;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 16;
	public static final int BASE_SPEED = 3;
	protected boolean selected;
	Point target_position;

	public GameUnitEntity(Canvas defaultCanvas) {
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
		if (selected) {
			Rectangle box = getBoundingBox();
			g.setColor(Color.GREEN);
			g.drawRect(box.x, box.y, box.width, box.height);
		}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		spriteManager.increment();
	}

	public int getSpeed() {
		return BASE_SPEED;
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(this.getPosition().x, this.getPosition().y, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public boolean isSelectedBy(Rectangle rect) {
		return getBoundingBox().intersects(rect);
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean b) {
		selected = b;
	}

	public Point getTarget_position() {
		if (target_position == null)
			return getPosition();
		return target_position;
	}

	public void setTarget_position(Point target_position) {
		this.target_position = target_position;
	}

	public void addUnit(GameUnitEntity game_unit) throws IllegalAddException {
		throw new IllegalAddException();
	}

	public void removeUnit(GameUnitEntity game_unit) throws IllegalAddException {
		throw new IllegalAddException();
	}

}
