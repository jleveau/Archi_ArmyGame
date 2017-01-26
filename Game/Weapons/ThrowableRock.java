package Weapons;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity_middle_age.GameUnit;
import gameframework.moves_rules.SpeedVector;
import soldier.core.BehaviorExtConst;
import soldier.core.BehaviorSoldier;

public class ThrowableRock extends GameThrowableWeapon {
	
	public ThrowableRock(Canvas c) {
		super(c);
	}

	private Rock rock;
	private boolean launched;

	@Override
	public GameWeapon throwToTarget(GameUnit launcher) {
		Rock rock = new Rock(canvas);
		rock.setPosition(launcher.getPosition());
		//rock.setTarget_position(target);
		launched = false;
		return rock;
	}
	
	@Override
	public String getName() {
		return "Rock";
	}
	
	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	@Override
	public BehaviorSoldier createExtension(BehaviorSoldier s) {
		return new BehaviorExtConst(this, s, 5, 0);
	}

	@Override
	public Point getPosition() {
		return rock.getPosition();
	}

	@Override
	public Rectangle getBoundingBox() {
		return rock.getBoundingBox();
	}

	@Override
	public void draw(Graphics g) {
		rock.draw(g);
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

}
