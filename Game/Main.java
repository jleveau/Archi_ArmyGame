
import gameframework.core.GameLevel;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArmyGame g = new ArmyGame();
		ArrayList<GameLevel> levels = new ArrayList<>();

		//levels.add(new GameLevelMedieval(g)); // only one level is available
		levels.add(new GameLevelSpacial(g)); // only one level is available

		g.setLevels(levels);

		g.start();
	}
}
