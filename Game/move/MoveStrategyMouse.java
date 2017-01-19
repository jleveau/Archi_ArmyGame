package move;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

public class MoveStrategyMouse extends MouseAdapter implements MoveStrategy {

	protected SpeedVector speedVector = null;
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3){
			speedVector = new SpeedVectorDefaultImpl(new Point(e.getX(), e.getY()));
		}
	}

	public void resetSpeedVector(){
		speedVector = null;
	}

	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}

}
