package entity_middle_age;

import java.awt.Canvas;

import gameframework.core.DrawableImage;
import gameframework.core.SpriteManagerDefaultImpl;
import gameframework.moves_rules.MoveBlocker;
import soldier.core.Unit;

public class GameSwordMan extends GameUnitEntity implements MoveBlocker{

	protected static DrawableImage image = null;
	protected boolean active = true;


	public GameSwordMan(Canvas defaultCanvas, Unit unit) {
		super(defaultCanvas, unit);
	}

	public int getSpeed() {
		return 5;
	}

	@Override
	public void createSpriteManager() {
		spriteManager = new SpriteManagerDefaultImpl("images/enemy.png", canvas, RENDERING_SIZE, 6);
		spriteManager.setTypes(
				//
				"left", "right", "up", "down", //
				"beginAfraid-left", "beginAfraid-right", "beginAfraid-up", "beginAfraid-down", //
				"hasWeapon-left", "hasWeapon-right", "hasWeapon-up", "hasWeapon-down", //
				"inactive-left", "inactive-right", "inactive-up", "inactive-down", //
				"unused");
	}
}
