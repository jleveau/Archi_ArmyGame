package entity_space;

import java.awt.Canvas;

import entity_middle_age.PlayerSwordMan;
import gameframework.core.SpriteManagerDefaultImpl;
import soldier.core.Unit;

public class SpaceShipPlayer extends PlayerSwordMan {
	
	private static int RENDERING_SIZE=16;

	public SpaceShipPlayer(Canvas defaultCanvas, Unit unit) {
		super(defaultCanvas, unit);
	}

	@Override
	public void createSpriteManager() {
		spriteManager = new SpriteManagerDefaultImpl("images/space/ship.gif", canvas, RENDERING_SIZE, 6);
		spriteManager.setTypes(
				//
				"left", "right", "up", "down", //
				"beginAfraid-left", "beginAfraid-right", "beginAfraid-up", "beginAfraid-down", //
				"hasWeapon-left", "hasWeapon-right", "hasWeapon-up", "hasWeapon-down", //
				"inactive-left", "inactive-right", "inactive-up", "inactive-down", //
				"unused");
	}
}
