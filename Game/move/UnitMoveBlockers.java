package move;

import Weapons.Missile;
import entity_middle_age.GameSwordMan;
import entity_middle_age.PlayerSwordMan;
import entity_space.SpaceShipEnemy;
import entity_space.SpaceShipPlayer;
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
	
	public void moveBlockerRule(SpaceShipEnemy game_unit, SpaceShipEnemy w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(SpaceShipPlayer game_unit, SpaceShipPlayer w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(SpaceShipPlayer game_unit, SpaceShipEnemy w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		if (game_unit.getTeam() != w.getTeam()){
			game_unit.setStrikeState();
			game_unit.attack(w);
		}
			
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(SpaceShipEnemy game_unit, SpaceShipPlayer w) throws IllegalMoveException {
		if (game_unit.equals(w))
			return;
		if (game_unit.getTeam() != w.getTeam()){
			game_unit.setStrikeState();
			game_unit.attack(w);
		}
			
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(Missile missile, SpaceShipPlayer w) throws IllegalMoveException {
		if (missile.getTeam() == w.getTeam())
			return;
		else{
			w.parry(missile.getDamage());
			missile.notify_destruction(missile);
		}
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(Missile missile, SpaceShipEnemy w) throws IllegalMoveException {
		if (missile.getTeam() == w.getTeam())
			return;
		else{
			w.parry(missile.getDamage());
			missile.notify_destruction(missile);
		}
		throw new IllegalMoveException();
	}
}
