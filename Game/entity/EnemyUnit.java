package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import soldier.core.AgeAbstractFactory;
import soldier.core.Unit;

public abstract class EnemyUnit extends GameUnit{
	
	Unit behavior_unit;
	GameUnit game_unit;

	public EnemyUnit(AgeAbstractFactory factory, GameUnit game_unit) {
		behavior_unit = factory.infantryUnit(" ");
		this.game_unit = game_unit;
	}

	@Override
	public void draw(Graphics g) {
		game_unit.draw(g);
	}

	@Override
	public Rectangle getBoundingBox() {
		return game_unit.getBoundingBox();
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		game_unit.oneStepMoveAddedBehavior();
	}

	
}
