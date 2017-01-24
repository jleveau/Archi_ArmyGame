package Weapons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import entity.GameUnit;
import soldier.core.BehaviorExtConst;
import soldier.core.BehaviorSoldier;

public class ThrowableRock extends GameThrowableWeapon {
	
	private Rock rock;
	private boolean launched;

	@Override
	public Rock throwToTarget(GameUnit launcher, GameUnit target) {
		Rock rock = new Rock();
		rock.setPosition(launcher.getPosition());
		rock.setTarget_position(target.getPosition());
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

}
