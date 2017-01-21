package entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.IllegalAddException;
import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.GameMovableDriver;
import gameframework.core.Overlappable;
import gameframework.moves_rules.MoveBlocker;
import gameframework.moves_rules.SpeedVector;
import observer_util.Observer;
import observers.GameObserver;
import soldier.core.Unit;
import soldier.core.UnitVisitor;
import soldier.core.Weapon;

public abstract class GameUnit extends GameMovable implements GameUnitItf{
	
	List<GameObserver<GameUnit>> game_obs;
	public static final int RENDERING_SIZE = 16;

	private Unit unit_behavior;
	protected Canvas canvas;
	private int team;
	
	public static final int BASE_SPEED = 3;


	public GameUnit(Unit unit) {
		super();
		this.unit_behavior = unit;
		game_obs = new LinkedList<GameObserver<GameUnit>>();
	}
	
	public int getSpeed() {
		return BASE_SPEED;
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
			notify_death();
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
	public float getMaxHealthPoints() {
		return unit_behavior.getMaxHealthPoints();
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
	
	@Override
	public void remove_game_obs(GameObserver<GameUnit> obs) {
		game_obs.remove(obs);
	}

	@Override
	public void add_game_obs(GameObserver<GameUnit> obs) {
		game_obs.add(obs);
	}

	@Override
	public void notify_death() {
		for (GameObserver<GameUnit> obs : game_obs){
			obs.update_death(this);
		}
		notifyObservers(this);
	}

	@Override
	public void notify_target_reached() {
		for (GameObserver<GameUnit> obs : game_obs){
			obs.update_target_reach(this);
		}
	}

}
