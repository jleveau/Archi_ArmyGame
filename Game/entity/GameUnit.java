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

public class GameUnit extends GameMovable
		implements Drawable, GameEntity, MoveBlocker,Overlappable, Selectable, Unit {
	public static final int RENDERING_SIZE = 16;


	public void setGame_unit(GameUnitEntity game_unit) {
		this.game_unit = game_unit;
	}

	private Unit unit_behavior;
	private GameUnitEntity game_unit;
	protected Canvas canvas;


	public GameUnit(GameUnitEntity game_unit, Unit unit) {
		super();
		this.unit_behavior = unit;
		this.game_unit = game_unit;
	}

	public void addUnit(GameUnit g) {
		this.unit_behavior.addUnit(g.unit_behavior);
		try {
			this.game_unit.addUnit(g.game_unit);
		}catch(IllegalAddException e){}
	}

	public void removeUnit(GameUnit g) {
		this.unit_behavior.removeUnit(g.unit_behavior);
		try {
		this.game_unit.removeUnit(g.game_unit);
		}catch(IllegalAddException e){}
	}
	
	public int getSpeed() {
		return this.game_unit.getSpeed();
	}

	public void oneStepMoveAddedBehavior() {
		this.game_unit.oneStepMoveAddedBehavior();
		
	}
	
	public GameUnitEntity getGame_unit() {
		return game_unit;
	}

	public boolean isSelected() {
		return game_unit.isSelected();
	}

	public Rectangle getBoundingBox() {
		return game_unit.getBoundingBox();
	}

	public void setSelected(boolean b) {
		game_unit.setSelected(b);
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
		return unit_behavior.parry(force);
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

	public void setTarget_position(Point direction) {
		this.game_unit.setTarget_position(direction);
	}

	public Point getTarget_position() {
		return this.game_unit.getTarget_position();
	}

	@Override
	public boolean isSelectedBy(Rectangle rect) {
		return game_unit.isSelectedBy(rect);
	}

	@Override
	public Point getPosition() {
		return game_unit.getPosition();
	}

	@Override
	public void draw(Graphics g) {
		this.game_unit.draw(g);
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
	public void setPosition(Point p) {
		game_unit.setPosition(p);
	}

	@Override
	public void setSpeedVector(SpeedVector speedVector) {
		game_unit.setSpeedVector(speedVector);
	}

	@Override
	public SpeedVector getSpeedVector() {
		return game_unit.getSpeedVector();
	}

	@Override
	public void setDriver(GameMovableDriver driver) {
		game_unit.setDriver(driver);
	}

	@Override
	public GameMovableDriver getDriver() {
		return game_unit.getDriver();
	}

	@Override
	public void oneStepMove() {
		game_unit.oneStepMove();
	}

}
