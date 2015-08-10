package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Bishop;
import model.ChessModel;
import model.King;
import model.Knight;
import model.Model;
import model.PColor;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;
import controller.Controller;
public class ChessBoard extends JPanel implements View {

	

private  Controller controller;

private Tile tiles[][]= new Tile[8][8];
private Image images[]= new Image[12];
private JFrame FRAME;
private Model model;
	public ChessBoard(JFrame FRAME,Model model){
		
	this.model=model;
	this.FRAME=FRAME;
	setLayout(new GridLayout(8,8));
	//Carico in memoria le immagini
	LoadImages();
	//Crea caselle
	CreateTiles();
	//Metti le pedine nella configurazione iniziale
	//InitialPosition();
	CreateMenu();
	
	}



private void CreateMenu(){
	JMenuBar menubar;
	JMenu menu;
	
	//Create the menu bar.
	menubar = new JMenuBar();

	//Build the first menu.
	menu = new JMenu("Chess");
	
	

	menu.setMnemonic(KeyEvent.VK_F);
	JMenuItem eMenuItem = new JMenuItem("New Game");
	
	eMenuItem.setMnemonic(KeyEvent.VK_E);
	eMenuItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			//((ChessModel)model).InitialPosition();
		}
    });
JMenuItem eMenuItem1 = new JMenuItem("Exit");
	
	eMenuItem1.setMnemonic(KeyEvent.VK_E);
	eMenuItem1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			((ChessFrame)FRAME).Close();
		}
    });
	

	
	
	
	
	
	
	
	
	menu.add(eMenuItem);
	menu.addSeparator();
	menu.add(eMenuItem1);
	
	
	
	menubar.add(menu);
	
	FRAME.setJMenuBar(menubar);
	
	
	
	
	
	
	
}
	public void InitialPosition(Piece pieces[][]){
		pieces[0][0].setImg(images[8]);
		pieces[0][1].setImg(images[10]);
		pieces[0][2].setImg(images[9]);
		pieces[0][3].setImg(images[6]);
		pieces[0][4].setImg(images[7]);
		pieces[0][5].setImg(images[9]);
		pieces[0][6].setImg(images[10]);
		pieces[0][7].setImg(images[8]);
		pieces[1][0].setImg(images[11]);
		pieces[1][1].setImg(images[11]);
		pieces[1][2].setImg(images[11]);
		pieces[1][3].setImg(images[11]);
		pieces[1][4].setImg(images[11]);
		pieces[1][5].setImg(images[11]);
		pieces[1][6].setImg(images[11]);
		pieces[1][7].setImg(images[11]);
		//Bianchi
		pieces[7][0].setImg(images[2]);
		pieces[7][1].setImg(images[4]);
		pieces[7][2].setImg(images[3]);
		pieces[7][3].setImg(images[0]);
		pieces[7][4].setImg(images[1]);
		pieces[7][5].setImg(images[3]);
		pieces[7][6].setImg(images[4]);
		pieces[7][7].setImg(images[2]);
		pieces[6][0].setImg(images[5]);
		pieces[6][1].setImg(images[5]);
		pieces[6][2].setImg(images[5]);
		pieces[6][3].setImg(images[5]);
		pieces[6][4].setImg(images[5]);
		pieces[6][5].setImg(images[5]);
		pieces[6][6].setImg(images[5]);
		pieces[6][7].setImg(images[5]);
		Change(pieces);
		
	}
	
	public void Change(Piece[][] pieces){//cambia la posizione grafica delle pedine
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(pieces[i][j]!=null){
				tiles[i][j].setImg(pieces[i][j].getImg());
				}else{
					tiles[i][j].setImg(null);
				}
			}
			}
		
		this.repaint();
		
		
		
	}
	
	
	
	
	
		
	
	private void LoadImages(){
		images[0]=this.makeColorTransparent(new ImageIcon("img/Wking.png").getImage(), Color.blue);
		images[1]=this.makeColorTransparent(new ImageIcon("img/Wqueen.png").getImage(), Color.blue);
		images[2]=this.makeColorTransparent(new ImageIcon("img/Wrook.png").getImage(), Color.blue);
		images[3]=this.makeColorTransparent(new ImageIcon("img/Wbishop.png").getImage(), Color.blue);
		images[4]=this.makeColorTransparent(new ImageIcon("img/Wknight.png").getImage(), Color.blue);
		images[5]=this.makeColorTransparent(new ImageIcon("img/Wpawn.png").getImage(), Color.blue);
		images[6]=this.makeColorTransparent(new ImageIcon("img/Bking.png").getImage(), Color.blue);
		images[7]=this.makeColorTransparent(new ImageIcon("img/Bqueen.png").getImage(), Color.blue);
		images[8]=this.makeColorTransparent(new ImageIcon("img/Brook.png").getImage(), Color.blue);
		images[9]=this.makeColorTransparent(new ImageIcon("img/Bbishop.png").getImage(), Color.blue);
		images[10]=this.makeColorTransparent(new ImageIcon("img/Bknight.png").getImage(), Color.blue);
		images[11]=this.makeColorTransparent(new ImageIcon("img/Bpawn.png").getImage(), Color.blue);
		
	}
	private void CreateTiles(){
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(((i+j) % 2)==0){
					
					add(tiles[i][j]=mkTile(i,j,Color.white));
					
				/*	 tiles[i][j].addMouseListener(new MouseAdapter(){
						@Override
						public void mousePressed(MouseEvent e){
			
							controller.onClick(i,j);
						}
						
						
					}); */
					
				}else{
					add(tiles[i][j]=mkTile(i,j,Color.gray));
					
					}
				}
			}
		
		
		
		
		
	}
	private Tile mkTile(int x, int y,Color color){
		Tile ret= new Tile(color);
		 ret.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){

				controller.onClick(x,y);
			}	
		});
		 return ret;
		 
	}
	public Image makeColorTransparent (Image im, final Color color) {
		 
		ImageFilter filter = new RGBImageFilter() {
		 
		// the color we are looking for... Alpha bits are set to opaque
		public int markerRGB = color.getRGB() | 0xFF000000;
		 
		public final int filterRGB(int x, int y, int rgb) {
		if ( ( rgb | 0xFF000000 ) == markerRGB ) {
		// Mark the alpha bits as zero - transparent
		return 0x00FFFFFF & rgb;
		}
		else {
		// nothing to do
		return rgb;
		}
		}
		};
		 
		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
		}
	
	
	
	public void setController(Controller controller){
		
		this.controller=controller;
		
	}

	@Override
	public Model getModel() {
		
		return null;
	}

	@Override
	public void LightUp(boolean[][] Pos) {
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(Pos[i][j]){
					tiles[i][j].setColor(Color.yellow);
				}
			}
		}
		repaint();
	}
