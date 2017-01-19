package move;

import entity.GameUnit;
import entity.GameSwordMan;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;
import entity.Wall;

public class UnitMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {
	
	public void moveBlockerRule(GameUnit game_unit, Wall w) throws IllegalMoveException {
		throw new IllegalMoveException();
	}

	public void moveBlockerRule(GameSwordMan game_unit, GameUnit w) throws IllegalMoveException {
		if (game_unit.equals(w.getGame_unit()))
			return;
		throw new IllegalMoveException();
	}
}
