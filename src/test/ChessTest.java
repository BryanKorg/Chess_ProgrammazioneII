package test;

import static org.junit.Assert.*;
import model.ChessModel;
import model.King;
import model.Knight;
import model.Model;
import model.PColor;
import model.Pawn;
import model.Piece;
import model.Queen;

import org.junit.Test;

public class ChessTest {

	/**
	 * Test Colori diversi
	 */
	@Test
	public void colorNotEqualsTest() {
		//private Knight cav= new Knight(PColor.BLACK, model);
		ChessModel modello = new ChessModel();
		assertNotEquals(new Knight(PColor.BLACK, modello), new Knight(PColor.WHITE, modello));
	}

	/**
	 * Test pezzi diversi
	 */
	@Test
	public void pieceTypeNotEqualTest() {
		ChessModel modello = new ChessModel();
		assertNotEquals(new Knight(PColor.BLACK, modello), new Pawn(PColor.BLACK, modello));
	}
	
	/**
	 * Test di stallo configurazione re nero sopra pedone bianco sopra re bianco turno nero
	 */
	@Test
	public void stallmateTest(){
		ChessModel modello = new ChessModel();
		Piece [][]scacchiera=new Piece [8][8];

		scacchiera[0][1]=new King(PColor.BLACK, modello);
		scacchiera[2][1]=new King(PColor.WHITE, modello);
		scacchiera[1][1]=new Pawn(PColor.WHITE, modello);
		
		modello.setConfiguration(scacchiera, 1, 0, 1, 2, PColor.BLACK);
		assertTrue(modello.IfCheckMate() && modello.IfCheck()==false); //Stallo
		
	}
	
	/**
	 * Test di scacco matto
	 * Re nero in a8 regina bianca in b7 re nero in c6
	 */
	@Test
	public void chessMateTest(){
		ChessModel modello = new ChessModel();
		Piece [][]scacchiera=new Piece [8][8];

		scacchiera[0][0]=new King(PColor.BLACK, modello);
		scacchiera[1][1]=new Queen(PColor.WHITE, modello);
		scacchiera[2][2]=new King(PColor.WHITE, modello);
		
		modello.setConfiguration(scacchiera, 0, 0, 2, 2, PColor.BLACK);
		assertTrue(modello.IfCheckMate() && modello.IfCheck()==true); //Scacco Matto
		
	}
	
	//TODO finisci test
}
