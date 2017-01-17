import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import entity.GameUnit;
import entity.Selectable;
import entity.Selector;

public class UnitSelector extends MouseAdapter implements MouseMotionListener {

	private Set<Selectable> selectable_units;
	Point press_point;
	Canvas canvas;
	Selector selector;

	UnitSelector(Selector s) {
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
		if (press_point == null)
			return;
		for (Selectable unit : selectable_units) {

			if (selector.getRect().intersects(unit.getSelectBox())) {
				unit.setSelected(true);
			}
		}
		selector.setRect(null);
		press_point = null;
	}

}