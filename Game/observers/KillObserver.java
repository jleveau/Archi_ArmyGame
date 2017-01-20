package observers;

import entity.GameUnit;
import gameframework.core.GameUniverse;
import observer_util.Observer;
import soldier.core.Unit;

public class KillObserver implements Observer<Unit> {
	
	GameUniverse universe;
	
	public KillObserver(GameUniverse universe) {
		super();
		this.universe = universe;
	}

	@Override
	public void update(Unit s) {
		if (s instanceof GameUnit)
			universe.removeGameEntity((GameUnit)s);
	}

}
