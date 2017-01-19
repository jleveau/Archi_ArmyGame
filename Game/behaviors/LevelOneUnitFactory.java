package behaviors;

import java.awt.Canvas;

import entity.GameSwordMan;
import entity.GameUnit;
import soldier.ages.AgeMiddleFactory;

public class LevelOneUnitFactory extends GameAbstractFactory{

	private AgeMiddleFactory f;
	
	public LevelOneUnitFactory(Canvas c){
		super(c);
		f = new AgeMiddleFactory();
	}
	
	public GameUnit infantryUnit(Canvas canvas, String name) {
		return new GameUnit(new GameSwordMan(canvas), f.infantryUnit(name));
	}



}
