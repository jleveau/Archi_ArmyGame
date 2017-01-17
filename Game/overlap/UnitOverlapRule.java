package overlap;

import gameframework.core.GameUniverse;
import gameframework.moves_rules.OverlapRulesApplierDefaultImpl;

public class UnitOverlapRule  extends OverlapRulesApplierDefaultImpl {
	GameUniverse universe;

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	
}
