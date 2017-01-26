package units_states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.text.html.HTMLDocument.Iterator;

import Weapons.GameWeapon;
import entity_middle_age.GameUnitEntity;
import gameframework.core.SpriteManager;

public class NormalState extends GameUnitState {
	static GameUnitState singleton = null;

	@Override
	public void draw(Graphics g, GameUnitEntity unit) {
		String spriteType;
		if (unit.hasWeapon())
			spriteType = "hasWeapon-";
		else
			spriteType = "beginAfraid-";
		
		Point tmp = unit.getSpeedVector().getDirection();

		if (tmp.getX() == -1) {
			spriteType += "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
		} else {
			spriteType += "right";
		}
		unit.getSpriteManager().setType(spriteType);
		unit.getSpriteManager().draw(g, unit.getPosition());
		
		if (unit.isSelected()) {
			Rectangle box = unit.getBoundingBox();
			g.setColor(Color.GREEN);
			g.drawRect(box.x, box.y, box.width, box.height);
		}
	}

	public static GameUnitState getInstance() {
		if (singleton != null) 
			return singleton;
		return singleton = new NormalState();
	}

}
