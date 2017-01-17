package move;

import entity.PlayerSwordMan;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;
import entity.Wall;

public class UnitMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {
	
	public void moveBlockerRule(PlayerSwordMan game_unit, Wall w) throws IllegalMoveException {
		throw new IllegalMoveException();
	}

}