public void LightDown(){
	
	for(int h=0;h<2;h++){
	for(int i=0;i<8;i++){
		for(int j=0;j<8;j++){
			
			if(tiles[i][j].getBackground()==Color.yellow ){
				if(((i+j) % 2)==0){
					tiles[i][j].setColor(Color.white);
					
				}else{
					tiles[i][j].setColor(Color.gray);
				}
			}
		}
	
	}
	
	super.repaint();
	}
}

@Override
public void Check(int x, int y,boolean stato) {
	
	if(stato){
	tiles[x][y].setColor(Color.red);
	}else{
	
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				
				if(tiles[i][j].getBackground()==Color.red ){
					if(((i+j) % 2)==0){
						tiles[i][j].setColor(Color.white);
						
					}else{
						tiles[i][j].setColor(Color.gray);
					}
				}
			}
		
		}
		
		
		
		
		
	
	}
}

@Override
public void ShowCheckMsg() {
	
	
	
	
	
	
	
	

	
	
	

	
	
Object[] options = {"New Game","Exit"};
int n = JOptionPane.showOptionDialog(this,"Checkmate!","Game Over",
JOptionPane.YES_NO_OPTION,
JOptionPane.PLAIN_MESSAGE,
null,     //do not use a custom Icon
options,  //the titles of buttons
options[0]); //default button title
	
if(n==1){
	
	((ChessFrame)FRAME).Close();
}else{
	
	((ChessModel)model).Inizialize();
	
	
}


	
	
}

@Override
public void PawnUpgrade(int x,int y,PColor currTurn) {
	
	
	Object[] possibilities = {"Queen","Knight", "Bishop", "Rook"};
	String s = (String)JOptionPane.showInputDialog(this, "Promote you Pawn!", "Pawn Upgrade",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    possibilities,
	                    "Queen");

	//If a string was returned, say so.
	if ((s != null) && (s.length() > 0)) {
	    Piece ret;
	    if(s.equals("Queen")){
	    	
	    	ret=new Queen(currTurn,(ChessModel)model);
	   if(currTurn==PColor.WHITE){ 	ret.setImg(images[1]);}else{ret.setImg(images[7]);}
	    	model.SetUpgradedPawn(x, y,ret );
	    	
	    }else if(s.equalsIgnoreCase("Rook")){
	    	
	       ret=new Rook(currTurn,(ChessModel)model);
	 	   if(currTurn==PColor.WHITE){ 	ret.setImg(images[2]);}else{ret.setImg(images[8]);}
	 	    	model.SetUpgradedPawn(x, y,ret );
	    	
	    }else if(s.equalsIgnoreCase("Knight")){
	    	
	    	   ret=new Knight(currTurn,(ChessModel)model);
	 	   if(currTurn==PColor.WHITE){ 	ret.setImg(images[4]);}else{ret.setImg(images[10]);}
	 	    	model.SetUpgradedPawn(x, y,ret );
	 	    	
	    }else if(s.equalsIgnoreCase("Bishop")){
	    	
	    	   ret=new Bishop(currTurn,(ChessModel)model);
	 	   if(currTurn==PColor.WHITE){ 	ret.setImg(images[3]);}else{ret.setImg(images[9]);}
	 	    	model.SetUpgradedPawn(x, y,ret );
	 	    	
	    }
	    
	  
	}
	
}


private static final long serialVersionUID = 1L;
	
	
}
