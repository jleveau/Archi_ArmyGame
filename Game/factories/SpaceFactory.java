package factories;

import java.awt.Canvas;

import Weapons.GameThrowableWeapon;
import Weapons.Missile;
import entity_middle_age.GameUnitEntity;
import entity_space.SpaceShipEnemy;
import entity_space.SpaceShipPlayer;
import soldier.ages.AgeMiddleFactory;

public class SpaceFactory extends GameAbstractFactory{
	
	private AgeMiddleFactory f;

	public SpaceFactory(Canvas c){
		super(c);
		f = new AgeMiddleFactory();
	}

	public GameUnitEntity infantryUnit(Canvas canvas, String name) {
		return new SpaceShipEnemy(canvas, f.infantryUnit(name));
	}

	public GameUnitEntity playerInfantryUnit(Canvas canvas, String name) {
		return new SpaceShipPlayer(canvas, f.infantryUnit(name));
	}

	@Override
	public GameThrowableWeapon throwable_weapon(Canvas canvas) {
		return new Missile(canvas);
	}

}
