import gameframework.core.GameEntity;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.OverlapProcessor;
import observers.EntityCreatorObserver;

public class ArmyGameUniverse extends GameUniverseDefaultImpl implements EntityCreatorObserver {

	public ArmyGameUniverse(MoveBlockerChecker obs, OverlapProcessor col) {
		super(obs, col);
	}

	@Override
	public void update_creation(GameEntity game_entity) {
		this.addGameEntity(game_entity);
	}

	@Override
	public void update_destruction(GameEntity game_entity) {
		this.removeGameEntity(game_entity);
	}

}
