package observers;

import entity.GameUnit;
import gameframework.core.GameUniverse;

public class KillObserver implements GameObserver<GameUnit> {

	GameUniverse universe;

	public KillObserver(GameUniverse universe) {
		super();
		this.universe = universe;
	}

	@Override
	public void update_death(GameUnit s) {
		if (!s.alive()){
			universe.removeGameEntity((GameUnit) s);
		}
	}

	@Override
	public void update_target_reach(GameUnit s) {
		return;
	}

}
