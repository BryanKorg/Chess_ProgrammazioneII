package model;

/**
 * Classe astratta per definire un pezzo
 */
public abstract class Piece {
	protected PColor color;
	//int nome; 			//TODO Da implementare con Enum? e a cosa serve?
	protected ChessModel myModel; 
	
	/**
	 * Crea un pezzo 
	 * @param color colore del pezzo
	 * @param model il {@link ChessModel} principale
	 */
	public Piece(PColor color,ChessModel model){
		this.color=color;
		this.myModel=model;
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
	
	//TODO Utilizziamo anche qui enumeratori? square tipo: EMPTY, ENEMY e INVALID?
	/**
	 * Determina il tipo di casella indicata da x,y
	 * @param x riga
	 * @param y colonna
	 * @return 0 : Casella vuota, 1: Casella con pedina avversaria, -1: Casella invalida
	 */
	protected int moveToValid(int x,int y){
		if(x<8 && y<8 && y>=0 && x>=0){ //se la casella � fuori dalla scacchiera la considero invalida
			if(myModel.pieces[x][y]==null){
					return 0;
			}else{			
				if(myModel.currTurn!=myModel.pieces[x][y].getColor()){
					return 1; // Pedina nemica. guardare ChessModel.MoveToValid-> Fa la stessa cosa (credo)
				}
			}
		}
		return -1; //la casella � invalida	
	}
	
}