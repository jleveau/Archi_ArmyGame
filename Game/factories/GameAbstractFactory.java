package factories;

import java.awt.Canvas;

import Weapons.GameThrowableWeapon;
import entity_middle_age.GameUnitEntity;
import entity_middle_age.Regiment;
import soldier.core.UnitGroup;

public abstract class GameAbstractFactory {
	protected Canvas canvas;

	public GameAbstractFactory(Canvas c) {
		this.canvas = c;
	}

	public abstract GameUnitEntity infantryUnit(Canvas canvas, String name);
	public abstract GameUnitEntity playerInfantryUnit(Canvas canvas, String name);
	public abstract GameThrowableWeapon throwable_weapon(Canvas canvas);
	
	public Regiment regiment(String name) {
		return new Regiment(canvas, new UnitGroup(name));
	}
}
