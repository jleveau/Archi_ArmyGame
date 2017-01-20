package entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import exceptions.IllegalAddException;
import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.GameMovableDriver;
import gameframework.core.Overlappable;
import gameframework.moves_rules.MoveBlocker;
import gameframework.moves_rules.SpeedVector;
import observer_util.Observer;
import soldier.core.Unit;
import soldier.core.UnitVisitor;
import soldier.core.Weapon;

public abstract class GameUnit extends GameMovable implements GameUnitItf{
	public static final int RENDERING_SIZE = 16;

	private Unit unit_behavior;
	protected Canvas canvas;
	private int team;


	public GameUnit(Unit unit) {
		super();
		this.unit_behavior = unit;
	}

	@Override
	public String getName() {
		return unit_behavior.getName();
	}

	@Override
	public float getHealthPoints() {
		return unit_behavior.getHealthPoints();
	}

	@Override
	public boolean alive() {
		return unit_behavior.alive();
	}

	@Override
	public void heal() {
		unit_behavior.heal();
	}

	@Override
	public float parry(float force) {
		float health = unit_behavior.parry(force);
		if (!alive())
			notifyObservers(this);
		return health;
	}
	
	public boolean canStrike(){
		return false;
	}
	
	public void attack(GameUnit enemy){
		if (canStrike()){
			enemy.parry(strike());
		}
	}

	@Override
	public float strike() {
		return unit_behavior.strike();
	}

	@Override
	public void addEquipment(Weapon w) {
		unit_behavior.addEquipment(w);
	}

	@Override
	public void removeEquipment(Weapon w) {
		unit_behavior.removeEquipment(w);
	}

	@Override
	public Iterator<Weapon> getWeapons() {
		return unit_behavior.getWeapons();
	}

	@Override
	public void accept(UnitVisitor v) {
		unit_behavior.accept(v);
	}

	@Override
	public void addObserver(Observer<Unit> ob) {
		unit_behavior.addObserver(ob);
	}

	@Override
	public void removeObserver(Observer<Unit> ob) {
		unit_behavior.removeObserver(ob);
	}

	@Override
	public void notifyObservers(Unit s) {
		unit_behavior.notifyObservers(s);
	}
	
	@Override
	public void addUnit(Unit au) {
		unit_behavior.addUnit(au);
	}

	@Override
	public void removeUnit(Unit au) {
		unit_behavior.removeUnit(au);
	}
	
	@Override
	public Iterator<Unit> subUnits() {
		return unit_behavior.subUnits();
	}
	
	@Override
	public int getTeam() {
		
		return team;
	}

	@Override
	public void setTeam(int team) {
		this.team = team;
		
	}

}
