package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;

public class Selector implements GameEntity, Drawable{
	Rectangle rect = null;
	
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
			g.setColor(Color.black);
			//XXX appear that rect can be null at this point, i don't understand whys
			if (rect != null)
				g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
	}
}
