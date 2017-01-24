package overlap;

import Weapons.Rock;
import Weapons.ThrowableRock;
import entity.PlayerSwordMan;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverse;
import gameframework.moves_rules.MoveStrategyStraightLine;
import gameframework.moves_rules.OverlapRulesApplierDefaultImpl;
import pacman.entity.Ghost;
import pacman.entity.Pacman;

public class UnitOverlapRule extends OverlapRulesApplierDefaultImpl {
	GameUniverse universe;

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void overlapRule(PlayerSwordMan p, ThrowableRock rock) {
		if (!rock.isLaunched()) {
			if (!p.hasWeapon()) {
				p.pickRock(rock);
			}
		}
	}
}
