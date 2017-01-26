package move;

import java.awt.Point;
import java.util.Set;

import entity_middle_age.GameUnit;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.Movable;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

public class AgroMoveDriver extends GameMovableDriverDefaultImpl {
	private Set<GameUnit> units;
	private static int BASE_AGRO_ZONE = 50;

	public AgroMoveDriver(Set<GameUnit> units) {
		this.units = units;
	}

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		Point pos = m.getPosition();
		Point target = m.getPosition();
		float min_dist = BASE_AGRO_ZONE;

		int speed = 1;
		if (m instanceof GameUnit)
			speed = ((GameUnit) m).getSpeed();

		for (GameUnit unit : units) {
			if (!unit.alive())
				continue;

			Point target_tmp = unit.getPosition();
			if (pos.distance(target_tmp) < min_dist) {
				target = unit.getPosition();
			}
		}

		Point dir = new Point(0, 0);
		if (pos.x < target.x)
			dir.x = 1;
		else if (pos.x > target.x)
			dir.x = -1;
		if (pos.y < target.y)
			dir.y = 1;
		if (pos.y > target.y)
			dir.y = -1;

		SpeedVector possible_speedVector = new SpeedVectorDefaultImpl(dir, speed);
		if (moveBlockerChecker.moveValidation(m, possible_speedVector))
			return possible_speedVector;
		possible_speedVector = new SpeedVectorDefaultImpl(new Point(dir.x, 0), speed);
		
		possible_speedVector = new SpeedVectorDefaultImpl(new Point(dir.x,0), speed);
		if (moveBlockerChecker.moveValidation(m, possible_speedVector)) {
			return possible_speedVector;
		}
		possible_speedVector = new SpeedVectorDefaultImpl(new Point(0,dir.y), speed);
		if (moveBlockerChecker.moveValidation(m, possible_speedVector)) {
			return possible_speedVector;
		}
		return SpeedVectorDefaultImpl.createNullVector();
	}

}
