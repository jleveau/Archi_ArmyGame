package move;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import gameframework.core.Movable;
import gameframework.moves_rules.IntersectTools;
import gameframework.moves_rules.MoveBlocker;
import gameframework.moves_rules.MoveBlockerCheckerDefaultImpl;
import gameframework.moves_rules.MoveBlockerRulesApplier;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;
import gameframework.moves_rules.SpeedVector;

public class GameMoveBlockerChecker extends MoveBlockerCheckerDefaultImpl{
	private ConcurrentLinkedQueue<MoveBlocker> moveBlockers;
	private MoveBlockerRulesApplier moveBlockerRuleApplier;

	public GameMoveBlockerChecker() {
		moveBlockers = new ConcurrentLinkedQueue<MoveBlocker>();
		this.moveBlockerRuleApplier = new MoveBlockerRulesApplierDefaultImpl();
	}
	
	public void addMoveBlocker(MoveBlocker p) {
		moveBlockers.add(p);
	}

	public void removeMoveBlocker(MoveBlocker p) {
		moveBlockers.remove(p);
	}

	public void setMoveBlockerRules(MoveBlockerRulesApplier moveBlockerRules) {
		this.moveBlockerRuleApplier = moveBlockerRules;
	}

	public boolean moveValidation(Movable m, SpeedVector mov) {
		Vector<MoveBlocker> moveBlockersInIntersection = new Vector<MoveBlocker>();
		Rectangle bound_box = m.getBoundingBox();
		Rectangle next_bound_box = new Rectangle(
				bound_box.x + mov.getDirection().x * mov.getSpeed(),
				bound_box.y + mov.getDirection().y * mov.getSpeed(),
				bound_box.width,
				bound_box.height);

		for (MoveBlocker moveBlocker : moveBlockers) {
			Rectangle bound_box_2 = moveBlocker.getBoundingBox();
			if (next_bound_box.intersects(bound_box_2)) {
				moveBlockersInIntersection.add(moveBlocker);
			}
		}
		if (!moveBlockersInIntersection.isEmpty()) {
			return moveBlockerRuleApplier.moveValidationProcessing(
					moveBlockersInIntersection, m);
		}
		return true;
	}
}
