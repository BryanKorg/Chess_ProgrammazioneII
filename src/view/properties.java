package view;

import java.awt.Color;

public class properties {

	//Images Path
		public static String piecesImagePath = "img/pezzi-scacchi.png";
		public static String tilesImagePath = "img/scacchiera.png";
		public static String backgroundImagePath = "img/back.png";
		
	//Images dimensions
		public static int piecesDimension = 128;
		public static int tilesDimension = 200;
		public static int pieceOffset = 0; //Offset interno per il pezzo
		
	//Colors
		public static Color colorFocus=new Color(0x8800B400,true);
		public static Color colorAlert=new Color(0x88FF0000,true);
		public static Color colorWhite=new Color(0xFFf6f6f6,true);
		public static Color colorBlack=new Color(0xFF5d5d5d,true);
}
