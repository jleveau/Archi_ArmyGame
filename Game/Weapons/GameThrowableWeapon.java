package Weapons;

import java.awt.Canvas;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity_middle_age.GameUnit;
import entity_middle_age.TeamUnit;
import gameframework.core.GameEntity;
import gameframework.core.Movable;
import gameframework.moves_rules.MoveBlockerChecker;
import observer_util.Observable;
import observers.EntityCreatorObserver;
import observers.GameObserver;
import soldier.core.WeaponAttack;

public abstract class GameThrowableWeapon extends WeaponAttack implements GameWeapon, Movable, TeamUnit{
	private static final int BASE_DAMAGE = 5;
	protected Canvas canvas;
	protected Point position;
	protected MoveBlockerChecker move_checker;
	private List<EntityCreatorObserver> observers;
	protected int team;

	public abstract GameWeapon throwToTarget(GameUnit launcher);


	public GameThrowableWeapon(Canvas c){
		canvas = c;
		observers = new ArrayList<EntityCreatorObserver>();
	}
	
	public void setPosition(Point position2) {
		this.position = position2;
	}
	
	public void setMove_checker(MoveBlockerChecker move_checker) {
		this.move_checker = move_checker;
	}
	
	public void notify_creation(GameEntity game_entity){
		List<EntityCreatorObserver> list_obs = new ArrayList<EntityCreatorObserver>();
		for (EntityCreatorObserver obs : observers){
			list_obs.add(obs);
		}
		for (EntityCreatorObserver obs: list_obs){
			obs.update_creation(game_entity);
		}
	}
	
	public void notify_destruction(GameEntity game_entity) {
		List<EntityCreatorObserver> list_obs = new ArrayList<EntityCreatorObserver>();
		for (EntityCreatorObserver obs : observers){
			list_obs.add(obs);
		}
		for (EntityCreatorObserver obs: list_obs){
			obs.update_destruction(game_entity);
		}
	}
	
	public void addObserver(EntityCreatorObserver obs){
		this.observers.add(obs);
	}

	
	@Override
	public int getTeam() {
		return team;
	}

	@Override
	public void setTeam(int team) {
		this.team = team;
	}
	
	public int getDamage(){
		return BASE_DAMAGE;
	}
}
