import java.awt.Canvas;
import java.util.Date;

import gameframework.core.GameLevel;
import gameframework.core.GameUniverse;
import gameframework.core.GameUniverseViewPort;
import gameframework.core.GameUniverseViewPortDefaultImpl;
import gameframework.core.ObservableValue;

public abstract class ArmyGameLevel extends Thread implements GameLevel{
	private static final int MINIMUM_DELAY_BETWEEN_GAME_CYCLES = 100;
	boolean stopGameLoop;
	Canvas canvas;
	protected ArmyGame g;
	protected ArmyGameUniverse universe;
	protected GameUniverseViewPortDefaultImpl gameBoard;

	protected ObservableValue<Integer> enemy_units[];
	protected ObservableValue<Integer> player_units[];

	protected ObservableValue<Boolean> endOfGame;
	
	protected static int Player_team = 0;
	protected static int Enemy_team = 1;
	
	protected abstract void init();
	

	public ArmyGameLevel(ArmyGame g) {
		this.g = g;
		
		canvas = g.getCanvas();
	}

	public boolean isEnded() {
		return false;
	}
	
	@Override
	public void start() {  
		endOfGame = g.endOfGame();

		init();
		super.start();

		try {
			super.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		stopGameLoop = false;
		// main game loop 
		long start;

		while (!stopGameLoop && !this.isInterrupted()) {
			start = new Date().getTime();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();
			try {
				long sleepTime = MINIMUM_DELAY_BETWEEN_GAME_CYCLES
						- (new Date().getTime() - start);
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {
			}
		}
	}

	public void end() {
		stopGameLoop = true;
	}

	protected void overlap_handler() {
	}



	
}
