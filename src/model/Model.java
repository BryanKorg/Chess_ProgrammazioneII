package model;

import view.View;

public interface Model {

	
	/**
	 * Commento a caso
	 * @param x
	 * @param y
	 */
	public void MoveCheck(int x,int y);
	public void setView(View view);
	public void SetUpgradedPawn(int x,int y,int value);
}
