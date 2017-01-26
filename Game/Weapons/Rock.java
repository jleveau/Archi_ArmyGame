package Weapons;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity_middle_age.GameUnit;
import entity_middle_age.Target;
import gameframework.core.GameMovable;
import gameframework.moves_rules.SpeedVector;
import soldier.core.BehaviorSoldier;
import soldier.core.Weapon;
import soldier.weapon.WeaponVisitor;

public class Rock extends GameThrowableWeapon implements GameWeapon, Target {

	public Rock(Canvas c) {
		super(c);
	}

	private static int RENDERING_SIZE = 6;
	private Point target;
	private Point position;

	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(this.getPosition().x, this.getPosition().y, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public Point getTarget_position() {
		return target;
	}

	@Override
	public void setTarget_position(Point target_pos) {
		target = target_pos;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviorSoldier createExtension(BehaviorSoldier s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(WeaponVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeedVector getSpeedVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpeedVector(SpeedVector m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oneStepMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameWeapon throwToTarget(GameUnit launcher) {
		// TODO Auto-generated method stub
		return null;
	}

}
