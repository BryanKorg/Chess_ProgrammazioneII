package model;

public class Queen extends Piece  {

	public Queen(PColor color, ChessModel model) {
		super(color, model);
	}

	@Override
	public void validTiles(int x, int y) {
		validTilesDiagonal(x, y);
		validTilesCross(x, y);
	}
	
	/**
	 * Valida le caselle in diagonale
	 * @param x riga
	 * @param y colonna
	 */
	private void validTilesDiagonal(int x,int y){
		int ret;
		int c=x;
		int r=y;
		
		//TODO rifare gli assegnamenti tipo quelli su Rook?
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
	
	/**
	 * Valida le caselle orizzontalmente e verticalmente
	 * @param x riga
	 * @param y colonna
	 */
	private void validTilesCross(int x, int y){
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
