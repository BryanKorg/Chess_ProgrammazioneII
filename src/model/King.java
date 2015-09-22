package model;

public class King extends Piece {
	
	public King(PColor color, ChessModel model) {
		super(color, model,0);
	}

	@Override
	public void validTiles(int x, int y) {
		if(moveToValid(x+1,y+1)!=-1){myModel.Shine[x+1][y+1]=true;}
		if(moveToValid(x+1,y-1)!=-1){myModel.Shine[x+1][y-1]=true;}
		if(moveToValid(x-1,y+1)!=-1){myModel.Shine[x-1][y+1]=true;}
		if(moveToValid(x-1,y-1)!=-1){myModel.Shine[x-1][y-1]=true;}
		if(moveToValid(x,y-1)!=-1){myModel.Shine[x][y-1]=true;}
		if(moveToValid(x,y+1)!=-1){myModel.Shine[x][y+1]=true;}
		if(moveToValid(x+1,y)!=-1){myModel.Shine[x+1][y]=true;}
		if(moveToValid(x-1,y)!=-1){myModel.Shine[x-1][y]=true;}
	}
	
}
