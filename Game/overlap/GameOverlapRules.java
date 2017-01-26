package overlap;

import Weapons.Missile;
import entity_middle_age.PlayerSwordMan;
import entity_space.SpaceShipPlayer;
import gameframework.core.GameUniverse;
import gameframework.moves_rules.OverlapRulesApplierDefaultImpl;
import soldier.core.BreakingRuleException;

public class GameOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void overlapRule(Missile p, PlayerSwordMan g) {
		try {
			if (!g.hasWeapon() && !p.isLaunched()) {
				g.addEquipment(p);
				universe.removeGameEntity(p);
			}
		} catch (BreakingRuleException e) {
		}
	}

	public void overlapRule(Missile p, SpaceShipPlayer g) {
		try {
			if (!g.hasWeapon() && !p.isLaunched()) {
				g.addEquipment(p);
				universe.removeGameEntity(p);
			}
		} catch (BreakingRuleException e) {
		}
	}
}
