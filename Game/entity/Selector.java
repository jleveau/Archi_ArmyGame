package entity;

import java.awt.Color;
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
		if (rect != null){
			Color select_color = new Color(0,255,0,100);
			g.setColor(select_color);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.setColor(Color.BLACK);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
	}
}
