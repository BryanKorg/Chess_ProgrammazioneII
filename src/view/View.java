package view;
import controller.Controller;
import model.Model;
import model.Piece;


public interface View {
	Model getModel();
	void setController(Controller controller);
	void Change( Piece[][] pieces); //TODO cambiarlo in pedine
	void LightUp(boolean Pos[][]);
	void LightDown();
	void Check(int x,int y,boolean stato);
	void ShowCheckMsg();
	void PawnUpgrade(int x,int y,boolean turn);
	public void InitialPosition(Piece pieces[][]);

	
}
