package entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Weapons.GameWeapon;
import Weapons.ThrowableRock;
import exceptions.IllegalAddException;
import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.SpriteManager;
import soldier.core.Unit;
import units_states.GameUnitState;
import units_states.NormalState;
import units_states.StrikeState;

public abstract class GameUnitEntity extends GameUnit implements Drawable {

	protected static DrawableImage image = null;
	protected boolean active = true;
	private static int ARBITRARY_REACH_TARGET=5;
	private static int ATTACK_SPEED = 1000;
	protected boolean selected;
	Point target_position;
	GameUnitState state;
	protected Set<GameWeapon> weapons;
	
	private long last_strike;
	private long time_between_strike;
	
	protected SpriteManager spriteManager;

	public GameUnitEntity(Canvas defaultCanvas, Unit unit) {
		super(unit);
		canvas = defaultCanvas;
		weapons = new HashSet<GameWeapon>();
		state = NormalState.getInstance();
		time_between_strike = ATTACK_SPEED;
		last_strike = 0;
		createSpriteManager();
	}
	
	public abstract void createSpriteManager();

	@Override
	public boolean canStrike() {
		return state.canStrike(this);
	}

	public boolean isActive() {
		return active;
	}

	public void setAlive(boolean aliveState) {
		active = aliveState;
	}

	public void draw(Graphics g) {
		state.draw(g, this);
	}

	@Override
	public void receive_order(Point p) {
		setTarget_position(p);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		spriteManager.increment();
		if (getTarget_position() != null) {
			if (getPosition().distance(getTarget_position()) < ARBITRARY_REACH_TARGET) {
				notify_target_reached();
			}
		}
		if (canStrike()){
			setState(NormalState.getInstance());
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(this.getPosition().x, this.getPosition().y, RENDERING_SIZE, RENDERING_SIZE);
	}

	@Override
	public boolean isSelectedBy(Rectangle rect) {
		return getBoundingBox().intersects(rect);
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean b) {
		selected = b;
	}

	public Point getTarget_position() {
		return target_position;
	}

	public void setTarget_position(Point target_position) {
		this.target_position = target_position;
	}

	public void addUnit(GameUnitEntity game_unit) throws IllegalAddException {
		throw new IllegalAddException();
	}

	public void removeUnit(GameUnitEntity game_unit) throws IllegalAddException {
		throw new IllegalAddException();
	}

	public GameUnitState getState() {
		return state;
	}

	public void setState(GameUnitState state) {
		this.state = state;
	}

	public void setStrikeState() {
		this.state = StrikeState.getInstance();
	}

	public void setNormalState() {
		this.state = NormalState.getInstance();
	}
	
	public long getLast_strike() {
		return last_strike;
	}

	public void setLast_strike(long last_strike) {
		this.last_strike = last_strike;
	}
	
	public long getTime_between_strike() {
		return time_between_strike;
	}

	public void setTime_between_strike(long time_between_strike) {
		this.time_between_strike = time_between_strike;
	}

	public SpriteManager getSpriteManager() {
		return spriteManager;
	}
	
	public void pickRock(ThrowableRock g) {
		this.addEquipment(g);
		weapons.add(g);
	}
	
	public Iterator<GameWeapon> getDrawableWeapons(){
		return weapons.iterator();
	}

}
