package entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;
import gameframework.core.SpriteManagerDefaultImpl;
import gameframework.moves_rules.MoveBlocker;
import observer_util.Observer;
import soldier.core.Unit;
import soldier.core.UnitVisitor;
import soldier.core.Weapon;
import units_states.StrikeState;

public class GameSwordMan extends GameUnitEntity implements MoveBlocker{

	protected static DrawableImage image = null;
	protected boolean active = true;


	public GameSwordMan(Canvas defaultCanvas, Unit unit) {
		super(defaultCanvas, unit);
	}

	public int getSpeed() {
		return 5;
	}

}
