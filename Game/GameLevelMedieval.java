import factories.GameAbstractFactory;
import factories.MedievalFactory;
import gameframework.core.GameLevel;

public class GameLevelMedieval extends GameLevelAbstr implements GameLevel {

	public GameLevelMedieval(ArmyGame g) {
		super(g, "images/background_image.gif");
	}

	@Override
	protected GameAbstractFactory createUnitFactory() {
		return new MedievalFactory(canvas);
	}

}
