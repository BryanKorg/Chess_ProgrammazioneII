package model;

public class Rook extends Piece {

	public Rook(PColor color, ChessModel model) {
		super(color, model,4);
	}

	@Override
	public void validTiles(int x, int y) {
		int ret;
		int c=x;
		int r=y;
		
		//Verso destra
		c++; //aggiungo 1 a c
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}	
			c++;
		}

		//Verso sinistra
		c=x-1; //resetto c e tolgo uno
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			c--;
		}

		//in su
		c=x; //resetto c
		r--; //tolgo 1 a r
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			r--;
		}

		//in gi�
		r=y+1; //resetto r e aggiungo 1
		while((ret=moveToValid(c, r))!=-1){
			myModel.Shine[c][r]=true;
			if(ret==1){break;}
			r++;
		}
	}
	
}
