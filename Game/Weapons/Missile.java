package Weapons;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

import entity_middle_age.GameUnit;
import gameframework.core.Movable;
import gameframework.core.SpriteManager;
import gameframework.core.SpriteManagerDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;
import soldier.core.BehaviorExtConst;
import soldier.core.BehaviorSoldier;
import soldier.core.WeaponAttack;

public class Missile extends GameThrowableWeapon implements Movable {

	private boolean launched;
	private static int RENDERING_SIZE = 16;
	private SpeedVector speed_vector;
	private boolean exploded;
	private SpriteManager sprite_manager;
	private static int BASE_SPEED = 10;
	private static int BASE_DAMAGE = 100;

	public Missile(Canvas c) {
		super(c);
		launched = false;
		exploded = false;
		speed_vector = SpeedVectorDefaultImpl.createNullVector();
		sprite_manager  = new SpriteManagerDefaultImpl("images/space/missile.gif", canvas, RENDERING_SIZE, 4);
		sprite_manager.setTypes("left", "right", "up", "down");
	}

	@Override
	public GameWeapon throwToTarget(GameUnit launcher) {
		this.setPosition((Point)launcher.getPosition().clone());
		launched = true;
		notify_creation(this);
		speed_vector = new SpeedVectorDefaultImpl(launcher.getSpeedVector().getDirection(),BASE_SPEED);
		this.team = launcher.getTeam();
		return this;
	}

	@Override
	public String getName() {
		return "Missile";
	}

	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	@Override
	public BehaviorSoldier createExtension(BehaviorSoldier s) {
		return new BehaviorExtConst(this, s, 0, 0);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(this.getPosition().x, this.getPosition().y, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public void draw(Graphics g) {
		Point tmp = this.getSpeedVector().getDirection();
		String spriteType;
		if (tmp.getX() == -1) {
			spriteType = "left";
		} else if (tmp.getY() == 1) {
			spriteType = "down";
		} else if (tmp.getY() == -1) {
			spriteType = "up";
		} else {
			spriteType = "right";
		}
		sprite_manager.setType(spriteType);
		sprite_manager.draw(g, getPosition());
	}

	@Override
	public SpeedVector getSpeedVector() {
		return speed_vector;
	}

	@Override
	public void setSpeedVector(SpeedVector m) {
		speed_vector = m;

	}

	@Override
	public void oneStepMove() {
		if (!this.launched)
			return;
		
		if (this.hasExploded()){
			System.out.println("exploded");
			return;
		}
		SpeedVector m = getSpeedVector();
		if (!move_checker.moveValidation(this, m)) {
			this.exploded = true;
		}
		position.translate((int) m.getDirection().getX() * m.getSpeed(),
				(int) m.getDirection().getY() * m.getSpeed());

	}

	public boolean hasExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	public int getDamage() {
		return BASE_DAMAGE;
	}

}
