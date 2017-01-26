package controller;

import java.awt.Point;

import entity_middle_age.GameUnitEntity;

public class OrderMove extends Order{
	
	public OrderMove(){}

	public OrderMove(Point position) {
		super();
		this.position = position;
		// TODO Auto-generated constructor stub
	}

	public void execute(GameUnitEntity unit){
		unit.setTarget_position(position);
	}
}
