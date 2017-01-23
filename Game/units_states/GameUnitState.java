package units_states;

import java.awt.Graphics;

import entity.GameUnitEntity;
import gameframework.core.SpriteManager;

public abstract class GameUnitState {
	
	public static final int RENDERING_SIZE = 16;
	

	public abstract void draw(Graphics g, GameUnitEntity unit);

	public boolean canStrike(GameUnitEntity unit) {
		return false;
	}
	
}
