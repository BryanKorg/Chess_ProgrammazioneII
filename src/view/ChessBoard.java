package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Bishop;
import model.ChessModel;
import model.Knight;
import model.Model;
import model.PColor;
import model.Piece;
import model.Queen;
import model.Rook;
import controller.Controller;

public class ChessBoard extends JPanel implements View {
	
	private  Controller controller;
	private Tile tiles[][]= new Tile[8][8];
	private Image pieceImage,squareImage;
	private ChessFrame FRAME;
	private Model model;
	private JMenuBar menubar;
	
	
	public ChessBoard(ChessFrame FRAME,Model model){	
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
		
		JMenu menu;
		
		//Crea la barra dei menu
		menubar = new JMenuBar();
		menubar.setBackground(Color.white);
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);
		menubar.setBorder(blackline);
		//Crea l'unico menu
		menu = new JMenu("Chess");
		menu.setBackground(Color.LIGHT_GRAY);
		menu.setMnemonic(KeyEvent.VK_C);
		JMenuItem eMenuItem = new JMenuItem("New Game");
		
		
	
		
		eMenuItem.setMnemonic(KeyEvent.VK_N);
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.initChessboard();
			}
	    });
		
		JMenuItem eMenuItem1 = new JMenuItem("Exit");
		
		eMenuItem1.setMnemonic(KeyEvent.VK_E);
		eMenuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FRAME.Close();
			}
	    });
		
		menu.add(eMenuItem);
		menu.addSeparator();
		menu.add(eMenuItem1);
		menubar.add(menu);
		
		FRAME.setJMenuBar(menubar);
	}
	
	public void initialPosition(Piece pieces[][]){
		change(pieces);
		this.repaint();
	}
	
	public void change(Piece[][] pieces){//cambia la posizione grafica delle pedine
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(pieces[i][j]!=null){
					tiles[i][j].setPieceID(pieces[i][j].getPieceID());
				}else{
					tiles[i][j].setPieceID(-1);
				}
			}
		}
		this.repaint();
	}

	private void LoadImages(){
		squareImage=new ImageIcon(properties.tilesImagePath).getImage();
		pieceImage = new ImageIcon(properties.piecesImagePath).getImage();
	}
	
	private void CreateTiles(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(((i+j) % 2)==0){
					add(tiles[i][j]=mkTile(i,j,PColor.WHITE));					
				}else{
					add(tiles[i][j]=mkTile(i,j,PColor.BLACK));
				}
			}
		}	
	}
	
	private Tile mkTile(int x, int y,PColor color){
		Tile ret= new Tile(color,squareImage,pieceImage);
		ret.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				controller.onClick(x,y);
			}	
		});
		return ret; 
	}
	
	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public void lightUp(boolean[][] Pos) {
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(Pos[i][j]){
					tiles[i][j].focusOn();;
				}
			}
		}
		repaint();
	}
	
	public void lightDown(){
		for(int h=0;h<2;h++){
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					tiles[i][j].focusOff();
				}
			}
		super.repaint();
		}
	}

	@Override
	public void check(int x, int y,boolean stato) {
		if(stato){
			tiles[x][y].alertOn();
		}else{
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					tiles[i][j].alertOff();
				}
			}
		}
	}

	@Override
	public void showCheckMsg() {
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
			model.initChessboard();
		}
	}

	@Override
	public void pawnUpgrade(int x,int y,PColor currTurn) {
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
		    	model.SetUpgradedPawn(x, y,ret );
		    }else if(s.equalsIgnoreCase("Rook")){
		    	ret=new Rook(currTurn,(ChessModel)model);
		    	model.SetUpgradedPawn(x, y,ret );
		    }else if(s.equalsIgnoreCase("Knight")){
		    	ret=new Knight(currTurn,(ChessModel)model);
		    	model.SetUpgradedPawn(x, y,ret );
		    }else if(s.equalsIgnoreCase("Bishop")){
		    	ret=new Bishop(currTurn,(ChessModel)model);
		    	model.SetUpgradedPawn(x, y,ret );   	
		    } 
		}
	}
	public void changeMenuColor(PColor color){
		if(color==PColor.WHITE){
			menubar.setBackground(properties.colorWhite);
		}else{
			menubar.setBackground(properties.colorBlack);
		}
	}
	private static final long serialVersionUID = 1L;


	@Override
	public void showStaleMate() {
		Object[] options = {"New Game","Exit"};
		int n = JOptionPane.showOptionDialog(this,"Stalemate!","Stalemate!!!",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.PLAIN_MESSAGE,
		null,     //do not use a custom Icon
		options,  //the titles of buttons
		options[0]); //default button title
			
		if(n==1){
			((ChessFrame)FRAME).Close();
		}else{
			model.initChessboard();
		}
	}

	@Override
	public void setController(Controller controller) {
		this.controller=controller;
	}
}
