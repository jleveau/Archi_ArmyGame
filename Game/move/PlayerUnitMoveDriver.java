package move;

import java.awt.Point;
import java.util.Random;

import entity.GameUnit;
import entity.GameUnitEntity;
import gameframework.core.GameMovableDriver;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.Movable;
import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.MoveStrategyRandom;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

public class PlayerUnitMoveDriver extends GameMovableDriverDefaultImpl {

	private int base_speed = 1;
	MoveStrategy random_str;
	
	public PlayerUnitMoveDriver(){
		random_str = new MoveStrategyRandom();
	}

	public SpeedVector getSpeedVector(Movable m) {
		int speed;
		SpeedVector target_position = moveStrategy.getSpeedVector();
		//Case no target
		if (target_position == null)
			return SpeedVectorDefaultImpl.createNullVector();
		if (target_position.getDirection() == null)
			return SpeedVectorDefaultImpl.createNullVector();
		
		if (m instanceof GameUnitEntity) {
			GameUnitEntity gm = (GameUnitEntity) m;
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
		Random rand = new Random();
		if (rand.nextInt(100)<10){
			SpeedVector rand_vector = random_str.getSpeedVector();
			rand_vector.setSpeed(speed);
			if (moveBlockerChecker.moveValidation(m, rand_vector)) {
				return rand_vector;
			}
		}
		return SpeedVectorDefaultImpl.createNullVector();
	}
}
