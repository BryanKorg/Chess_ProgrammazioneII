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

import model.ChessModel;
import model.Model;
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
			
			
			((ChessModel)model).InitialPosition();
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
	
	
	public void Change(int[][] positions){//cambia la posizione grafica delle pedine
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){

		switch(positions[i][j]){
		case 11:tiles[i][j].setImg(images[11]);
				break;
		case 8:tiles[i][j].setImg(images[8]);
		break;
		case 10:tiles[i][j].setImg(images[10]);
		break;
		case 9:tiles[i][j].setImg(images[9]);
		break;
		case 6:tiles[i][j].setImg(images[6]);
		break;
		case 7:tiles[i][j].setImg(images[7]);
		break;
		
		case 5:tiles[i][j].setImg(images[5]);
		break;
		case 2:tiles[i][j].setImg(images[2]);
		break;
		case 4:tiles[i][j].setImg(images[4]);
		break;
		case 3:tiles[i][j].setImg(images[3]);
		break;
		case 0:tiles[i][j].setImg(images[0]);
		break;
		case 1:tiles[i][j].setImg(images[1]);
		break;
		default:
			tiles[i][j].setImg(null);}
	
				}
		
			}
		//}
		
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
	
	((ChessModel)model).InitialPosition();
	
	
}


	
	
}

@Override
public void PawnUpgrade(int x,int y,boolean turn) {
	
	
	Object[] possibilities = {"Queen","Knight", "Bishop", "Rook"};
	String s = (String)JOptionPane.showInputDialog(this, "Promote you Pawn!", "Pawn Upgrade",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    possibilities,
	                    "Queen");

	//If a string was returned, say so.
	if ((s != null) && (s.length() > 0)) {
	    if(turn){
	    if(s.equals("Queen")){
	    	model.SetUpgradedPawn(x, y, 1);
	    }else if(s.equalsIgnoreCase("Rook")){
	    	model.SetUpgradedPawn(x, y, 2);
	    }else if(s.equalsIgnoreCase("Knight")){
	    	model.SetUpgradedPawn(x, y, 4);
	    }else if(s.equalsIgnoreCase("Bishop")){
	    	model.SetUpgradedPawn(x, y, 3);
	}
	    
	    }else{
	    	
	    	   if(s.equals("Queen")){
	   	    	model.SetUpgradedPawn(x, y, 7);
	   	    }else if(s.equalsIgnoreCase("Rook")){
	   	    	model.SetUpgradedPawn(x, y, 8);
	   	    }else if(s.equalsIgnoreCase("Knight")){
	   	    	model.SetUpgradedPawn(x, y, 10);
	   	    }else if(s.equalsIgnoreCase("Bishop")){
	   	    	model.SetUpgradedPawn(x, y, 9);
	   	}
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	}
	
}


private static final long serialVersionUID = 1L;
	
	
}
