package move;

import java.awt.Canvas;
import java.util.Set;

import entity_middle_age.GameUnit;
import gameframework.core.GameMovableDriver;
import gameframework.core.Movable;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.SpeedVector;

public class PlayerMoveDriver implements GameMovableDriver {

	private MoveToTargetDriver target_driver;
	private AgroMoveDriver agro_zone_driver;

	public PlayerMoveDriver(Set<GameUnit> enemies, MoveBlockerChecker moveBlockerChecker) {
		target_driver = new MoveToTargetDriver();

		agro_zone_driver = new AgroMoveDriver(enemies);

		target_driver.setmoveBlockerChecker(moveBlockerChecker);
		agro_zone_driver.setmoveBlockerChecker(moveBlockerChecker);
	}

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		SpeedVector speed_vect = target_driver.getSpeedVector(m);
		// Try to go to the target
		if (!(speed_vect.getDirection().x == 0 && speed_vect.getDirection().y == 0))
			return speed_vect;
		// if not possible, try to attack
		return agro_zone_driver.getSpeedVector(m);
	}

}
