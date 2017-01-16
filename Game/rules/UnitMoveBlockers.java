package rules;

import entity.GameUnit;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;
import pacman.entity.Wall;

public class UnitMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {

	public void moveBlockerRule(GameUnit game_unit, Wall w) throws IllegalMoveException {
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(GameUnit game_unit1, GameUnit game_unit2) throws IllegalMoveException {
		throw new IllegalMoveException();
	}

}
