package observers;

import gameframework.core.GameEntity;

public interface EntityCreatorObserver {
	public void update_creation(GameEntity game_entity);

	public void update_destruction(GameEntity game_entity);
}
