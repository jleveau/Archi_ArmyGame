import java.awt.Point;
import java.util.Observer;

import behaviors.LevelOneUnitFactory;
import entity.GameSwordMan;
import entity.GameUnit;
import entity.GameUnitGroup;
import entity.Selector;
import entity.Wall;
import gameframework.core.CanvasDefaultImpl;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.core.GameUniverseViewPortDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.MoveBlockerCheckerDefaultImpl;
import gameframework.moves_rules.MoveStrategyRandom;
import gameframework.moves_rules.OverlapProcessor;
import gameframework.moves_rules.OverlapProcessorDefaultImpl;
import move.EnnemyUnitMovableDriver;
import move.GameMoveBlockerChecker;
import move.MoveStrategyMouse;
import move.PlayerUnitMoveDriver;
import move.UnitMoveBlockers;
import observers.KillObserver;
import overlap.UnitOverlapRule;
import pacman.rule.PacmanMoveBlockers;
import soldier.core.UnitGroup;
import soldier.gameLevel.LevelOneFactory;

public class GameLevelOne extends ArmyGameLevel {
	


	public static int BASE_GOLD = 1000;
	private int NB_ENEMY = 10;
	

	public GameLevelOne(ArmyGame g) {
		super(g, BASE_GOLD);
	}

	static int[][] tab = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static final int SPRITE_SIZE = 16;

	@Override
	protected void init() {

		MoveBlockerChecker moveBlockerChecker = new GameMoveBlockerChecker();
		OverlapProcessor overlap_proc = new OverlapProcessorDefaultImpl();

		overlap_proc.setOverlapRules(new UnitOverlapRule());
		moveBlockerChecker.setMoveBlockerRules(new UnitMoveBlockers());

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlap_proc);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		KillObserver kill_obs = new KillObserver(universe);

		//Rectangle used to select units with the mouse
		Selector selector = new Selector();
		universe.addGameEntity(selector);
		UnitSelector unit_selector = new UnitSelector(selector);
		canvas.addMouseListener(unit_selector);
		canvas.addMouseMotionListener(unit_selector);

		for (int i = 0; i < this.gold.length; i++) {
			gold[i].setValue(base_gold);
		}

		GameSwordMan sword_man;
		LevelOneUnitFactory factory = new LevelOneUnitFactory(canvas);

		GameUnitGroup regiment = factory.regiment("reg 1");
		GameUnitGroup regiment_2 = factory.regiment("reg 2");
		

		for (int j = 0; j < tab.length; j++) {
			for (int i = 0; i < tab[j].length; i++) {
				//Create Walls
				if (tab[j][i] == 1) {
					universe.addGameEntity(new Wall(canvas, i * SPRITE_SIZE, j * SPRITE_SIZE));
				}
				//create Enemies
				if (tab[j][i] == 2){
					GameMovableDriverDefaultImpl enemyDriv = new EnnemyUnitMovableDriver();
					MoveStrategyRandom ranStr = new MoveStrategyRandom();
					enemyDriv.setStrategy(ranStr);
					enemyDriv.setmoveBlockerChecker(moveBlockerChecker);
					sword_man = factory.infantryUnit(canvas, "Enemy swordman");
					sword_man.setDriver(enemyDriv);
					sword_man.setPosition(new Point(i * SPRITE_SIZE, j * SPRITE_SIZE));
					sword_man.setTeam(Enemy_team);
					sword_man.addObserver(kill_obs);
					universe.addGameEntity(sword_man);

					//(overlapRules).addGhost(myGhost);
				}
				if (tab[j][i] == 3){
					GameMovableDriverDefaultImpl playerDriver = new PlayerUnitMoveDriver();
					MoveStrategyMouse mouse_str = new MoveStrategyMouse();
					canvas.addMouseListener(mouse_str);
					playerDriver.setStrategy(mouse_str);
					playerDriver.setmoveBlockerChecker(moveBlockerChecker);
					sword_man =  factory.infantryUnit(canvas, "Player swordman");
					sword_man.setDriver(playerDriver);
					sword_man.setPosition(new Point(i * SPRITE_SIZE, j * SPRITE_SIZE));
					sword_man.setTeam(Player_team);
					regiment.addUnit(sword_man);
					universe.addGameEntity(sword_man);
					//(overlapRules).addGhost(myGhost);
				}
				
				if (tab[j][i] == 4){
					GameMovableDriverDefaultImpl playerDriver = new PlayerUnitMoveDriver();
					MoveStrategyMouse mouse_str = new MoveStrategyMouse();
					canvas.addMouseListener(mouse_str);
					playerDriver.setStrategy(mouse_str);
					playerDriver.setmoveBlockerChecker(moveBlockerChecker);
					sword_man =  factory.infantryUnit(canvas, "Player swordman");
					sword_man.setDriver(playerDriver);
					sword_man.setPosition(new Point(i * SPRITE_SIZE, j * SPRITE_SIZE));
					regiment_2.addUnit(sword_man);
					universe.addGameEntity(sword_man);

					//(overlapRules).addGhost(myGhost);
				}
			}
		}
		universe.addGameEntity(regiment);

		unit_selector.addUnit(regiment);
		unit_selector.addUnit(regiment_2);

	}
}
