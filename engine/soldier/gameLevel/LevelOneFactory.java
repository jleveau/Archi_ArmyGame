package soldier.gameLevel;
/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Université Bordeaux.
 */

import soldier.core.AgeAbstractFactory;
import soldier.core.Unit;
import soldier.core.Weapon;
import soldier.units.UnitHorseMan;
import soldier.units.UnitSwordMan;
import soldier.weapon.WeaponShield;
import soldier.weapon.WeaponSword;

public class LevelOneFactory implements AgeAbstractFactory {

	@Override
	public Unit infantryUnit(String name) {
		return new UnitSwordMan(name);
	}

	@Override
	public Unit riderUnit(String name) {
		return new UnitHorseMan(name);
	}

	@Override
	public Weapon attackWeapon() {
		return new WeaponSword();
	}

	@Override
	public Weapon defenseWeapon() {
		return new WeaponShield();
	}
}
