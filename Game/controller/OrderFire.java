package controller;

import java.awt.Point;

import entity_middle_age.GameUnit;
import entity_middle_age.GameUnitEntity;

public class OrderFire extends Order{
	
	public OrderFire(){
		
	}

	public OrderFire(Point position, GameUnit unit) {
		super(position, unit);
	}

	public void execute(GameUnitEntity unit){
		if (unit.hasWeapon()){
			Point target = new Point(0,0);
			unit.throwProjectile(target);
		}
	}
}
