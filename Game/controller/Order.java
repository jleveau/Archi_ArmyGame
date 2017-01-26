package controller;

import java.awt.Point;

import entity_middle_age.GameUnit;
import entity_middle_age.GameUnitEntity;

public abstract class Order {
	// Pattern Visitor
	//Tell a unit to do an action at some position
	public Point position;
	public GameUnit unit;
	
	public Order(){
	}
	
	public Order(Point position, GameUnit unit) {
		super();
		this.position = position;
		this.unit = unit;
	}
	
	public abstract void execute(GameUnitEntity unit);
	
}
