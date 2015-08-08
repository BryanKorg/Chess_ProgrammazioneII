package model;

/**
 * Classe astratta per definire un pezzo
 */
public abstract class Piece {
	PColor color;
	int nome; 			//TODO Da implementare con Enum? e a cosa serve?
	
	/**
	 * Crea un pezzo 
	 * @param color colore del pezzo
	 */
	public Piece(PColor color){
		this.color=color;
	}
	
	/**
	 * Calcola le caselle valide per una mossa per il pezzo nella casella indicata da x, y
	 * @param x riga
	 * @param y colonna
	 */
	abstract public void validTiles(int x, int y);
	
	/**
	 * Ottiene il colore del pezzo
	 * @return colore di tipo {@link PColor}
	 */
	public PColor getColor(){
		return color;
	}
	
	//TODO Utilizziamo anche qui enumeratori? tipo: EMPTY, ENEMY e INVALID?
	/**
	 * Determina il tipo di casella indicata da x,y
	 * @param piece
	 * @param color
	 * @param x
	 * @param y
	 * @return 0 : Casella vuota, 1: Casella con pedina avversaria, -1: Casella invalida
	 */
	protected int moveToValid(Piece piece,PColor color,int x,int y){
		if(x<8 && y<8 && y>=0 && x>=0){ //se la casella Ã¨ fuori dalla scacchiera la considero invalida
			if(piece==null){
					return 0;
			}else{			
				if(piece.getColor()!=this.color){
					return 1; //guardare ChessModel.MoveToValid-> Fa la stessa cosa (credo)
				}
			}
		}
		return -1; //la casella è invalida	
	}
}
