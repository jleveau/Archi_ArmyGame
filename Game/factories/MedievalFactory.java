package factories;

import java.awt.Canvas;

import Weapons.GameThrowableWeapon;
import Weapons.Missile;
import entity_middle_age.GameSwordMan;
import entity_middle_age.GameUnit;
import entity_middle_age.PlayerSwordMan;
import soldier.ages.AgeMiddleFactory;

public class MedievalFactory extends GameAbstractFactory{

	private AgeMiddleFactory f;
	
	public MedievalFactory(Canvas c){
		super(c);
		f = new AgeMiddleFactory();
	}
	
	public GameSwordMan infantryUnit(Canvas canvas, String name) {
		return new GameSwordMan(canvas, f.infantryUnit(name));
	}

	public GameSwordMan playerInfantryUnit(Canvas canvas, String name) {
		return new PlayerSwordMan(canvas, f.infantryUnit(name));
	}

	@Override
	public GameThrowableWeapon throwable_weapon(Canvas canvas) {
		return new Missile(canvas);
	}

}
