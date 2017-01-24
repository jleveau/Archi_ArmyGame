package Weapons;

import java.awt.Rectangle;

import entity.GameUnit;
import soldier.core.WeaponAttack;

public abstract class GameThrowableWeapon extends WeaponAttack implements GameWeapon {
	
	public abstract Rock throwToTarget(GameUnit launcher, GameUnit target);
	

}
