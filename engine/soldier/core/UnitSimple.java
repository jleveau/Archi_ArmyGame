/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Université Bordeaux.
 */
package soldier.core;

import java.util.Collections;
import java.util.Iterator;

import observer_util.ObservableAbstract;

public abstract class UnitSimple extends ObservableAbstract<Unit> implements Unit {

	private BehaviorSoldier behavior;
	private String name;

	public UnitSimple(String name, BehaviorSoldier behavior) {
		this.behavior = behavior;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getHealthPoints() {
		return behavior.getHealthPoints();
	}

	@Override
	public float getMaxHealthPoints() {
		return behavior.getMaxHealthPoints();
	}

	@Override
	public boolean alive() {
		return behavior.alive();
	}

	@Override
	public void heal() {
		behavior.heal();
	}

	@Override
	public float parry(float force) {
		float result = behavior.parry(force);
		notifyObservers(this);
		return result;
	}

	@Override
	public float strike() {
		float result = behavior.strike();
		return result;
	}

	@Override
	public void addEquipment(Weapon w) {
		behavior = w.createExtension(behavior);
	}

	@Override
	public void removeEquipment(Weapon w) {

		BehaviorSoldier current = behavior;
		BehaviorSoldier previous = behavior;
		while (current instanceof BehaviorExtension && ((BehaviorExtension) current).getOwner() != w) {
			previous = current;
			current = ((BehaviorExtension) current).parent();
		}
		if (((BehaviorExtension) current).getOwner() == w) {
			BehaviorSoldier new_parent = ((BehaviorExtension) previous).parent();
			// Adding this test to be able to remove the weapon if its the only extension
			if (!(((BehaviorExtension) current).parent() instanceof BehaviorExtension)) {
				behavior = ((BehaviorExtension) current).parent();
			} else {
				((BehaviorExtension) previous).reparent(new_parent);
			}
		}
	}

	@Override
	public Iterator<Weapon> getWeapons() {
		return new Iterator<Weapon>() {
			BehaviorSoldier current = behavior;

			@Override
			public boolean hasNext() {
				return current instanceof BehaviorExtension;
			}

			@Override
			public Weapon next() {
				Weapon tmp = ((BehaviorExtension) current).getOwner();
				current = ((BehaviorExtension) current).parent();
				return tmp;
			}
		};
	}

	@Override
	final public Iterator<Unit> subUnits() {
		return Collections.emptyIterator();
	}

	@Override
	final public void addUnit(Unit au) {
		throw new UnsupportedOperationException();
	}

	@Override
	final public void removeUnit(Unit au) {
	}

	public int nbWeapons() {
		int result = 0;
		for (Iterator<Weapon> it = getWeapons(); it.hasNext(); it.next())
			++result;
		return result;
	}
}
