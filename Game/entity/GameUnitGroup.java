package entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import soldier.core.Unit;
import soldier.core.UnitGroup;

public class GameUnitGroup extends GameUnit implements GameEntity, Unit, Target, Drawable {

	private Set<GameUnitEntity> units;
	private boolean selected;
	private Point target_pos;
	private static int LIFE_BAR_WIDTH = 50;
	private static int LIFE_BAR_HEIGHT = 5;
	private static int OFFSET_LIFE_BAT_HEIGHT = 15;

	public GameUnitGroup(Canvas canvas, UnitGroup group) {
		super(group);
		units = new HashSet<GameUnitEntity>();
		selected = false;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		for (GameUnitEntity game_unit : units) {
			game_unit.oneStepMoveAddedBehavior();
		}
	}

	@Override
	public boolean isSelectedBy(Rectangle rect) {
		for (GameUnitEntity game_unit : units) {
			if (game_unit.isSelectedBy(rect))
				return true;
		}
		return false;
	}

	// May be changed if we want better coordination
	@Override
	public void setTarget_position(Point target_position) {
		for (GameUnitEntity game_unit : units) {
			game_unit.setTarget_position(target_position);
		}
	}

	@Override
	public void setSelected(boolean b) {
		for (GameUnitEntity game_unit : units) {
			game_unit.setSelected(b);
		}
		selected = b;
	}

	public void addUnit(GameUnitEntity g) {
		super.addUnit(g);
		this.units.add(g);
	}

	public void removeUnit(GameUnitEntity g) {
		super.removeUnit(g);
		this.units.remove(g);
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public Point getTarget_position() {
		return target_pos;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(0, 0, 0, 0);
	}

	public Point getLifeBarPos() {
		int pos_x = 0;
		int pos_y = Integer.MAX_VALUE;
		for (GameUnit unit : units) {
			pos_x += unit.getPosition().x;
			pos_y = Integer.min(pos_y, unit.getPosition().y);
		}
		pos_x /= units.size();
		pos_y -= OFFSET_LIFE_BAT_HEIGHT;
		return new Point(pos_x, pos_y);
	}

	@Override
	public void draw(Graphics g) {
		// Draw life bar
		Point life_bar_pos = getLifeBarPos();
		g.setColor(Color.GREEN);
		g.fillRect(life_bar_pos.x - (LIFE_BAR_WIDTH / 2), life_bar_pos.y, LIFE_BAR_WIDTH, LIFE_BAR_HEIGHT);
		g.setColor(Color.black);
		g.drawRect(life_bar_pos.x - (LIFE_BAR_WIDTH / 2), life_bar_pos.y, LIFE_BAR_WIDTH, LIFE_BAR_HEIGHT);

	}

	@Override
	public Point getPosition() {
		int pos_x = 0;
		int pos_y = 0;
		for (GameUnit unit : units) {
			pos_x += unit.getPosition().x;
			pos_y += unit.getPosition().y;
		}
		pos_x /= units.size();
		pos_y /= units.size();
		return new Point(pos_x, pos_y);
	}

}
