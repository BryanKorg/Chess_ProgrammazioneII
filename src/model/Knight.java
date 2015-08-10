package model;

import java.awt.Image;

public class Knight extends Piece {

	public Knight(PColor color, ChessModel model) {
		super(color, model);
	}

	@Override
	public void validTiles(int x, int y) {
		if(moveToValid(x+1,y+2)!=-1){myModel.Shine[x+1][y+2]=true;}
		if(moveToValid(x+1,y-2)!=-1){myModel.Shine[x+1][y-2]=true;}
		if(moveToValid(x+2,y-1)!=-1){myModel.Shine[x+2][y-1]=true;}
		if(moveToValid(x+2,y+1)!=-1){myModel.Shine[x+2][y+1]=true;}
		if(moveToValid(x-1,y-2)!=-1){myModel.Shine[x-1][y-2]=true;}
		if(moveToValid(x-1,y+2)!=-1){myModel.Shine[x-1][y+2]=true;}
		if(moveToValid(x-2,y-1)!=-1){myModel.Shine[x-2][y-1]=true;}
		if(moveToValid(x-2,y+1)!=-1){myModel.Shine[x-2][y+1]=true;}
	}
	
}
