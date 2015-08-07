package controller;
import model.Model;
import view.View;


public class ChessController implements Controller {
private final View view;
private Model model;
	public ChessController(View view){
		this.view=view;
		
		
		view.setController(this);
	}
	
	
	@Override
	public void onClick(int x, int y) {
		// TODO Auto-generated method stub
	 model.MoveCheck(x, y);//gestisce il clic di una casella
	 
	
	}


	@Override
	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
	}

}
