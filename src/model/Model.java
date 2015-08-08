package model;

import view.View;

public interface Model {

	
	/**
	 * Effettua un cambiamento date le coordinate del quadrato scelto
	 * @param x riga
	 * @param y colonna
	 */
	
	public void MoveCheck(int x,int y);
	
	/**
	 * Setta la view
	 * @param view
	 */
	public void setView(View view);
	
	/**
	 * Setta il pezzo per fare la promozione del pedone
	 * @param x
	 * @param y
	 * @param value
	 */
	public void SetUpgradedPawn(int x,int y,int value);  //TODO deve essere di tipo piece?
}
