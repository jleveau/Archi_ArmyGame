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
	
	public GameSwordMan infantryUnit(Canvas canvas, String name) {
		return new GameSwordMan(canvas, f.infantryUnit(name));
	}



}
