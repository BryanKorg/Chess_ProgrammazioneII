package view;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import model.ChessModel;
import model.Model;
import controller.ChessController;
import controller.Controller;

public class ChessFrame extends JFrame {//Frame principale
	private final Controller controller;
	private final Model model= new ChessModel();
	private View chessboard;
	public ChessFrame(){//inizializza le altre componenti 
		setTitle("Chess");
	
		chessboard= AddChessBoard();
		this.controller= new ChessController(chessboard);
		controller.setModel(model);
		model.setView(chessboard);
		
		((ChessModel)model).InitialPosition();	 
	}
	
	
	
	public void Close(){
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	
	private View AddChessBoard(){
		ChessBoard panel= new ChessBoard(this,model);
		panel.setSize(500, 500);
		add(panel);
		return panel;
	}
	
	private static final long serialVersionUID = 1L;


	public Model getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setController(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	











	

 



}

