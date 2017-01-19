package entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import gameframework.core.GameMovableDriver;

public class GameUnitGroup extends GameUnitEntity {

	private Set<GameUnitEntity> units;

	public GameUnitGroup(Canvas canvas) {
		super(canvas);
		units = new HashSet<GameUnitEntity>();
	}

	@Override
	public void draw(Graphics g) {
		for (GameUnitEntity game_unit : units) {
			game_unit.draw(g);
		}
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		for (GameUnitEntity game_unit : units) {
			game_unit.oneStepMoveAddedBehavior();
		}
	}

	@Override
	public boolean isSelectedBy(Rectangle rect) {
		for (GameUnitEntity game_unit : units) {
			if (game_unit.isSelectedBy(rect))
				return true;
		}
		return false;
	}
	
	//May be changed if we want better coordination
	@Override
	public void setTarget_position(Point target_position) {
		for (GameUnitEntity game_unit : units) {
			game_unit.setTarget_position(target_position);
		}
	}

	@Override
	public int getSpeed() {
		int speed = Integer.MAX_VALUE;
		for (GameUnitEntity game_unit : units) {
			if (game_unit.getSpeed() < speed)
				speed = game_unit.getSpeed();
		}
		return speed;
	}


	@Override
	public void setSelected(boolean b) {
		for (GameUnitEntity game_unit : units) {
			game_unit.setSelected(b);
		}
	}

	public void addUnit(GameUnitEntity g) {
		this.units.add(g);
	}

	public void removeUnit(GameUnitEntity g) {
		this.units.remove(g);
	}

	public Iterator<GameUnitEntity> getSubUnits() {
		return this.units.iterator();
	}

}
