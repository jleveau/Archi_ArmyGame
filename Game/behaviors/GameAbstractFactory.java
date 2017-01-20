package behaviors;

import java.awt.Canvas;

import entity.GameUnit;
import entity.GameUnitGroup;
import soldier.core.Unit;
import soldier.core.UnitGroup;

public abstract class GameAbstractFactory {
	private Canvas canvas;

	public GameAbstractFactory(Canvas c) {
		this.canvas = c;
	}

	public abstract Unit infantryUnit(Canvas canvas, String name);

	public GameUnitGroup regiment(String name) {
		return new GameUnitGroup(canvas, new UnitGroup(name));
	}
}
