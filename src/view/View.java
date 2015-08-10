package view;

import controller.Controller;
import model.Model;
import model.PColor;
import model.Piece;

public interface View {
	
	/**
	 * Ottiene il modello della view
	 * @return {@link Model}
	 */
	public Model getModel();
	
	/**
	 * Setta il {@link Controller}
	 * @param controller
	 */
	public void setController(Controller controller);
	
	/**
	 * Applica le modifiche alla view
	 * @param pieces matrice di {@link Piece}
	 */
	public void change( Piece[][] pieces);
	
	/**
	 * Evidenzia le caselle raggiungibili dal pezzo selezionato
	 * @param Pos
	 */
	public void lightUp(boolean Pos[][]);
	
	/**
	 * Elimina le evidenziature
	 */
	public void lightDown();
	
	/**
	 * Evidenzia il re sotto scacco
	 * @param x
	 * @param y
	 * @param stato
	 */
	public void check(int x,int y,boolean stato);
	
	/**
	 * Avviso di scacco matto
	 */
	public void showCheckMsg();
	
	/**
	 * Richiede l'upgrade del pedone
	 * @param x riga
	 * @param y colonna
	 * @param currTurn colore del pedone
	 */
	public void pawnUpgrade(int x,int y,PColor currTurn);
	
	/**
	 * Inizializza la scacchiera
	 * @param pieces
	 */
	public void initialPosition(Piece pieces[][]);
	
}
