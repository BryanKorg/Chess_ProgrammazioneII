
package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.ImageIcon;

import view.View;

public class ChessModel implements Model{
	private Image images[]= new Image[12];	
	private View view;
	private int holdx; //tiene traccia della x della pedina che si è scelto di muovere nella phase precedente di gioco (phase=true)
	private int holdy;//tiene traccia della y " "
	private int WKingx;//tiene traccia della x del Re bianco
	private int WKingy;//""
	private int BKingx;//tiene traccia della x del Re nero
	private int BKingy;//""
	protected PColor currTurn; //Indica il colore del turno corrente
	private boolean phase; //indica la fase di movimento 
	protected Piece pieces[][]= new Piece [8][8];//matrice in cui sono memorizzate le pedine delle pedine, se null allora la casella è vuota
	protected boolean Shine[][]= new boolean[8][8];//matrice in cui vengono memorizzate le caselle valide per una mossa
	private boolean HoldShine[][]= new boolean[8][8];//matrice di supporto temporaneo per operazioni su Shine

	public ChessModel(){	
	}
	
	public void initChessboard(){
		//Inizializzo la matrice a null
		pieces= new Piece [8][8];
		//Neri
		pieces[0][0]=new Rook(PColor.BLACK,this);
		pieces[0][1]=new Knight(PColor.BLACK,this);
		pieces[0][2]=new Bishop(PColor.BLACK,this);
		pieces[0][3]=new King(PColor.BLACK,this);
		pieces[0][4]=new Queen(PColor.BLACK,this);
		pieces[0][5]=new Bishop(PColor.BLACK,this);
		pieces[0][6]=new Knight(PColor.BLACK,this);
		pieces[0][7]=new Rook(PColor.BLACK,this);
		pieces[1][0]=new Pawn(PColor.BLACK,this);
		pieces[1][1]=new Pawn(PColor.BLACK,this);
		pieces[1][2]=new Pawn(PColor.BLACK,this);
		pieces[1][3]=new Pawn(PColor.BLACK,this);
		pieces[1][4]=new Pawn(PColor.BLACK,this);
		pieces[1][5]=new Pawn(PColor.BLACK,this);
		pieces[1][6]=new Pawn(PColor.BLACK,this);
		pieces[1][7]=new Pawn(PColor.BLACK,this);
		//Bianchi
		pieces[7][0]=new Rook(PColor.WHITE,this);
		pieces[7][1]=new Knight(PColor.WHITE,this);
		pieces[7][2]=new Bishop(PColor.WHITE,this);
		pieces[7][3]=new King(PColor.WHITE,this);
		pieces[7][4]=new Queen(PColor.WHITE,this);
		pieces[7][5]=new Bishop(PColor.WHITE,this);
		pieces[7][6]=new Knight(PColor.WHITE,this);
		pieces[7][7]=new Rook(PColor.WHITE,this);
		pieces[6][0]=new Pawn(PColor.WHITE,this);
		pieces[6][1]=new Pawn(PColor.WHITE,this);
		pieces[6][2]=new Pawn(PColor.WHITE,this);
		pieces[6][3]=new Pawn(PColor.WHITE,this);
		pieces[6][4]=new Pawn(PColor.WHITE,this);
		pieces[6][5]=new Pawn(PColor.WHITE,this);
		pieces[6][6]=new Pawn(PColor.WHITE,this);
		pieces[6][7]=new Pawn(PColor.WHITE,this);
		WKingx=7;
		WKingy=3;
		BKingx=0;
		BKingy=3;
	
		view.initialPosition(pieces);
		
		view.check(0,0,false);
		view.lightDown();
		currTurn=PColor.WHITE;
		phase=true;
	}
	
	@Override
	public void setView(View view) {		
		this.view=view;	
	}

