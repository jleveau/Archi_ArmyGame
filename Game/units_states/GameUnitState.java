package units_states;

import java.awt.Graphics;

import entity.GameUnitEntity;
import gameframework.core.SpriteManager;

public abstract class GameUnitState {
	
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 16;
	
	protected GameUnitState(SpriteManager spriteManager2){
		spriteManager = spriteManager2;
	}
	
	public abstract void draw(Graphics g, GameUnitEntity unit);

	public void increment() {
		spriteManager.increment();
	}

	public boolean canStrike() {
		return false;
	}
	
}
