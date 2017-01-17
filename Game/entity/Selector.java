package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;

public class Selector implements GameEntity, Drawable{
	Rectangle rect;
	
	public void setRect(Rectangle r){
		this.rect = r;
	}

	public Rectangle getRect() {
		return rect;
	}

	@Override
	public void draw(Graphics g) {
		if (rect != null)
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
}
