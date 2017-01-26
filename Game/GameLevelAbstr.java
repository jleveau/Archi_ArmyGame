import java.awt.Point;
import java.util.HashSet;

import Weapons.GameThrowableWeapon;
import Weapons.Missile;
import controller.Selector;
import controller.UnitController;
import entity_middle_age.GameUnit;
import entity_middle_age.GameUnitEntity;
import entity_middle_age.Regiment;
import entity_middle_age.Wall;
import factories.GameAbstractFactory;
import gameframework.core.CanvasDefaultImpl;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.core.GameUniverseViewPortDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.MoveStrategyRandom;
import gameframework.moves_rules.OverlapProcessor;
import gameframework.moves_rules.OverlapProcessorDefaultImpl;
import gameframework.moves_rules.OverlapRulesApplier;
import move.EnnemyUnitMovableDriver;
import move.GameMoveBlockerChecker;
import move.PlayerMoveDriver;
import move.UnitMoveBlockers;
import observers.KillObserver;
import overlap.GameOverlapRules;

public abstract class GameLevelAbstr extends ArmyGameLevel {


	private HashSet<GameUnit> player_units;
	private HashSet<GameUnit> enemy_units;
	private GameAbstractFactory unit_factory;
	private String background;
	
	protected abstract GameAbstractFactory createUnitFactory();

	public GameLevelAbstr(ArmyGame g, String background) {
		super(g);
		unit_factory = createUnitFactory();
		this.background = background;
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
			{ 1, 0, 0, 0, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static final int SPRITE_SIZE = 16;

	@Override
	protected void init() {
		MoveBlockerChecker moveBlockerChecker = new GameMoveBlockerChecker();
		OverlapProcessor overlap_proc = new OverlapProcessorDefaultImpl();
		OverlapRulesApplier rules = new GameOverlapRules();
		overlap_proc.setOverlapRules(rules);
		moveBlockerChecker.setMoveBlockerRules(new UnitMoveBlockers());

		universe = new ArmyGameUniverse(moveBlockerChecker, overlap_proc);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		gameBoard.setBackground(background);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		rules.setUniverse(universe);
		
		KillObserver kill_obs = new KillObserver(universe);
		
		//Sets of units (per team)
		player_units = new HashSet<>();
		enemy_units = new HashSet<>();
		
		// Rectangle used to select units with the mouse
		Selector selector = new Selector();
		universe.addGameEntity(selector);
		
		//Setup controller
		UnitController unit_controller = new UnitController(selector);
		canvas.addMouseListener(unit_controller);
		canvas.addMouseMotionListener(unit_controller);
		canvas.addKeyListener(unit_controller);
		
		GameUnitEntity game_unit_entity;
		
		//Instanciate regiments
		Regiment regiment_enemy = unit_factory.regiment("reg ennemy");
		Regiment[] player_regiments = new Regiment[3];
		for (int i=0; i< player_regiments.length; ++i){
			player_regiments[i]=unit_factory.regiment("regiment " + i);
		}
		
		//Create move drivers
		EnnemyUnitMovableDriver enemyDriv = new EnnemyUnitMovableDriver(player_units);
		PlayerMoveDriver playerDriver = new PlayerMoveDriver(enemy_units, moveBlockerChecker);


		for (int j = 0; j < tab.length; j++) {
			for (int i = 0; i < tab[j].length; i++) {
				
				//Create Missiles
				if (tab[j][i] == -1){
					GameThrowableWeapon projectile = unit_factory.throwable_weapon(canvas);
					projectile.setPosition(new Point(i * SPRITE_SIZE, j * SPRITE_SIZE));
					projectile.setMove_checker(moveBlockerChecker);
					projectile.addObserver(universe);
					universe.addGameEntity(projectile);
				}
				
				// Create Walls
				if (tab[j][i] == 1) {
					universe.addGameEntity(new Wall(canvas, i * SPRITE_SIZE, j * SPRITE_SIZE));
				}
				// create Enemies
				if (tab[j][i] == 2) {
					MoveStrategyRandom ranStr = new MoveStrategyRandom();
					enemyDriv.setStrategy(ranStr);
					enemyDriv.setmoveBlockerChecker(moveBlockerChecker);
					game_unit_entity = unit_factory.infantryUnit(canvas, "Enemy swordman");
					game_unit_entity.setDriver(enemyDriv);
					game_unit_entity.setPosition(new Point(i * SPRITE_SIZE, j * SPRITE_SIZE));
					game_unit_entity.setTeam(Enemy_team);
					enemy_units.add(game_unit_entity);

					game_unit_entity.add_game_obs(kill_obs);
					regiment_enemy.addUnit(game_unit_entity);
					universe.addGameEntity(game_unit_entity);

				}
				// Create Player Units
				else if (tab[j][i] >= 3){
					// create Swordman
					Regiment regiment = player_regiments[tab[j][i] - 3];
					game_unit_entity = unit_factory.playerInfantryUnit(canvas, "Player swordman");
					game_unit_entity.setDriver(playerDriver);
					game_unit_entity.setPosition(new Point(i * SPRITE_SIZE, j * SPRITE_SIZE));
					game_unit_entity.setTeam(Player_team);
					game_unit_entity.add_game_obs(kill_obs);
					player_units.add(game_unit_entity);
					regiment.addUnit(game_unit_entity);
					universe.addGameEntity(game_unit_entity);
				}
			}
		}
		universe.addGameEntity(regiment_enemy);
		regiment_enemy.add_game_obs(kill_obs);

		//Adding listeners and units to the regiments
		for (int i=0; i<player_regiments.length; ++i){
			Regiment regiment = player_regiments[i];
			universe.addGameEntity(regiment);
			unit_controller.addUnit(regiment);
			regiment.add_game_obs(kill_obs);
		}
		

	}
}
