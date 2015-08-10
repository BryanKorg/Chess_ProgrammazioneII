package controller;

import model.Model;

public interface Controller {
	
	/**
	 * Imposta il modello
	 * @param model
	 */
	void setModel(Model model);
	
	/**
	 * Metodo chiamato al click di un quadrato
	 * @param x riga
	 * @param y colonna
	 */
	void onClick(int x, int y);
}
