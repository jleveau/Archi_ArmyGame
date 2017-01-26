package controller;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import entity_middle_age.GameUnit;

public class UnitController extends MouseAdapter implements MouseMotionListener, KeyListener {

	private Set<Selectable> selectable_units;
	Point press_point;
	Selector selector;

	public UnitController(Selector s) {
		selectable_units = new HashSet<Selectable>();
		selector = s;
	}

	public void addUnit(GameUnit unit) {
		selectable_units.add(unit);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			press_point = new Point(e.getX(), e.getY());
			for (Selectable unit : selectable_units) {
				unit.setSelected(false);
			}
			selector.setRect(new Rectangle(press_point.x, press_point.y, 0, 0));
		}
		if (e.getButton() == MouseEvent.BUTTON3){
			OrderMove order = new OrderMove(new Point(e.getX(), e.getY()));
			sendOrder(order);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			selector.getRect().x = Math.min(press_point.x, e.getX());
			selector.getRect().y = Math.min(press_point.y, e.getY());
			selector.getRect().width = Math.abs(press_point.x - e.getX());
			selector.getRect().height = Math.abs(press_point.y - e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			if (press_point == null)
				return;
			
			selector.getRect().x = Math.min(press_point.x, e.getX());
			selector.getRect().y = Math.min(press_point.y, e.getY());
			selector.getRect().width = Math.max(1,Math.abs(press_point.x - e.getX()));
			selector.getRect().height = Math.max(1,Math.abs(press_point.y - e.getY()));
			for (Selectable unit : selectable_units) {
				if (unit.isSelectedBy(selector.getRect())) {
					unit.setSelected(true);
				}
			}
			selector.setRect(null);
			press_point = null;
		}
	}
	
	public void sendOrder(Order order){
		for (Selectable unit : selectable_units) {
			if (unit.isSelected()){
				order.unit = (GameUnit) unit;
				unit.receive_order(order);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyChar() == ' '){
			 OrderFire order = new OrderFire();
			 sendOrder(order);
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