	@Override
	public void MoveCheck(int x, int y) {
		if(phase){
			if(IsValid(x,y)){//Controlla se la caselle premuta è valida per una mossa
				//Resetta le matrice delle caselle valide per una mossa
				ResetShineMatrix();
				//Memorizza le coordinate della casella che si intende spostare
				holdx=x;
				holdy=y;
				//Determina le caselle valide per una mossa
				pieces[x][y].validTiles(x, y);
				
				if(FilterValidTiles()){ //talgo dalle caselle valide quelle che lascerebbero/metterebbero il re sotto scacco
					phase=false;//passa alla seconda fase  della mossa 
					//Chiede alla view di illuminare le caselle valide	
					view.lightUp(Shine);
				}
			}
		}else{
			//Codice della seconda fase di gioco
			if(!Shine[x][y]){
						view.lightDown();
			}else{
				//effettua la modifa sulla posizione delle pedine sulla matrice delle posizioni
				Swap(x,y);
				view.change(pieces);
				//chiede alla view di spegnere le caselle illuminate 
				view.lightDown();
				if(currTurn==PColor.WHITE){view.check(WKingx,WKingy,false);}else{view.check(BKingx,BKingy,false);}
				ifPawnUpgrade(x,y);
				if(currTurn==PColor.WHITE){currTurn=PColor.BLACK;}else{currTurn=PColor.WHITE;}
				//Passo il turno seguente
			}

				//Prima di permettere all'altro giocatore di muovere, controllo se è sotto scacco
				if(IfCheck()){
					if(IfCheckMate()){
						view.showCheckMsg();	
					}else{
						if(currTurn==PColor.WHITE){
							view.check(WKingx,WKingy,true);
							
						}else{
							view.check(BKingx,BKingy,true);
						}
					}
				}else{
					if(currTurn==PColor.WHITE){
						view.check(WKingx,WKingy,false);
					}else{
						view.check(BKingx,BKingy,false);
					}
				}
				phase=true; //torno alla phase iniziale della mossa
			}
			
		}	
		
		
	
	
	/**
	 * Controlla se la casella premuta � valida per l'esecuzione di una mossa
	 * @param x riga
	 * @param y colonna
	 * @return {@link Boolean} true se valido, altrimenti false
	 */
	private boolean IsValid(int x, int y){ 
		if(pieces[x][y]==null){
			return false;
		}
		if(pieces[x][y].getColor()!=currTurn){
			return false;	
		}
		return true;
	}
	
	private void ResetShineMatrix(){
		//Resetta la matrice delle caselle valide
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						Shine[i][j]=false;
					}
				}
	}

	
			
			
	private Piece Swap(int x,int y){//modifica la matrice delle posizioni
		Piece tmp;
		//devo controllare se ho messo un re e in caso affermatico modificare gli attributi delle coorinate
		if(WKingx==holdx && WKingy==holdy){
			WKingx=x;
			WKingy=y;
			
		}else if(BKingx==holdx && BKingy==holdy){
			BKingx=x;
			BKingy=y;
			
		}
		
		
		tmp=pieces[x][y];
		pieces[x][y]=pieces[holdx][holdy];
		pieces[holdx][holdy]=null;
		return tmp; //devo tenere traccua di positions[x][y] perchè necessario in caso di RollBack
	}
	private void RollBack(int x,int y,Piece tmp){ //effettua la mossa contraria di Swap
		pieces[holdx][holdy]=pieces[x][y];
		pieces[x][y]=tmp;
		
		if(pieces[holdx][holdy] instanceof King && pieces[holdx][holdy].getColor()==PColor.WHITE){
		
			WKingx=holdx;
			WKingy=holdy;
			
		}else if(pieces[holdx][holdy] instanceof King && pieces[holdx][holdy].getColor()==PColor.BLACK) {
			BKingx=holdx;
			BKingy=holdy;
		
	}	
}


	
	private boolean FilterValidTiles(){
		//filtra le caselle valide per una mossa togliendo dalle possibilità le caselle che non liberano
		//il re dallo scacco o che lo renderebbero sotto scacco
		Piece tmp;
		int count=0;
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(Shine[i][j]){
					count++; //il contatore sever a determinare se vi è almeno una casella valida(vedi sotto)
					tmp=Swap(i,j);
					HoldShine=CopyBooleanMatrix(Shine);
					if(IfCheck()){
						HoldShine[i][j]=false;
						count--;
					}
					Shine=CopyBooleanMatrix(HoldShine);
					RollBack(i,j,tmp);
						}
				}
			}
			
			if(count>0){return true;}else{return false;} //true se c'è almeno una casella valida dove spostare la pedina in quesitone
		
	}
	
