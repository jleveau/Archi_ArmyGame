package units_states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.GameUnitEntity;
import gameframework.core.SpriteManager;

public class StrikeState extends GameUnitState {
	
	private long last_strike;
	static private long time_between_strike = 1000;
	static private GameUnitState singleton = null;

	public StrikeState(SpriteManager spriteManager) {
		super(spriteManager);
		last_strike = 0;
	}
	
	@Override
	public boolean canStrike() {
		long time = System.currentTimeMillis();
		if (last_strike == 0){
			last_strike = time;
			return true;
		}
		else{
			if (last_strike+time_between_strike < time){
				last_strike = time;
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
		spriteManager.setType(spriteType);
		spriteManager.draw(g, unit.getPosition());
	}
	
	public static GameUnitState getInstance(SpriteManager spriteManager) {
		if (singleton != null) 
			return singleton;
		singleton =  new StrikeState(spriteManager);
		return singleton;
	}
}
