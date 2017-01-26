package entity_middle_age;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import controller.Order;
import observer_util.Observer;
import observers.GameObserver;
import soldier.core.Unit;
import soldier.core.UnitGroup;

public class Regiment extends GameUnitGroup implements GameObserver<GameUnit>, Observer<Unit> {
	
	private static int LIFE_BAR_WIDTH = 50;
	private static int LIFE_BAR_HEIGHT = 5;
	private static int OFFSET_LIFE_BAT_HEIGHT = 15;


	public Regiment(Canvas canvas, UnitGroup group) {
		super(canvas, group);
	}

	@Override
	public void addUnit(GameUnitEntity g) {
		g.add_game_obs(this);
		super.addUnit(g);
	}

	@Override
	public void removeUnit(GameUnitEntity g) {
		g.removeObserver(this);
		super.removeUnit(g);
	}

	@Override
	public void update_death(GameUnit s) {
		if (!this.alive()) {
			notify_death();
		}
	}

	@Override
	public void update_target_reach(GameUnit s) {
		setTarget_position(null);
	}

	@Override
	public void update(Unit s) {

	}
	
	private Point getLifeBarPos() {
		int nb = 0;
		int pos_x = 0;
		int pos_y = Integer.MAX_VALUE;
		for (GameUnit unit : units) {
			if (!unit.alive())
				continue;
			pos_x += unit.getPosition().x;
			pos_y = Integer.min(pos_y, unit.getPosition().y);
			nb++;
		}
		pos_x /= nb;
		pos_y -= OFFSET_LIFE_BAT_HEIGHT;
		return new Point(pos_x, pos_y);
	}

	
	@Override
	public void draw(Graphics g) {
		// Draw life bar
		Point life_bar_pos = getLifeBarPos();
		float health_ratio = getHealthPoints() / getMaxHealthPoints();
		g.setColor(Color.GREEN);
		g.fillRect(life_bar_pos.x - (LIFE_BAR_WIDTH / 2), life_bar_pos.y, (int) (health_ratio * LIFE_BAR_WIDTH), LIFE_BAR_HEIGHT);
		g.setColor(Color.black);
		g.drawRect(life_bar_pos.x - (LIFE_BAR_WIDTH / 2), life_bar_pos.y, LIFE_BAR_WIDTH, LIFE_BAR_HEIGHT);

	}

	@Override
	public void receive_order(Order order) {
		for (GameUnitEntity unit : units) {
			order.execute(unit);
		}
	}

}
