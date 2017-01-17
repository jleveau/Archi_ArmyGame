package move;

import java.awt.Point;

import entity.GameUnit;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.Movable;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

public class PlayerUnitMoveDriver extends GameMovableDriverDefaultImpl {

	private int base_speed = 1;

	public SpeedVector getSpeedVector(Movable m) {
		
		
		int speed;
		SpeedVector target_position = moveStrategy.getSpeedVector();

		//Case no target
		if (target_position == null)
			return new SpeedVectorDefaultImpl(new Point(0,0), 1);
		if (target_position.getDirection() == null)
			return new SpeedVectorDefaultImpl(new Point(0,0), 1);
		
		if (m instanceof GameUnit) {
			GameUnit gm = (GameUnit) m;

			speed = gm.getSpeed();
			if (gm.isSelected()){
				gm.setTarget_position(target_position.getDirection());
			}
			else{
				target_position.setDirection(gm.getTarget_position());
			}
		} else
		{
			speed = base_speed;
		}
		//Case target reached
		if (target_position.getDirection().distance(m.getPosition()) == 0)
			return new SpeedVectorDefaultImpl(new Point(0,0), 1);

		Point current_pos = m.getPosition();

		// Compute direction to target

		int dir_x = target_position.getDirection().x - current_pos.x;
		int dir_y = target_position.getDirection().y - current_pos.y;
		int new_x, new_y;
		

		if (Math.abs(dir_x) <= speed)
			new_x = 0;
		else {
			if (dir_x > 0){
				new_x = speed;
			}
			else new_x = -speed;
		}
		
		if (Math.abs(dir_y) <= speed)
			new_y = 0;
		else {
			if (dir_y > 0){
				new_y = speed;
			}
			else new_y = -speed;
		}
		Point direction = new Point(new_x, new_y);
		SpeedVector possibleSpeedVector = new SpeedVectorDefaultImpl(direction, 1);
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}

		// current vector.
		possibleSpeedVector = m.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}
		return SpeedVectorDefaultImpl.createNullVector();
	}
}
