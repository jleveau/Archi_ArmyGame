package observers;

import entity.GameUnit;

public interface GameObserver<S extends GameUnit>{

	public void update_death(S s);
	
	public void update_target_reach(S s);

}
