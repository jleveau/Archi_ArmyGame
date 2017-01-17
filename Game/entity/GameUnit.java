package entity;


import java.awt.Rectangle;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;

public abstract class GameUnit extends GameMovable implements Drawable, GameEntity,
Overlappable {
	public static final int RENDERING_SIZE = 16;

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	public abstract int getSpeed();
}
