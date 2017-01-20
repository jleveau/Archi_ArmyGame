package units_states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.GameUnitEntity;
import gameframework.core.SpriteManager;

public class NormalState extends GameUnitState {
	static GameUnitState singleton = null;

	public NormalState(SpriteManager spriteManager) {
		super(spriteManager);
	}

	@Override
	public void draw(Graphics g, GameUnitEntity unit) {
		String spriteType = "";
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
		if (unit.isSelected()) {
			Rectangle box = unit.getBoundingBox();
			g.setColor(Color.GREEN);
			g.drawRect(box.x, box.y, box.width, box.height);
		}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, unit.getPosition());
	}

	public static GameUnitState getInstance(SpriteManager spriteManager) {
		if (singleton != null) 
			return singleton;
		return singleton = new NormalState(spriteManager);
	}

}
