package entity_middle_age;

import controller.Selectable;
import gameframework.core.GameEntity;
import observers.GameObserver;
import soldier.core.Unit;

public interface GameUnitItf extends Unit, Selectable, GameEntity, TeamUnit, Target{
	
	void notify_death();
	void notify_target_reached();
	void remove_game_obs(GameObserver<GameUnit> obs);
	void add_game_obs(GameObserver<GameUnit> obs);
}
