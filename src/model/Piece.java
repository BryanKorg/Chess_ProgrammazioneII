package model;

public abstract class Piece {
PColor color; //Da implementare con Enum?
int nome;

public PColor getColor(){
	return color;
}
public int MoveToValid(Piece piece,PColor color,int x,int y){
	//determina il tipo di casella indicata da x,y
			//return 0 : Casella vuota, 1: Casella con pedina avversaria, -1: Casella invalida
			if(x<8 && y<8 && y>=0 && x>=0){ //se la casella è fuori dalla scacchiera la considero invalida
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
