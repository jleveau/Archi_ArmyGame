package move;

import java.awt.Point;
import java.util.Random;

import entity_middle_age.GameUnit;
import entity_middle_age.GameUnitEntity;
import gameframework.core.GameMovableDriver;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.Movable;
import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.MoveStrategyRandom;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

public class MoveToTargetDriver extends GameMovableDriverDefaultImpl {

	private int base_speed = 1;

	public SpeedVector getSpeedVector(Movable m) {
		int speed = 0;
		SpeedVector target_position = null;;
		//Case no target
	
		if (m instanceof GameUnitEntity) {
			GameUnitEntity gm = (GameUnitEntity) m;
			speed = gm.getSpeed();
			target_position = new SpeedVectorDefaultImpl(gm.getTarget_position(),speed);
		} 
		
		if (target_position == null)
			return SpeedVectorDefaultImpl.createNullVector();
		if (target_position.getDirection() == null)
			return SpeedVectorDefaultImpl.createNullVector();
		
		Point current_pos = m.getPosition();

		// Compute direction to target

		int dir_x = target_position.getDirection().x - current_pos.x;
		int dir_y = target_position.getDirection().y - current_pos.y;
		int new_x, new_y;
		
		
		if (Math.abs(dir_x) <= speed)
			new_x = 0;
		else {
			if (dir_x > 0){
				new_x = 1;
			}
			else new_x = -1;
		}
		
		if (Math.abs(dir_y) <= speed)
			new_y = 0;
		else {
			if (dir_y > 0){
				new_y = 1;
			}
			else new_y = -1;
		}
		Point direction = new Point(new_x, new_y);
		SpeedVector possibleSpeedVector = new SpeedVectorDefaultImpl(direction, speed);
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}
		possibleSpeedVector = new SpeedVectorDefaultImpl(new Point(direction.x,0), speed);
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}
		possibleSpeedVector = new SpeedVectorDefaultImpl(new Point(0,direction.y), speed);
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}

		return SpeedVectorDefaultImpl.createNullVector();
	}
}
