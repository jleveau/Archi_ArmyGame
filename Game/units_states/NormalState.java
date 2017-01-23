package units_states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.GameUnitEntity;
import gameframework.core.SpriteManager;

public class NormalState extends GameUnitState {
	static GameUnitState singleton = null;

	@Override
	public void draw(Graphics g, GameUnitEntity unit) {
		String spriteType = "beginAfraid-";
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
