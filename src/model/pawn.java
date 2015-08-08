package model;

/**
 * Classe per il pedone
 */
public class pawn extends Piece {

	public pawn(PColor color, ChessModel model) {
		super(color, model);
	}

	@Override
	public void validTiles(int x, int y) {
		if(myModel.currTurn==PColor.WHITE){	
			if(moveToValid(x-1,y)==0)
				myModel.Shine[x-1][y]=true;
			if(moveToValid(x-1,y+1)==1 )
				myModel.Shine[x-1][y+1]=true;
			if(moveToValid(x-1,y-1)==1)
				myModel.Shine[x-1][y-1]=true;
		}else{
			if(moveToValid(x+1,y)==0)
				myModel.Shine[x+1][y]=true;
			if(moveToValid(x+1,y+1)==1 )
				myModel.Shine[x+1][y+1]=true;
			if(moveToValid(x+1,y-1)==1)
				myModel.Shine[x+1][y-1]=true;
		}
	}
	
}
