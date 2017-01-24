package units_states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import Weapons.GameWeapon;
import entity.GameUnitEntity;
import gameframework.core.SpriteManager;

public class StrikeState extends GameUnitState {

	static private GameUnitState singleton = null;

	@Override
	public boolean canStrike(GameUnitEntity unit) {
		long time = System.currentTimeMillis();
		if (unit.getLast_strike() == 0) {
			unit.setLast_strike(time);
			return true;
		} else {
			if (unit.getLast_strike() + unit.getTime_between_strike() < time) {
				unit.setLast_strike(time);
				return true;
			}
		}
		return false;
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
		unit.getSpriteManager().setType(spriteType);
		unit.getSpriteManager().draw(g, unit.getPosition());
		
		java.util.Iterator<GameWeapon> it = unit.getDrawableWeapons();
		while(it.hasNext()){
			it.next().draw(g);
		}
	}

	public static GameUnitState getInstance() {
		if (singleton != null)
			return singleton;
		singleton = new StrikeState();
		return singleton;
	}
}
