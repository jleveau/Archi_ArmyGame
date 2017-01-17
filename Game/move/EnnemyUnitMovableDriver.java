package move;

import java.awt.Point;

import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.Movable;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

public class EnnemyUnitMovableDriver  extends GameMovableDriverDefaultImpl {

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		return new SpeedVectorDefaultImpl(new Point(0,0));
	}

}