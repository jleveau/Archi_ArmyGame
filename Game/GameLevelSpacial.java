import factories.GameAbstractFactory;
import factories.SpaceFactory;
import gameframework.core.GameLevel;

public class GameLevelSpacial extends GameLevelAbstr implements GameLevel {

	public GameLevelSpacial(ArmyGame g) {
		super(g,"images/space/background_image.gif");
	}

	@Override
	protected GameAbstractFactory createUnitFactory() {
		return new SpaceFactory(canvas);
	}

}
