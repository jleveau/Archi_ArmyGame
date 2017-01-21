package entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

import exceptions.IllegalAddException;
import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Movable;
import gameframework.core.Overlappable;
import gameframework.core.SpriteManager;
import gameframework.core.SpriteManagerDefaultImpl;
import gameframework.moves_rules.MoveBlocker;
import observer_util.Observable;
import soldier.core.Unit;
import units_states.GameUnitState;
import units_states.NormalState;
import units_states.StrikeState;

public abstract class GameUnitEntity extends GameUnit implements Drawable {

	protected static DrawableImage image = null;
	protected boolean active = true;
	private static int ARBITRARY_REACH_TARGET=5;
	protected boolean selected;
	Point target_position;
	GameUnitState state;
	private SpriteManager spriteManager;

	public GameUnitEntity(Canvas defaultCanvas, Unit unit) {
		super(unit);
		canvas = defaultCanvas;
		spriteManager = new SpriteManagerDefaultImpl("images/ghost.gif", canvas, RENDERING_SIZE, 6);

		state = NormalState.getInstance(spriteManager);

		spriteManager.setTypes(
				//
				"left", "right", "up", "down", //
				"beginAfraid-left", "beginAfraid-right", "beginAfraid-up", "beginAfraid-down", //
				"endAfraid-left", "endAfraid-right", "endAfraid-up", "endAfraid-down", //
				"inactive-left", "inactive-right", "inactive-up", "inactive-down", //
				"unused");
	}

	@Override
	public boolean canStrike() {
		return state.canStrike();
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
		state.increment();
		if (getTarget_position() != null) {

			if (getPosition().distance(getTarget_position()) < ARBITRARY_REACH_TARGET) {
				notify_target_reached();
			}
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
		this.state = StrikeState.getInstance(spriteManager);
	}

	public void setNormalState() {
		this.state = NormalState.getInstance(spriteManager);
	}

}
