package entity_middle_age;

import java.awt.Canvas;

import gameframework.core.SpriteManagerDefaultImpl;
import soldier.core.Unit;

public class PlayerSwordMan extends GameSwordMan{

	public PlayerSwordMan(Canvas defaultCanvas, Unit unit) {
		super(defaultCanvas, unit);
	}

	@Override
	public void createSpriteManager() {
		spriteManager = new SpriteManagerDefaultImpl("images/player.gif", canvas, RENDERING_SIZE, 6);
		spriteManager.setTypes(
				//
				"left", "right", "up", "down", //
				"beginAfraid-left", "beginAfraid-right", "beginAfraid-up", "beginAfraid-down", //
				"hasWeapon-left", "hasWeapon-right", "hasWeapon-up", "hasWeapon-down", //
				"inactive-left", "inactive-right", "inactive-up", "inactive-down", //
				"unused");
	}
}