private void  ifPawnUpgrade(int x,int y){//determina se un pedone ha ragiunto la casella di promozione
										//in caso affermativo chiama la funzione di promozione
		
		if((pieces[x][y] instanceof Pawn && x==0)||(pieces[x][y] instanceof Pawn && x==7)){
			view.pawnUpgrade(x,y,currTurn);
			}
	
}

public void SetUpgradedPawn(int x,int y,Piece piece){//setta la pedina con cui è stato promosso il pedone
	pieces[x][y]=piece;
	view.change(pieces);
}
	
	
	private boolean IfCheck(){//Controlla sel il giocatore chiamante è sotto scacco
		ResetShineMatrix();
	
			
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					
				if(currTurn==PColor.WHITE){
					if(pieces[i][j]!=null && pieces[i][j].getColor()==PColor.BLACK){
						currTurn=PColor.BLACK;
						pieces[i][j].validTiles(i, j);
						currTurn=PColor.WHITE;
						if(Shine[WKingx][WKingy]){
							
							return true;
						}
						
					}
				}else{
					if(pieces[i][j]!=null && pieces[i][j].getColor()==PColor.WHITE){
						
						currTurn=PColor.WHITE;
						pieces[i][j].validTiles(i, j);
						currTurn=PColor.BLACK;
						if(Shine[BKingx][BKingy]){
							
							return true;
						}
						
						
					}
				}
				
			}
			
				
		}
		
		return false;
	}
	
	
	private boolean IfCheckMate(){
		Piece tmp;
		if(currTurn==PColor.WHITE){
		for(int i=7;i>=0;i--){
			for(int j=7;j>=0;j--){
				
				if(pieces[i][j]!=null && pieces[i][j].getColor()==PColor.WHITE){
					ResetShineMatrix();
					pieces[i][j].validTiles(i, j);
					for(int k=0;k<8;k++){
						for(int h=0;h<8;h++){
							if(Shine[k][h]){
								holdx=i;
								holdy=j;
								tmp=Swap(k,h);
								HoldShine=CopyBooleanMatrix(Shine);
								if(!IfCheck()){
								RollBack(k,h,tmp);
								return false;
								}
									
								Shine=CopyBooleanMatrix(HoldShine);
								RollBack(k,h,tmp);
								
							}
						}
					}
					
				}
				
				
			}
		}
		
		}else{
			
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					
					if(pieces[i][j]!=null && pieces[i][j].getColor()==PColor.BLACK){
						ResetShineMatrix();
						pieces[i][j].validTiles(i, j);
						for(int k=0;k<8;k++){
							for(int h=0;h<8;h++){
								if(Shine[k][h]){
									holdx=i;
									holdy=j;
									tmp=Swap(k,h);
									HoldShine=CopyBooleanMatrix(Shine);
									if(!IfCheck()){
									RollBack(k,h,tmp);
									return false;
									}
										
									Shine=CopyBooleanMatrix(HoldShine);
									RollBack(k,h,tmp);
									
								}
							}
						}
						
					}
					
					
				}
			}
			
			
			
			
			
			
		}
		
		
		
		return true;
		
	}
	
	
	public static boolean[][] CopyBooleanMatrix(boolean [][] input) {//copia una matrice booleana
	    if (input == null)
	        return null;
	    boolean[][] result = new boolean[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}




	
	
	
	
	}


