package model;

public class Bishop extends Piece {

	public Bishop(PColor color, ChessModel model) {
		super(color, model);
	}

	@Override
	public void validTiles(int x, int y) {
		int ret;
		int c=x;
		int r=y;
		
		//diagonale gi�+destra
		r++;
		c++;
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			r++;
			c++;
		}
		
		//diagonale su+destra
		c=x;
		r=y;
		r--;
		c++;
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			r--;
			c++;
		}
		
		//diagonale su+sinistra
		c=x;
		r=y;
		r--;
		c--;
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			r--;
			c--;
		}
		
		//diagonale gi�+sinistra
		c=x;
		r=y;
		r++;
		c--;
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			r++;
			c--;
		}	
	}
	
}
