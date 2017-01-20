package move;

import entity.GameUnit;
import entity.GameSwordMan;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;
import units_states.StrikeState;
import entity.Wall;

public class UnitMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {
	
	public void moveBlockerRule(GameUnit game_unit, Wall w) throws IllegalMoveException {
		throw new IllegalMoveException();
	}

	public void moveBlockerRule(GameSwordMan game_unit, GameSwordMan w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		if (game_unit.getTeam() != w.getTeam()){
			game_unit.setStrikeState();
			game_unit.attack(w);
		}
			
		throw new IllegalMoveException();
	}
}
