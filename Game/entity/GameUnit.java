package entity;


import java.awt.Point;
import java.awt.Rectangle;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;

public abstract class GameUnit extends GameMovable implements Drawable, GameEntity,
Overlappable, Selectable {
	public static final int RENDERING_SIZE = 16;
	private boolean selected;
	private Point target_position;
	private Rectangle select_box;
	
	GameUnit(){
		super();
		selected = false;
		target_position = new Point(0,0);
		select_box = new Rectangle(0,0,0,0);
	}
	
	public void setTarget(int x, int y){
		this.target_position.x = x;
		this.target_position.y = y;
	}
	
	public Point getTarget_position() {
		return target_position;
	}

	public void setTarget_position(Point target_position) {
		this.target_position = target_position;
	}

	public boolean isSelected() {
		return selected;
	}


	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	public abstract int getSpeed();

	public void setSelected(boolean b) {
		selected = b;
	}

	@Override
	public Rectangle getSelectBox() {
		return new Rectangle(this.getPosition().x, this.getPosition().y,
				this.getBoundingBox().width, this.getBoundingBox().height);
	}
}
