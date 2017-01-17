package entity;

import java.awt.Rectangle;

public interface Selectable {
	public Rectangle getSelectBox();
	public boolean isSelected();
	public void setSelected(boolean b);
}
