package controller;

import java.awt.Point;
import java.awt.Rectangle;

public interface Selectable {
	public boolean isSelectedBy(Rectangle rect);
	public boolean isSelected();
	public void setSelected(boolean b);
	public void receive_order(Order order);
}
