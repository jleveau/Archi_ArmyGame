package entity;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import soldier.core.Unit;

public interface GameUnitItf extends Unit, Selectable, GameEntity, TeamUnit, Target{

}
