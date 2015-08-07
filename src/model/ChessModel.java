package model;

import view.View;







public class ChessModel implements Model{
		

private View view;
private int holdx; //tiene traccia della x della pedina che si è scelto di muovere nella phase precedente di gioco (phase=true)
private int holdy;//tiene traccia della y " "
private int WKingx;//tiene traccia della x del Re bianco
private int WKingy;//""
private int BKingx;//tiene traccia della x del Re nero
private int BKingy;//""
private boolean turn; //indica quale giocatore tocca muovere
private boolean phase; //indica la fase di movimento 
private int positions[][]= new int [8][8];//matrice in cui sono memorizzate le posizione delle pedine, se =-1 allora la casella è vuota
boolean Shine[][]= new boolean[8][8];//matrice in cui vengono memorizzate le caselle valide per una mossa
boolean HoldShine[][]= new boolean[8][8];//matrice di supporto temporaneo per operazioni su Shine

	public ChessModel(){
		
		
	}
	
	public void InitialPosition(){
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				positions[i][j]=-1;
			}
		}
		
		//Neri
		positions[0][0]=8;
		positions[0][1]=10;
		positions[0][2]=9;
		positions[0][3]=6;
		positions[0][4]=7;
		positions[0][5]=9;
		positions[0][6]=10;
		positions[0][7]=8;
		positions[1][0]=11;
		positions[1][1]=11;
		positions[1][2]=11;
		positions[1][3]=11;
		positions[1][4]=11;
		positions[1][5]=11;
		positions[1][6]=11;
		positions[1][7]=11;
		//Bianchi
		positions[7][0]=2;
		positions[7][1]=4;
		positions[7][2]=3;
		positions[7][3]=0;
		positions[7][4]=1;
		positions[7][5]=3;
		positions[7][6]=4;
		positions[7][7]=2;
		positions[6][0]=5;
		positions[6][1]=5;
		positions[6][2]=5;
		positions[6][3]=5;
		positions[6][4]=5;
		positions[6][5]=5;
		positions[6][6]=5;
		positions[6][7]=5;
		WKingx=7;
		WKingy=3;
		BKingx=0;
		BKingy=3;
	
		
		
		view.Change(positions);
		
		view.Check(0,0,false);
		view.LightDown();
		turn=true;
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
			ShineValidated(x,y);
			if(FilterValidTiles()){ //talgo dalle caselle valide quelle che lascerebbero/metterebbero il re sotto scacco
				phase=false;//passa alla seconda fase  della mossa 
				//Chiede alla view di illuminare le caselle valide	
				view.LightUp(Shine);
			}
			
			}
		}else{
			//Codice della seconda fase di gioco
			if(!Shine[x][y]){
						view.LightDown();
			}else{
						//effettua la modifa sulla posizione delle pedine sulla matrice delle posizioni
						Swap(x,y);
						//prima di modificare definitivamente devo controllare se la mossa libera il re dallo scacco
				
						view.Change(positions);
						//chiede alla view di spegnere le caselle illuminate 
						view.LightDown();
						if(turn){view.Check(WKingx,WKingy,false);}else{view.Check(BKingx,BKingy,false);}
						ifPawnUpgrade(x,y);
						turn=!turn; //Passo il turno seguente
						
						
					
					
				//Prima di permettere all'altro giocatore di muovere, controllo se è sotto scacco
				
				
					if(IfCheck()){
							if(IfCheckMate()){
								view.ShowCheckMsg();
								
							}else{
								if(turn){ view.Check(WKingx,WKingy,true);
								}else{ view.Check(BKingx,BKingy,true);}
								 }
					}else{
							if(turn){view.Check(WKingx,WKingy,false);
							}else{view.Check(BKingx,BKingy,false);}
						 }
					
				
				
				
			    }
			
			phase=true; //torno alla phase iniziale della mossa
			
			}
		
		}
	
	//Controlla se la casella premuta è valida per l'esecuzione di una mossa
	private boolean IsValid(int x, int y){ 
		if(positions[x][y]==-1){
			return false;
		}
		if((turn==true && positions[x][y]>5) || (turn==false && positions[x][y]<6) ){
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

	
			
			
	private int Swap(int x,int y){//modifica la matrice delle posizioni
		int tmp;
		if(WKingx==holdx && WKingy==holdy){
			WKingx=x;
			WKingy=y;
			
		}else if(BKingx==holdx && BKingy==holdy){
			BKingx=x;
			BKingy=y;
			
		}
		
		
		tmp=positions[x][y];
		positions[x][y]=positions[holdx][holdy];
		positions[holdx][holdy]=-1;
		return tmp;
	}
	private void RollBack(int x,int y,int tmp){ //effettua la mossa contraria di Swap
		positions[holdx][holdy]=positions[x][y];
		positions[x][y]=tmp;
		
		if(positions[holdx][holdy]==0 ){
		
			WKingx=holdx;
			WKingy=holdy;
			
		}else if( positions[holdx][holdy]==6) {
			BKingx=holdx;
			BKingy=holdy;
		
	}
		
		
	}

	private int MoveToValid(int x,int y){ //determina il tipo di casella indicata da x,y
		//return 0 : Casella vuota, 1: Casella con pedina avversaria, -1: Casella invalida
		if(x<8 && y<8 && y>=0 && x>=0){ //se la casella è fuori dalla scacchiera la considero invalida
			if(positions[x][y]==-1){
				return 0;
			}else{
				if(turn){
					if(positions[x][y]>5){
						return 1; //devo segnalare che questa piastrella contiene una pedina avversaria
					}
				}else{
					if(positions[x][y]<6){
						return 1; //devo segnalare che questa piastrella contiene una pedina avversaria
					}
				}
		
			}
		}

		return -1; //la casella è invalida

}
	
	
	
	
	
	private  void ShineValidated(int x,int y){ //"Riempie" la matrice Shine nelle posizioni di spostamento 
											//valide a seconda del tipo di pedina
		switch(positions[x][y]){
			case 11:
				 ValidTilesPawn(x,y);
				break;
			case 8:
				ValidTilesRook(x,y);
				break;
			case 10:
				ValidTilesKnight(x,y);
				break;
			case 9:
				ValidTilesBishop(x,y);
				break;
			case 6:
				ValidTilesKing(x,y);
				break;
			case 7:
				ValidTilesQueen(x,y);
				break;
			case 5:
				ValidTilesPawn(x,y);
				break;
			case 2:
				ValidTilesRook(x,y);
				break;
			case 4:
				ValidTilesKnight(x,y);
				break;
			case 3:
				ValidTilesBishop(x,y);
				break;
			case 0:
				ValidTilesKing(x,y);
				break;
			case 1:
				ValidTilesQueen(x,y);
				break;
			default:
				
			}
		
										}
	
	
	
	
	
	private void ValidTilesPawn(int x, int y){
		//calcola le caselle valide per una mossa per il pedone che si trova nella casella indicata da x,y
		if(turn){
			
					if(MoveToValid(x-1,y)==0){
					Shine[x-1][y]=true;
					
					}
				
					if(MoveToValid(x-1,y+1)==1 ){
						Shine[x-1][y+1]=true;
						
					}
				
					if(MoveToValid(x-1,y-1)==1){
						Shine[x-1][y-1]=true;
					}
	
		
		}else{
			
			
				if(MoveToValid(x+1,y)==0){
				Shine[x+1][y]=true;
				
				}
			
				if(MoveToValid(x+1,y+1)==1 ){
				Shine[x+1][y+1]=true;
				}
			
				if(MoveToValid(x+1,y-1)==1){
				Shine[x+1][y-1]=true;
				}
			
			
			
			
			
			
			
		}
		
		
			
		
		}
	private void ValidTilesKnight(int x,int y){
		
		//calcola le caselle valide per una mossa per il cavallo che si trova nella casella indicata da x,y
		
			
				if(MoveToValid(x+1,y+2)!=-1){Shine[x+1][y+2]=true;}
				if(MoveToValid(x+1,y-2)!=-1){Shine[x+1][y-2]=true;}
				if(MoveToValid(x+2,y-1)!=-1){Shine[x+2][y-1]=true;}
				if(MoveToValid(x+2,y+1)!=-1){Shine[x+2][y+1]=true;}
				if(MoveToValid(x-1,y-2)!=-1){Shine[x-1][y-2]=true;}
				if(MoveToValid(x-1,y+2)!=-1){Shine[x-1][y+2]=true;}
				if(MoveToValid(x-2,y-1)!=-1){Shine[x-2][y-1]=true;}
				if(MoveToValid(x-2,y+1)!=-1){Shine[x-2][y+1]=true;}
				
			
			
			
		
		
	
	}
	
	private void ValidTilesRook(int x, int y){
		//calcola le caselle valide per una mossa per la torre che si trova nella casella indicata da x,y	
		
		int ret;
		int c=x;
		int r=y;
		//Verso destra
		c++;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			
			c++;
			
		}
		c=x;
		r=y;
		//Verso sinistra
		c--;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			c--;
		}
		//in su
		c=x;
		r=y;
		r--;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			r--;
		}
		//in giù
		c=x;
		r=y;
		r++;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			r++;
		}
		
		
	}
	public void ValidTilesBishop(int x,int y){
		//calcola le caselle valide per una mossa per l'alfiere che si trova nella casella indicata da x,y
		
		int ret;
		int c=x;
		int r=y;
		
		//diagonale giù+destra
		r++;
		c++;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			r++;
			c++;
			
		}
		//diagonale su+destra
		c=x;
		r=y;
		r--;
		c++;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			r--;
			c++;
			
		}
		//diagonale su+sinistra
		c=x;
		r=y;
		r--;
		c--;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			r--;
			c--;
			
		}
		
		//diagonale giù+sinistra
		c=x;
		r=y;
		r++;
		c--;
		while((ret=MoveToValid(c, r))!=-1){
			Shine[c][r]=true;
			if(ret==1){break;}
			r++;
			c--;
			
		}
		
		
	}
	private void ValidTilesKing(int x,int y){
		//calcola le caselle valide per una mossa per il re che si trova nella casella indicata da x,y
		if(MoveToValid(x+1,y+1)!=-1){Shine[x+1][y+1]=true;}
		if(MoveToValid(x+1,y-1)!=-1){Shine[x+1][y-1]=true;}
		if(MoveToValid(x-1,y+1)!=-1){Shine[x-1][y+1]=true;}
		if(MoveToValid(x-1,y-1)!=-1){Shine[x-1][y-1]=true;}
		if(MoveToValid(x,y-1)!=-1){Shine[x][y-1]=true;}
		if(MoveToValid(x,y+1)!=-1){Shine[x][y+1]=true;}
		if(MoveToValid(x+1,y)!=-1){Shine[x+1][y]=true;}
		if(MoveToValid(x-1,y)!=-1){Shine[x-1][y]=true;}
		
		
		
	}

	
	private void ValidTilesQueen(int x,int y){
		//calcola le caselle valide per una mossa per la regina che si trova nella casella indicata da x,y
		//Siccome la mossa della regina è la fusione delle mosse dell'alfiere e della torre
		//basta chiamare le funzioni che calcolano le caselle valide per alfiere e torre
		ValidTilesBishop(x,y);
		ValidTilesRook(x,y);
		
		
		
		
	}
	private boolean FilterValidTiles(){
		//filtra le caselle valide per una mossa togliendo dalle possibilità le caselle che non liberano
		//il re dallo scacco o che lo renderebbero sotto scacco
		int tmp;
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
		
		if((x==0 && positions[x][y]==5)||(x==7 && positions[x][y]==11)){
			view.PawnUpgrade(x,y,turn);
			}
	
}

public void SetUpgradedPawn(int x,int y,int value){//setta la pedina con cui è stato promosso il pedone
	positions[x][y]=value;
	view.Change(positions);
}
	
	
	private boolean IfCheck(){//Controlla sel il giocatore chiamante è sotto scacco
		ResetShineMatrix();
	
			
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					
				if(turn){
					if(positions[i][j]>5){
						turn=false;
						ShineValidated(i,j);
						turn=true;
						if(Shine[WKingx][WKingy]){
							
							return true;
						}
						
					}
				}else{
					if(positions[i][j]<6 && positions[i][j]>=0 ){
						
						turn=true;
						ShineValidated(i,j);
						turn=false;
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
		int tmp;
		if(turn){
		for(int i=7;i>=0;i--){
			for(int j=7;j>=0;j--){
				
				if(positions[i][j]<6){
					ResetShineMatrix();
					ShineValidated(i,j);
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
					
					if(positions[i][j]>5){
						ResetShineMatrix();
						ShineValidated(i,j);
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


	
	
	

