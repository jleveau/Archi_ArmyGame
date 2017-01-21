package behaviors;

import java.awt.Canvas;

import entity.GameUnit;
import entity.GameUnitGroup;
import entity.Regiment;
import soldier.core.Unit;
import soldier.core.UnitGroup;

public abstract class GameAbstractFactory {
	private Canvas canvas;

	public GameAbstractFactory(Canvas c) {
		this.canvas = c;
	}

	public abstract Unit infantryUnit(Canvas canvas, String name);

	public Regiment regiment(String name) {
		return new Regiment(canvas, new UnitGroup(name));
	}
}
