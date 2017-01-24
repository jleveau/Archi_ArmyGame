package move;

import entity.GameSwordMan;
import entity.PlayerSwordMan;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;

public class UnitMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {
	
	public void moveBlockerRule(GameSwordMan game_unit, GameSwordMan w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(PlayerSwordMan game_unit, PlayerSwordMan w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(PlayerSwordMan game_unit, GameSwordMan w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		if (game_unit.getTeam() != w.getTeam()){
			game_unit.setStrikeState();
			game_unit.attack(w);
		}
			
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(GameSwordMan game_unit, PlayerSwordMan w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		if (game_unit.getTeam() != w.getTeam()){
			game_unit.setStrikeState();
			game_unit.attack(w);
		}
			
		throw new IllegalMoveException();
	}
}
