import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import gameframework.core.CanvasDefaultImpl;
import gameframework.core.Game;
import gameframework.core.GameLevel;
import gameframework.core.ObservableValue;

public class ArmyGame implements Game, Observer{
	protected static final int NB_ROWS = 31;
	protected static final int NB_COLUMNS = 28;
	protected static final int SPRITE_SIZE = 16;
	public static int WIDTH;
	public static int HEIGHT;
	public static final int MAX_NUMBER_OF_PLAYER = 1;
	

	protected CanvasDefaultImpl defaultCanvas = null;

	// initialized before each level
	protected ObservableValue<Boolean> endOfGame = null;

	private Frame f;

	private ArmyGameLevel currentPlayedLevel = null;
	protected int levelNumber;
	protected ArrayList<GameLevel> gameLevels;
	protected int current_player;

	protected Label information;
	protected Label informationValue;
	protected Label currentLevel;
	protected Label currentLevelValue;
	private String game_type;

	public ArmyGame() {
		HEIGHT = SPRITE_SIZE * NB_ROWS;
		WIDTH = SPRITE_SIZE * NB_COLUMNS;
		currentLevel = new Label("Level:");
		current_player = 0;
		createGUI();
	}

	public void createGUI() {
		f = new Frame("Army Game");
		f.dispose();

		createMenuBar();
		Container c = createStatusBar();

		defaultCanvas = new CanvasDefaultImpl();
		defaultCanvas.setSize(SPRITE_SIZE * NB_COLUMNS, SPRITE_SIZE * NB_ROWS);
		f.add(defaultCanvas);
		f.add(c, BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("file");
		MenuItem start_medieval = new MenuItem("new game medieval");
		MenuItem start_space = new MenuItem("new game spacial");
		MenuItem save = new MenuItem("save");
		MenuItem restore = new MenuItem("load");
		MenuItem quit = new MenuItem("quit");
		Menu game = new Menu("game");
		MenuItem pause = new MenuItem("pause");
		MenuItem resume = new MenuItem("resume");
		menuBar.add(file);
		menuBar.add(game);
		f.setMenuBar(menuBar);
		
		start_medieval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		start_space.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		restore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restore();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resume();
			}
		});

		file.add(start_medieval);
		file.add(start_space);
		file.add(save);
		file.add(restore);
		file.add(quit);
		game.add(pause);
		game.add(resume);
	}

	private Container createStatusBar() {
		JPanel c = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		c.setLayout(layout);
		currentLevelValue = new Label(Integer.toString(levelNumber));
		c.add(currentLevel);
		c.add(currentLevelValue);
		return c;
	}

	public Canvas getCanvas() {
		return defaultCanvas;
	}

	public void start() {

		levelNumber = 0;
		for (GameLevel level : gameLevels) {
			endOfGame = new ObservableValue<Boolean>(false);
			endOfGame.addObserver(this);
			try {
				if (currentPlayedLevel != null && !currentPlayedLevel.isEnded()) {
					currentPlayedLevel.interrupt();
					currentPlayedLevel = null;
				}
				currentPlayedLevel = (ArmyGameLevel) level;
				levelNumber++;
				currentLevelValue.setText(Integer.toString(levelNumber));
				currentPlayedLevel.start();
				currentPlayedLevel.join();
			} catch (Exception e) {
			}
		}

	}

	public void restore() {
		System.out.println("restore(): Unimplemented operation");
	}

	public void save() {
		System.out.println("save(): Unimplemented operation");
	}

	public void pause() {
		System.out.println("pause(): Unimplemented operation");
		// currentPlayedLevel.suspend();
	}

	public void resume() {
		System.out.println("resume(): Unimplemented operation");
		// currentPlayedLevel.resume();
	}
	
	public int getCurrentPlayer(){
		return current_player;
	}


	public ObservableValue<Boolean> endOfGame() {
		return endOfGame;
	}

	public void setLevels(ArrayList<GameLevel> levels) {
		gameLevels = levels;
	}

	public void update(Observable o, Object arg) {
		if (o == endOfGame) {
			if (endOfGame.getValue()) {
				informationValue.setText("You win");
				currentPlayedLevel.interrupt();
				currentPlayedLevel.end();
			}
		}
	}

	@Override
	public ObservableValue<Integer>[] score() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Integer>[] life() {
		// TODO Auto-generated method stub
		return null;
	}

}
